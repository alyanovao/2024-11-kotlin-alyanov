package ru.aao.geolocation.repository.inmemory

import com.benasher44.uuid.uuid4
import io.github.reactivecircus.cache4k.Cache
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import ru.aao.geolocation.common.models.BaseGeolocation
import ru.aao.geolocation.common.models.GeolocationId
import ru.aao.geolocation.common.models.GlLock
import ru.aao.geolocation.common.repository.*
import ru.aao.geolocation.common.repository.exception.RepoEmptyLockException
import ru.aao.geolocation.repository.common.IRepoInitialize
import kotlin.random.Random
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

class RepoInMemory (
    ttl: Duration = 2.minutes,
    val randomUuid: () -> String = {uuid4().toString()}
): GeolocationRepoBase(), IRepository, IRepoInitialize {
    private val mutex = Mutex()
    private val cache = Cache.Builder<String, GlEntity>()
    .expireAfterWrite(ttl)
    .build()

    override fun save(gls: Collection<BaseGeolocation>) = gls.map { gl ->
        val entity = GlEntity(gl)
        require(entity.personId != null)
        cache.put(entity.personId, entity)
        gl
    }

    override suspend fun create(request: DBGlRequest): IDBGlResponse = tryGlMethod {
        val key = Random.nextLong(0, Long.MAX_VALUE)

        val gl = request.request.copy(
            id = GeolocationId(key),
            lock = GlLock(key.toString())
        )
        val entity = GlEntity(gl)
        mutex.withLock{
            cache.put(key.toString(), entity)
        }
        DBGlResponseOk(gl)
    }

    override suspend fun readCurrent(request: DBGlIdRequest): IDBGlResponse = tryGlMethod{
        val key = request.id.asLong();
        mutex.withLock{
            cache.get(key.toString())
                ?.let {
                    DBGlResponseOk(it.toInternal())
                } ?: errorNoFound(request.id);
        }
    }

    override suspend fun readAll(request: DBGlRequest): IDBGlsResponse = tryGlsMethod{
        val result: List<BaseGeolocation> = cache.asMap().asSequence()
            .filter { entity ->
                request.request.takeIf { it != null }?.let {
                it.personId.asLong().toString() == entity.value.personId &&
                it.deviceId.asLong().toString() == entity.value.deviceId
                } ?: true
            }
            .map { it.value.toInternal() }
            .toList()
        DBGlsResponseOk(result)
    }

    override suspend fun update(request: DBGlRequest): IDBGlResponse = tryGlMethod{
        val request = request.request
        val id = request.id.takeIf { it != GeolocationId.NONE } ?: GeolocationId.NONE
        val key = request.id.asLong()
        mutex.withLock {
            val oldId = cache.get(key.toString())?.toInternal()
            when {
                oldId == null -> errorNoFound(id)
                else -> {
                    val newGl = request.copy(lock = GlLock(randomUuid()))
                    val entity = GlEntity(newGl)
                    cache.put(key.toString(), entity)
                    DBGlResponseOk(newGl)
                }
            }
        }
    }

    override suspend fun delete(request: DBGlRequest): IDBGlResponse = tryGlMethod{
        val id = request.request.id
        val personId = request.request.personId
        val deviceId = request.request.deviceId
        val key = id.asLong().toString()
        mutex.withLock {
            val oldGl = cache.get(key)?.toInternal()
            val oldPersonId = oldGl?.personId
            val oldDeviceId = oldGl?.deviceId
            when {
                oldGl == null -> errorNoFound(id)
                personId != oldPersonId -> errorNoFound(id)
                deviceId != oldDeviceId -> errorNoFound(id)
                else -> {
                    cache.invalidate(key)
                    DBGlResponseOk(oldGl)
                }
            }
        }
    }
}
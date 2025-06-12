package ru.aao.geolocation.repository.postgres

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import ru.aao.geolocation.common.models.*
import kotlin.random.Random

class GlTable(table: String): Table(table) {
    val id = text(SqlField.ID)
    //val title = text(SqlField.TITLE)
    //val description = text(SqlField.DESCRIPTION)
    val personId = text(SqlField.PERSON_ID).nullable()
    val deviceId = text(SqlField.DEVICE_ID).nullable()
    val longitude = text(SqlField.LONGITUDE).nullable()
    val latitude = text(SqlField.LATITUDE).nullable()
    val bearing = (text(SqlField.BEARING).nullable())
    val altitude = (text(SqlField.ALTITUDE).nullable())

    override val primaryKey = PrimaryKey(id)

    fun from(res: ResultRow) = BaseGeolocation(
        id = GeolocationId(res[id].toLong()),
        personId = res[personId]?.let { PersonId(it.toLong()) } ?: PersonId.NONE,
        deviceId = res[deviceId]?.let { DeviceId(it.toLong()) } ?: DeviceId.NONE,
        longitude = res[longitude]?.let { Longitude(it.toDouble()) } ?: Longitude.NONE,
        latitude = res[latitude]?.let { Latitude(it.toDouble()) } ?: Latitude.NONE,
        bearing = res[bearing]?.let { Bearing(it.toDouble()) } ?: Bearing.NONE,
        altitude = res[altitude]?.let {Altitude(it.toDouble()) } ?: Altitude.NONE
    )

    fun to(it: UpdateBuilder<*>, res: BaseGeolocation, randomUuid: () -> String) {
        it[id] = res.id.takeIf { it != GeolocationId.NONE }?.asLong()?.toString() ?: Random.nextLong(1, 100000).toString()
        it[personId] = res.personId.takeIf { it != PersonId.NONE }?.asLong()?.toString() ?: randomUuid()
        it[deviceId] = res.deviceId.takeIf { it != DeviceId.NONE }?.asLong()?.toString() ?: randomUuid()
        it[longitude] = res.longitude.takeIf { it != Longitude.NONE }?.asDouble().toString()
        it[latitude] = res.latitude.takeIf { it != Latitude.NONE }?.asDouble().toString()
        it[bearing] = res.bearing.takeIf { it != Bearing.NONE }?.asDouble().toString()
        it[altitude] = res.altitude.takeIf { it != Altitude.NONE }?.asDouble().toString()
    }
}
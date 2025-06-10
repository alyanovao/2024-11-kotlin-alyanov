package ru.aao.geolocation.repository.postgres

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import ru.aao.geolocation.common.models.BaseGeolocation
import ru.aao.geolocation.common.models.DeviceId
import ru.aao.geolocation.common.models.GeolocationId
import ru.aao.geolocation.common.models.PersonId

class GlTable(table: String): Table(table) {
    val id = text(SqlField.ID)
    val title = text(SqlField.TITLE)
    val description = text(SqlField.DESCRIPTION)
    val personId = text(SqlField.PERSON_ID).nullable()
    val deviceId = text(SqlField.DEVICE_ID).nullable()

    override val primaryKey = PrimaryKey(id)

    fun from(res: ResultRow) = BaseGeolocation(
        id = GeolocationId(res[id].toLong()),
        personId = res[personId]?.let { PersonId(it.toLong()) } ?: PersonId.NONE,
        deviceId = res[deviceId]?.let { DeviceId(it.toLong()) } ?: DeviceId.NONE,
    )

    fun to(it: UpdateBuilder<*>, res: BaseGeolocation, randomUuid: () -> String) {
        //it[id] = res.id.takeIf { it != GeolocationId.NONE }?.asLong() ?: randomUuid()
        //it[personId] = res.personId.takeIf { it != PersonId.NONE } ?: randomUuid()
        //it[deviceId] = res.deviceId.takeIf { it != DeviceId.NONE } ?: randomUuid()
    }
}
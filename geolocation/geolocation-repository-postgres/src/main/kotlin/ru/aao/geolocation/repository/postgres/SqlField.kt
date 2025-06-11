package ru.aao.geolocation.repository.postgres

object SqlField {
    const val ID = "id"
    const val TITLE = "title"
    const val DESCRIPTION = "description"
    const val PERSON_ID = "person_id"
    const val DEVICE_ID = "device_id"
    const val LONGITUDE = "longitude"
    const val LATITUDE = "latitude"
    const val BEARING = "bearing"
    const val ALTITUDE = "altitude"
    val allFields = listOf(ID, TITLE, DESCRIPTION, PERSON_ID, DEVICE_ID, LONGITUDE, LATITUDE, BEARING)

    const val TYPE = "type"
}
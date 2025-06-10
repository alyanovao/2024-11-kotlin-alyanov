package ru.aao.geolocation.repository.postgres

object SqlField {
    const val ID = "id"
    const val TITLE = "title"
    const val DESCRIPTION = "description"
    const val PERSON_ID = "person_id"
    const val DEVICE_ID = "device_id"
    val allFields = listOf(ID, TITLE, DESCRIPTION, PERSON_ID, PERSON_ID)

    const val TYPE = "type"
}
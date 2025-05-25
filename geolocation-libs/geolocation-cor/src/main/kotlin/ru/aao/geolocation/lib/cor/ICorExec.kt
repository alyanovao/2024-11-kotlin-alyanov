package ru.aao.geolocation.lib.cor

interface ICorExec<T> {
    val description: String
    suspend fun exec(context: T)
}
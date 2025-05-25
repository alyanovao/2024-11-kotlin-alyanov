package ru.aao.geolocation.lib.logging

interface IGlLogProvider: AutoCloseable, IGlLogWrapper {
    val logId: String

    override fun close() {}
}
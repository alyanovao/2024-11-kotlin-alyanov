package ru.aao.dsl.user

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.TemporalAdjusters

class AvailableContext {
    private var _availabilities: MutableList<LocalDateTime> = mutableListOf()

    val availabilities: List<LocalDateTime>
        get() = _availabilities

    fun add(dateTime: LocalDateTime) {
        _availabilities.add(dateTime)
    }

    fun dayTimeOfWeek(day: DayOfWeek, time: String) {
        val day = LocalDate.now().with(TemporalAdjusters.next(day))
        val time = LocalTime.parse(time)
        add(LocalDateTime.of(day, time))
    }
}
package com.blackhand.sqldelight.core.common

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

object DateTimeUtils {
    fun now(): LocalDateTime {
        return Clock.System.now().toLocalDateTime(kotlinx.datetime.TimeZone.currentSystemDefault())
    }

    fun toEpochMillis(date: LocalDateTime?): Long {
        return date?.toInstant(kotlinx.datetime.TimeZone.currentSystemDefault())
            ?.toEpochMilliseconds() ?: 0
    }

    fun formatNoteDate(date: LocalDateTime): String {
        val month = date.month
        val dayOfMonth = if (date.dayOfMonth > 10) "0${date.dayOfMonth}" else date.dayOfMonth
        val year = date.year
        val hour = if (date.hour > 10) "0${date.hour}" else date.hour
        val min = if (date.minute > 10) "0${date.minute}" else date.minute
        return buildString {
            append(dayOfMonth)
            append("")
            append(month)
            append("")
            append(year)
            append(", ")
            append(hour)
            append(" : ")
            append(min)
        }
    }
}
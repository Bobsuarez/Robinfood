package com.robinfood.core.utils

import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

/**
 * Returns the actual date of timeZone with format
 */
fun getCurrentDateTimeInTimeZone(timeZone: String, format: String): String {
    val zoneId = ZoneId.of(timeZone)
    val currentDateTime = ZonedDateTime.now(zoneId)
    val formatter = DateTimeFormatter.ofPattern(format)
    return formatter.format(currentDateTime)
}
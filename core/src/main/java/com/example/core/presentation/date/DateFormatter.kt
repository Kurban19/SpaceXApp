package com.example.core.presentation.date

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

private const val PARSE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss"
private const val DATE_END_OFFSET = 5

class DateFormatter {

    fun format(stringDate: String, dateFormat: DateConversion): String {
        val date = LocalDateTime.parse(
            stringDate.dropLast(DATE_END_OFFSET),
            DateTimeFormatter.ofPattern(PARSE_PATTERN),
        )

        val formatter = DateTimeFormatter.ofPattern(dateFormat.format, Locale.US)
        return date.format(formatter)
    }

}
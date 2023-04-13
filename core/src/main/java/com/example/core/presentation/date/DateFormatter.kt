package com.example.core.presentation.date

import java.time.LocalDate
import java.time.format.DateTimeFormatter

private const val PARSE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss"
private const val DATE_END_OFFSET = 5

class DateFormatter {

    fun format(stringDate: String, dateFormat: DateConversion): String {
        val date = LocalDate.parse(
            stringDate.dropLast(DATE_END_OFFSET),
            DateTimeFormatter.ofPattern(PARSE_PATTERN)
        )

        val formatter = DateTimeFormatter.ofPattern(dateFormat.format)
        return date.format(formatter)
    }

}
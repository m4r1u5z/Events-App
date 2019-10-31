package com.baddevelopergames.eventsapp.eventslist

import org.junit.Assert.*
import org.junit.Test
import java.util.*

class DateParserTest {
    private val dateParser = DateParser()

    @Test
    fun `date parser test`() {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
        calendar.set(Calendar.HOUR_OF_DAY, 18)
        calendar.set(Calendar.MINUTE, 48)
        val strValue = dateParser.parse(calendar.timeInMillis)
        assertEquals("Monday 18:48", strValue)
    }
}
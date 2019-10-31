package com.baddevelopergames.eventsapp.eventslist

import java.text.SimpleDateFormat
import java.util.*

class DateParser {
    private val simpleDateFormat = SimpleDateFormat("EEEE HH:mm", Locale.getDefault())

    fun parse(time: Long): String {
        val date = Date(time)
        return simpleDateFormat.format(date)
    }
}
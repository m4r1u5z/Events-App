package com.baddevelopergames.eventsapp.data.model

sealed class Day(val value: Long) {
    object Friday : Day(1548975600000L)
    object Saturday : Day(1549062000000L)
    object Sunday : Day(1549148400000L)
}

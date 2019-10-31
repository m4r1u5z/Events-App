package com.baddevelopergames.eventsapp.utils

fun Long.isBetween(left: Long, right: Long) = this in (left + 1) until right
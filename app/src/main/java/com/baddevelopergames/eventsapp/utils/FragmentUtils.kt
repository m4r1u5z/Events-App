package com.baddevelopergames.eventsapp.utils

import androidx.fragment.app.Fragment

fun Fragment.contextNonNull() = context ?: throw NullPointerException("Context cannot be null")

fun Fragment.argumentsNonNull() = arguments ?: throw NullPointerException("Arguments cannot be null")

fun Fragment.activityNonNull() = activity ?: throw NullPointerException("Activity cannot be null")
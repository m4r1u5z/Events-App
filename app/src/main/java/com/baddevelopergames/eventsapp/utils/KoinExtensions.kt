package com.baddevelopergames.eventsapp.utils

import android.app.Activity
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

inline fun <reified T : Any> Activity.injectActivity(): Lazy<T> = inject { parametersOf(this) }

inline fun <reified T : Any> Fragment.injectFragment(): Lazy<T> = inject { parametersOf(this) }
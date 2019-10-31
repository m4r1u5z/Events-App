package com.baddevelopergames.eventsapp

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.After
import org.junit.Before
import java.io.File

open class BaseTestBehaviour {

    @Before
    open fun setUp() {
        clearLocalStorage()
    }

    @After
    open fun tearDown() {
        clearLocalStorage()
    }

    private fun clearLocalStorage() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val root = context.filesDir.parentFile
        val sharedPreferencesFileNames = File(root, "shared_prefs").list()
        for (fileName in sharedPreferencesFileNames) {
            context.getSharedPreferences(fileName.replace(".xml", ""), Context.MODE_PRIVATE).edit().clear().commit()
        }
    }
}
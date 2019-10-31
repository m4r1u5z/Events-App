package com.baddevelopergames.eventsapp.main

import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.BlockJUnit4ClassRunner

@RunWith(BlockJUnit4ClassRunner::class)
class MainActivityTest {

    @get:Rule
    val testRule = ActivityTestRule(MainActivity::class.java)

    private val mainScreen = MainScreen()

    @Test
    fun mainActivityShouldContainToolbar() {
        mainScreen {
            toolbar {
                isDisplayed()
            }
        }
    }
}
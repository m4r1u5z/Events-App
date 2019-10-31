package com.baddevelopergames.eventsapp.splashscreen

interface SplashScreenView {
    fun openMainActivity()

    fun showError(message: String)
}
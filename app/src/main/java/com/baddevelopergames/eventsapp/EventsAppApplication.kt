package com.baddevelopergames.eventsapp

import android.app.Application
import com.baddevelopergames.eventsapp.favourites.favouritesModule
import com.baddevelopergames.eventsapp.eventslist.eventsModule
import com.baddevelopergames.eventsapp.splashscreen.splashScreenModule
import com.baddevelopergames.eventsapp.workshops.workshopsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

@Suppress("unused")
class EventsAppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@EventsAppApplication)
            modules(splashScreenModule, workshopsModule, eventsModule, favouritesModule, appModule)
        }
    }
}
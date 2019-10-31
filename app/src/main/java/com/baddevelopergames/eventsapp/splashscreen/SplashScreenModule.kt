package com.baddevelopergames.eventsapp.splashscreen

import org.koin.dsl.module

val splashScreenModule = module {
    factory { (view: SplashScreenView) -> SplashScreenPresenter(view, get()) }
}
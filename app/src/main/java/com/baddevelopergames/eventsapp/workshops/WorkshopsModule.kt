package com.baddevelopergames.eventsapp.workshops

import org.koin.dsl.module

val workshopsModule = module {
    factory { (view: WorkshopsPresenter.WorkshopsView) -> WorkshopsPresenter(view, get()) }
}
package com.baddevelopergames.eventsapp.favourites

import org.koin.dsl.module

val favouritesModule = module {
    factory { (view: FavouritesPresenter.FavouritesView) -> FavouritesPresenter(view, get()) }
}
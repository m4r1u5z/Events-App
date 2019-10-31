package com.baddevelopergames.eventsapp.favourites

import com.baddevelopergames.eventsapp.data.model.base.EventsAppItem
import com.baddevelopergames.eventsapp.data.usecase.UseCaseFactory

class FavouritesPresenter(
    private val view: FavouritesView,
    private val useCaseFactory: UseCaseFactory
) {
    interface FavouritesView {
        fun initRecyclerView(list: MutableList<EventsAppItem>)
    }

    fun init() {
        view.initRecyclerView(useCaseFactory.getFavouritesItemsUseCase())
    }
}
package com.baddevelopergames.eventsapp.data.repository

import com.baddevelopergames.eventsapp.data.model.Day
import com.baddevelopergames.eventsapp.data.model.ItemType
import com.baddevelopergames.eventsapp.data.model.base.EventsAppItem

interface ItemsRepository {
    fun getAmountOfDays(): Int

    operator fun invoke(
        type: ItemType? = null,
        onlyFavourites: Boolean = false,
        day: Day? = null
    ): List<EventsAppItem>
}
package com.baddevelopergames.eventsapp.data

import com.baddevelopergames.eventsapp.data.model.base.EventsAppItem

object Items {
    var items = listOf<EventsAppItem>()
    fun getAllStoredItems() = items
}

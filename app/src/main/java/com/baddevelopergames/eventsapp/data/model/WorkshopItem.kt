package com.baddevelopergames.eventsapp.data.model

import com.baddevelopergames.eventsapp.data.model.base.EventsAppItem

class WorkshopItem(
    id: String,
    imageUrl: String,
    title: String,
    timeStart: Long,
    val author: String,
    val buyUrl: String
) : EventsAppItem(id, imageUrl, title, timeStart, ItemType.WORKSHOP)
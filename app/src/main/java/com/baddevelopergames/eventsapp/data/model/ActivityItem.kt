package com.baddevelopergames.eventsapp.data.model

import com.baddevelopergames.eventsapp.data.model.base.EventsAppItem

class ActivityItem(
    id: String,
    imageUrl: String,
    title: String,
    time: Long,
    val ticketsUrl: String,
    val address: String,
    val facebook: String
) : EventsAppItem(id, imageUrl, title, time, ItemType.ACTIVITY)
package com.baddevelopergames.eventsapp.data.model

import com.baddevelopergames.eventsapp.data.model.base.EventsAppItem

class LectureItem(
    id: String,
    imageUrl: String,
    title: String,
    time: Long,
    val author: String,
    val description: String,
    val superStar: Boolean
) : EventsAppItem(id, imageUrl, title, time, ItemType.LECTURE)
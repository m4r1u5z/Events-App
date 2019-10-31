package com.baddevelopergames.eventsapp.data.entity

import com.baddevelopergames.eventsapp.data.model.WorkshopItem
import com.google.gson.annotations.SerializedName

class WorkshopEntity {
    @SerializedName("id")
    lateinit var id: String

    @SerializedName("image")
    lateinit var image: String

    @SerializedName("title")
    lateinit var title: String

    @SerializedName("time_start")
    var timeStart: Long = 0L

    @SerializedName("author")
    lateinit var author: String

    @SerializedName("tickets_url")
    lateinit var ticketsUrl: String
}

fun WorkshopEntity.toEventsAppItem() = WorkshopItem(
    id = id,
    imageUrl = image,
    title = title,
    timeStart = timeStart,
    author = author,
    buyUrl = ticketsUrl
)
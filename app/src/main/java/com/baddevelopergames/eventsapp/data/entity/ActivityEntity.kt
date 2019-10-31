package com.baddevelopergames.eventsapp.data.entity

import com.baddevelopergames.eventsapp.data.model.ActivityItem
import com.google.gson.annotations.SerializedName

class ActivityEntity {
    @SerializedName("id")
    lateinit var id: String

    @SerializedName("image")
    lateinit var image: String

    @SerializedName("title")
    lateinit var title: String

    @SerializedName("time")
    var time: Long = 0L

    @SerializedName("tickets_url")
    lateinit var ticketsUrl: String

    @SerializedName("address")
    lateinit var address: String

    @SerializedName("facebook")
    lateinit var facebook: String
}

fun ActivityEntity.toEventsAppItem() = ActivityItem(
    id = id,
    imageUrl = image,
    title = title,
    time = time,
    ticketsUrl = ticketsUrl,
    address = address,
    facebook = facebook
)
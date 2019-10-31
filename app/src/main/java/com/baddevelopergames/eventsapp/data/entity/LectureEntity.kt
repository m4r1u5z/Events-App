package com.baddevelopergames.eventsapp.data.entity

import com.baddevelopergames.eventsapp.data.model.LectureItem
import com.google.gson.annotations.SerializedName

class LectureEntity {
    @SerializedName("id")
    lateinit var id: String

    @SerializedName("image")
    lateinit var image: String

    @SerializedName("title")
    lateinit var title: String

    @SerializedName("time")
    var time: Long = 0L

    @SerializedName("author")
    lateinit var author: String

    @SerializedName("description")
    lateinit var description: String

    @SerializedName("super_star")
    var superStar: Boolean = false
}

fun LectureEntity.toEventsAppItem() = LectureItem(
    id = id,
    imageUrl = image,
    title = title,
    time = time,
    author = author,
    description = description,
    superStar = superStar
)
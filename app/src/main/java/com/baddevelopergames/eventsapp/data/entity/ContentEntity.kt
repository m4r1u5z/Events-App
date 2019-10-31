package com.baddevelopergames.eventsapp.data.entity

import com.google.gson.annotations.SerializedName

class ContentEntity {
    @SerializedName("lectures")
    var lectures: List<LectureEntity> = listOf()

    @SerializedName("workshops")
    var workshops: List<WorkshopEntity> = listOf()

    @SerializedName("activities")
    var activities: List<ActivityEntity> = listOf()
}

package com.baddevelopergames.eventsapp.data.model.base

import com.baddevelopergames.eventsapp.data.model.ItemType
import java.io.Serializable

open class EventsAppItem(
    val id: String,
    val imageUrl: String,
    val title: String,
    val timeStart: Long,
    val type: ItemType,
    var isExpanded: Boolean = false
) : Serializable
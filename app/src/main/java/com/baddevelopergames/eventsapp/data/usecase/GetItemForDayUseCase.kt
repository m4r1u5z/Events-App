package com.baddevelopergames.eventsapp.data.usecase

import com.baddevelopergames.eventsapp.data.model.Day
import com.baddevelopergames.eventsapp.data.model.base.EventsAppItem
import com.baddevelopergames.eventsapp.data.repository.ItemsRepository

class GetItemForDayUseCase(private val repository: ItemsRepository) {
    operator fun invoke(day: Day): List<EventsAppItem> = repository.invoke(day = day)
}
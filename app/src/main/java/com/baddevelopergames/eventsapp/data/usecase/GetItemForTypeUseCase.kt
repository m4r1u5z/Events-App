package com.baddevelopergames.eventsapp.data.usecase

import com.baddevelopergames.eventsapp.data.model.ItemType
import com.baddevelopergames.eventsapp.data.repository.ItemsRepository

class GetItemForTypeUseCase(private val repository: ItemsRepository) {
    operator fun invoke(type: ItemType) = repository.invoke(type = type).toMutableList()
}
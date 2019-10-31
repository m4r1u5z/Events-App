package com.baddevelopergames.eventsapp.data.usecase

import com.baddevelopergames.eventsapp.data.repository.ItemsRepository

class GetDaysCountUseCase(private val repository: ItemsRepository) {
    operator fun invoke() = repository.getAmountOfDays()
}
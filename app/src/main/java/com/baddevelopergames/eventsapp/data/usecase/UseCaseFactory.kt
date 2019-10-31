package com.baddevelopergames.eventsapp.data.usecase

import com.baddevelopergames.eventsapp.data.model.Day
import com.baddevelopergames.eventsapp.data.model.ItemType
import com.baddevelopergames.eventsapp.data.repository.ItemsRepository
import com.baddevelopergames.eventsapp.data.repository.RemoteRepository

class UseCaseFactory(
    private val repository: RemoteRepository,
    private val localRepository: ItemsRepository
) {
    fun getLecturesUseCase() = GetAppContentUseCase(repository).invoke()

    fun getItemsForDayUseCase(day: Day) = GetItemForDayUseCase(localRepository).invoke(day)

    fun getDaysCountUseCase() = GetDaysCountUseCase(localRepository).invoke()

    fun getItemsForTypeUseCase(type: ItemType) = GetItemForTypeUseCase(localRepository)
        .invoke(type)

    fun getFavouritesItemsUseCase() = GetFavouritesItemUseCase(localRepository).invoke()
}
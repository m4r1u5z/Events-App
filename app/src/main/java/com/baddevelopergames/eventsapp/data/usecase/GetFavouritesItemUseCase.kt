package com.baddevelopergames.eventsapp.data.usecase

import com.baddevelopergames.eventsapp.data.repository.ItemsRepository

class GetFavouritesItemUseCase(private val repository: ItemsRepository) {
    operator fun invoke() = repository.invoke(onlyFavourites = true).toMutableList()
}
package com.baddevelopergames.eventsapp.data.repository

import android.content.Context
import com.baddevelopergames.eventsapp.data.Items
import com.baddevelopergames.eventsapp.data.model.Day
import com.baddevelopergames.eventsapp.data.model.ItemType
import com.baddevelopergames.eventsapp.data.model.base.EventsAppItem
import com.baddevelopergames.eventsapp.favourites.FavouritesStorage
import com.baddevelopergames.eventsapp.utils.isBetween

class ItemsRepositoryImpl(
    context: Context,
    private val favouritesStorage: FavouritesStorage = FavouritesStorage(context)
) : ItemsRepository {

    override fun getAmountOfDays(): Int = 3

    override fun invoke(
        type: ItemType?,
        onlyFavourites: Boolean,
        day: Day?
    ): MutableList<EventsAppItem> {
        var items = Items.getAllStoredItems()

        type?.let {
            items = items.filter {
                it.type == type
            }
        }

        day?.let {
            items = items.filter {
                when (day) {
                    Day.Friday -> it.timeStart.isBetween(Day.Friday.value, Day.Saturday.value)
                    Day.Saturday -> it.timeStart.isBetween(Day.Saturday.value, Day.Sunday.value)
                    Day.Sunday -> it.timeStart > Day.Sunday.value
                }
            }
        }

        if (onlyFavourites) {
            val favouritesIds = favouritesStorage.getAll()
            items = items.filter {
                favouritesIds.contains(it.id)
            }
        }

        val mutableItems = items.toMutableList()
        mutableItems.sortBy { it.timeStart }
        return mutableItems
    }
}
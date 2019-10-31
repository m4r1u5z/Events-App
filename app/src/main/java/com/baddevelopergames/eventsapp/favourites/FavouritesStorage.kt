package com.baddevelopergames.eventsapp.favourites

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.baddevelopergames.eventsapp.data.model.base.EventsAppItem

private const val PREFIX = "favourite_"

class FavouritesStorage(
    context: Context,
    private val sp: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
) {
    fun addFavourite(item: EventsAppItem) {
        setFavourite(item, true)
    }

    fun removeFavourite(item: EventsAppItem) {
        setFavourite(item, false)
    }

    fun getAll(): List<String> {
        return sp.all.filter {
            it.value == true
        }.map {
            it.key.substringAfter(PREFIX)
        }
    }

    fun isFavourite(item: EventsAppItem) = sp.getBoolean(getKey(item), false)

    fun setFavourite(item: EventsAppItem, isFavourite: Boolean) {
        val editor = sp.edit()
        editor.putBoolean(getKey(item), isFavourite)
        editor.apply()
    }

    fun getKey(item: EventsAppItem) = "$PREFIX${item.id}"
}
package com.baddevelopergames.eventsapp.eventslist

import android.content.res.Resources
import androidx.fragment.app.FragmentManager
import com.baddevelopergames.eventsapp.R
import com.baddevelopergames.eventsapp.favourites.FavouritesStorage
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val eventsModule = module {
    factory { FavouritesStorage(androidContext()) }
    factory { androidContext().resources }
    factory { (interactions: EventsAdapterInteractions) ->
        EventsAdapter(androidContext(), mutableListOf(), interactions, get(), (get() as Resources))
    }
    factory { (fm: FragmentManager) ->
        EventsPagerAdapter((get() as Resources).getStringArray(R.array.days), fm, get())
    }
}
package com.baddevelopergames.eventsapp.favourites

import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import com.baddevelopergames.eventsapp.R

class FavouritesScreen : Screen<FavouritesScreen>() {
    val placeholder = KTextView { withId(R.id.empty_view) }
}
package com.baddevelopergames.eventsapp.workshops

import android.view.View
import com.agoda.kakao.image.KImageView
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import com.baddevelopergames.eventsapp.R
import org.hamcrest.Matcher

class WorkshopsScreenItem(parent: Matcher<View>) : KRecyclerItem<WorkshopsScreenItem>(parent) {
    val title = KTextView(parent) { withId(R.id.title) }
    val favourites = KImageView(parent) { withId(R.id.favourite) }
}

class WorkshopsScreen : Screen<WorkshopsScreen>() {
    val list =
        KRecyclerView(
            builder = { withId(R.id.workshops_rv) },
            itemTypeBuilder = { itemType(::WorkshopsScreenItem) })
}
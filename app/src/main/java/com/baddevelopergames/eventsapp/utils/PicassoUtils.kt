package com.baddevelopergames.eventsapp.utils

import android.widget.ImageView
import com.baddevelopergames.eventsapp.R
import com.squareup.picasso.Picasso

infix fun ImageView.loadFrom(path: String) {
    Picasso.get().load(path).resizeDimen(R.dimen.row_photo_width, R.dimen.row_photo_height)
        .centerCrop().into(this)
}
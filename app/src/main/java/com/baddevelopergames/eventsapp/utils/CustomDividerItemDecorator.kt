package com.baddevelopergames.eventsapp.utils

import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView

class CustomDividerItemDecorator(private val dividerDrawable: Drawable) : RecyclerView.ItemDecoration() {
    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val dividerLeft = parent.paddingLeft
        val dividerRight = parent.width - parent.paddingRight

        val childCount = parent.childCount
        for (i in 0..childCount - 2) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            val dividerTop = child.bottom + params.bottomMargin
            val dividerBottom = dividerTop + dividerDrawable.intrinsicHeight

            dividerDrawable.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom)
            dividerDrawable.draw(canvas)
        }
    }
}
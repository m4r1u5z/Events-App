package com.baddevelopergames.eventsapp.utils

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.baddevelopergames.eventsapp.R
import com.baddevelopergames.eventsapp.eventslist.EventsAdapter

class RecyclerViewBuilder {
    class Builder(private val context: Context) {
        private var view: RecyclerView? = null
        private var adapter: EventsAdapter? = null

        fun with(view: RecyclerView): Builder {
            this.view = view
            return this
        }

        fun setAdapter(adapter: EventsAdapter): Builder {
            this.adapter = adapter
            return this
        }

        fun build() {
            val view = view ?: throw NullPointerException("View cannot be null")
            view.addItemDecoration(getItemDecorator())
            view.adapter = adapter
            view.layoutManager = LinearLayoutManager(context)
            view.setHasFixedSize(true)
            view.itemAnimator = null
        }

        private fun getItemDecorator(): CustomDividerItemDecorator {
            val drawable = ContextCompat.getDrawable(context, R.drawable.item_decorator)
                ?: throw NullPointerException("Item decorator drawable cannot be null")
            return CustomDividerItemDecorator(drawable)
        }
    }
}
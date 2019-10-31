package com.baddevelopergames.eventsapp.eventslist

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.baddevelopergames.eventsapp.R
import com.baddevelopergames.eventsapp.data.model.ActivityItem
import com.baddevelopergames.eventsapp.data.model.ItemType
import com.baddevelopergames.eventsapp.data.model.LectureItem
import com.baddevelopergames.eventsapp.data.model.WorkshopItem
import com.baddevelopergames.eventsapp.data.model.base.EventsAppItem
import com.baddevelopergames.eventsapp.favourites.FavouritesStorage
import com.baddevelopergames.eventsapp.utils.loadFrom
import kotlinx.android.synthetic.main.row_activity.view.*
import kotlinx.android.synthetic.main.row_lecture.view.*
import kotlinx.android.synthetic.main.row_workshop.view.*

class EventsAdapter(
    private val context: Context,
    var list: MutableList<EventsAppItem>,
    private val interactions: EventsInteractions,
    private val favouritesStorage: FavouritesStorage,
    private val resources: Resources,
    private val dateParser: DateParser = DateParser()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dataObserver = object : RecyclerView.AdapterDataObserver() {
        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            super.onItemRangeRemoved(positionStart, itemCount)
            interactions.onDataAmountChanged(list.isEmpty())
        }

        override fun onChanged() {
            super.onChanged()
            interactions.onDataAmountChanged(list.isEmpty())
        }
    }

    init {
        registerAdapterDataObserver(dataObserver)
        interactions.onDataAmountChanged(list.isEmpty())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ItemType.LECTURE.ordinal -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.row_lecture, parent, false)
                LectureViewHolder(view)
            }
            ItemType.WORKSHOP.ordinal -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.row_workshop, parent, false)
                WorkshopViewHolder(view)
            }
            ItemType.ACTIVITY.ordinal -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.row_activity, parent, false)
                ActivityViewHolder(view)
            }
            else -> throw UnsupportedOperationException("Not supported View Type")
        }
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list[position]
        when (getItemViewType(position)) {
            ItemType.LECTURE.ordinal -> {
                (holder as LectureViewHolder).bind(item as LectureItem)
            }
            ItemType.WORKSHOP.ordinal -> {
                (holder as WorkshopViewHolder).bind(item as WorkshopItem)
            }
            ItemType.ACTIVITY.ordinal -> {
                (holder as ActivityViewHolder).bind(item as ActivityItem)
            }
        }
    }

    override fun getItemViewType(position: Int) = list[position].type.ordinal

    inner class LectureViewHolder(itemView: View) : BaseViewHolder<LectureItem>(itemView) {
        override fun bind(item: LectureItem) {
            super.bind(item)
            itemView.lecture_author.text = item.author
            itemView.lecture_description.text = item.description
            itemView.lecture_super_star.setOnClickListener {
                interactions.showMessage(resources.getString(R.string.super_star_lecture_info))
            }
            itemView.lecture_description.setOnClickListener {
                toggle(item)
            }
        }

        override fun additionalItemsVisibility(item: LectureItem, visibility: Int) {
            itemView.lecture_author.visibility = visibility
            itemView.lecture_description.visibility = visibility
            if (item.superStar) {
                itemView.lecture_super_star.visibility = visibility
            }
        }
    }

    inner class WorkshopViewHolder(itemView: View) : BaseViewHolder<WorkshopItem>(itemView) {
        override fun bind(item: WorkshopItem) {
            super.bind(item)
            itemView.workshop_author.text = item.author
            itemView.workshop_author.setOnClickListener {
                toggle(item)
            }
            itemView.workshop_ticket.setOnClickListener {
                ActivitiesUtils.openTicketsUrl(context, item.buyUrl)
            }
        }

        override fun additionalItemsVisibility(item: WorkshopItem, visibility: Int) {
            itemView.workshop_author.visibility = visibility
            itemView.workshop_ticket.visibility = visibility
        }
    }

    inner class ActivityViewHolder(itemView: View) : BaseViewHolder<ActivityItem>(itemView) {
        override fun bind(item: ActivityItem) {
            super.bind(item)
            itemView.activity_location.text = item.address
            itemView.activity_ticket.setOnClickListener {
                ActivitiesUtils.openTicketsUrl(context, item.ticketsUrl)
            }
            itemView.activity_location_icon.setOnClickListener {
                ActivitiesUtils.openMap(context, item.address)
            }
            itemView.activity_facebook.setOnClickListener {
                ActivitiesUtils.openActivityUrl(context, item.facebook)
            }
        }

        override fun additionalItemsVisibility(item: ActivityItem, visibility: Int) {
            itemView.activity_location.visibility = visibility
            itemView.activity_location_icon.visibility = visibility
            itemView.activity_facebook.visibility = visibility
            if (item.ticketsUrl.isNotEmpty()) {
                itemView.activity_ticket.visibility = visibility
            }
        }
    }

    abstract inner class BaseViewHolder<ITEM : EventsAppItem>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var favourite: ImageView = itemView.findViewById(R.id.favourite)
        private var photo: ImageView = itemView.findViewById(R.id.photo)

        open fun bind(item: ITEM) {
            itemView.findViewById<TextView>(R.id.title).text = item.title
            itemView.findViewById<TextView>(R.id.time).text = dateParser.parse(item.timeStart)
            itemView.setOnClickListener {
                toggle(item)
            }
            photo loadFrom item.imageUrl
            photo.setOnClickListener { interactions.onImageClicked(item) }
            if (item.isExpanded) {
                setFavouriteIcon(item, favourite)
                favourite.setOnClickListener {
                    toggleFavourite(item)
                    setFavouriteIcon(item, favourite)
                    interactions.showMessage(getFavouriteActionConfirmationMessage(item))
                    interactions.onRemoveClicked(adapterPosition)
                }
                favourite.visibility = View.VISIBLE
                additionalItemsVisibility(item, View.VISIBLE)
            } else {
                favourite.visibility = View.GONE
                additionalItemsVisibility(item, View.GONE)
            }
        }

        fun toggle(item: ITEM) {
            item.isExpanded = !item.isExpanded
            notifyItemChanged(adapterPosition)
        }

        abstract fun additionalItemsVisibility(item: ITEM, visibility: Int)
    }

    interface EventsInteractions {
        fun showMessage(message: String)

        fun onRemoveClicked(position: Int)

        fun onDataAmountChanged(isEmpty: Boolean)

        fun onImageClicked(item: EventsAppItem)
    }

    fun removeItem(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    private fun toggleFavourite(item: EventsAppItem) {
        if (favouritesStorage.isFavourite(item)) {
            favouritesStorage.removeFavourite(item)
        } else {
            favouritesStorage.addFavourite(item)
        }
    }

    private fun setFavouriteIcon(item: EventsAppItem, view: View) {
        if (favouritesStorage.isFavourite(item)) {
            view.setBackgroundResource(R.drawable.ic_favorite_red_24dp)
        } else {
            view.setBackgroundResource(R.drawable.ic_favorite_border_red_24dp)
        }
    }

    private fun getFavouriteActionConfirmationMessage(item: EventsAppItem): String =
        if (favouritesStorage.isFavourite(item)) {
            resources.getString(R.string.favourites_added)
        } else {
            resources.getString(R.string.favourites_removed)
        }
}
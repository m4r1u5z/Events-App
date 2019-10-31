package com.baddevelopergames.eventsapp.eventslist

import com.baddevelopergames.eventsapp.data.model.base.EventsAppItem

interface EventsAdapterInteractions : EventsAdapter.EventsInteractions {
    override fun showMessage(message: String) {
        // override if needed
    }

    override fun onRemoveClicked(position: Int) {
        // override if needed
    }

    override fun onDataAmountChanged(isEmpty: Boolean) {
        // override if needed
    }

    override fun onImageClicked(item: EventsAppItem) {
        // override if needed
    }
}
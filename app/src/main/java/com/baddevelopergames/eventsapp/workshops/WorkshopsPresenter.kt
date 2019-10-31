package com.baddevelopergames.eventsapp.workshops

import com.baddevelopergames.eventsapp.data.model.ItemType
import com.baddevelopergames.eventsapp.data.model.base.EventsAppItem
import com.baddevelopergames.eventsapp.data.usecase.UseCaseFactory

class WorkshopsPresenter(
    private val view: WorkshopsView,
    private val useCaseFactory: UseCaseFactory
) {
    interface WorkshopsView {
        fun initRecyclerView(list: MutableList<EventsAppItem>)
    }

    fun init() {
        view.initRecyclerView(useCaseFactory.getItemsForTypeUseCase(type = ItemType.WORKSHOP))
    }
}
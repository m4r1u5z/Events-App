package com.baddevelopergames.eventsapp.data.usecase

import com.baddevelopergames.eventsapp.data.entity.toEventsAppItem
import com.baddevelopergames.eventsapp.data.model.AppContent
import com.baddevelopergames.eventsapp.data.model.Error
import com.baddevelopergames.eventsapp.data.model.Result
import com.baddevelopergames.eventsapp.data.model.Success
import com.baddevelopergames.eventsapp.data.model.base.EventsAppItem
import com.baddevelopergames.eventsapp.data.repository.RemoteRepository

class GetAppContentUseCase(private val repository: RemoteRepository) {
    operator fun invoke(): Result<AppContent> {
        val result = repository.getContent()

        if (result is Error) {
            return Error(result.exception)
        }

        val data = (result as Success).data
        val lectures = data.lectures
        val workshops = data.workshops
        val activities = data.activities

        val events = mutableListOf<EventsAppItem>()
            .apply {
                addAll(
                    lectures.map {
                        it.toEventsAppItem()
                    }
                )
                addAll(
                    workshops.map {
                        it.toEventsAppItem()
                    }
                )
                addAll(
                    activities.map {
                        it.toEventsAppItem()
                    }
                )
            }
        return Success(AppContent(events))
    }
}
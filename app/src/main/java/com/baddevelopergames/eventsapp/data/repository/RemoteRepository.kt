package com.baddevelopergames.eventsapp.data.repository

import com.baddevelopergames.eventsapp.data.api.ApiService
import com.baddevelopergames.eventsapp.data.entity.ContentEntity
import com.baddevelopergames.eventsapp.data.model.Error
import com.baddevelopergames.eventsapp.data.model.Result
import com.baddevelopergames.eventsapp.data.model.Success
import kotlinx.coroutines.runBlocking

class RemoteRepository(private val api: ApiService) {
    fun getContent(): Result<ContentEntity> = runBlocking {
        try {
            val response = api.contentAsync().await()
            Success(response)
        } catch (e: Exception) {
            Error(e)
        }
    }
}
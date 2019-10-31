package com.baddevelopergames.eventsapp.data.api

import com.baddevelopergames.eventsapp.data.entity.ContentEntity
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiService {
    @GET("m4r1u5z/dummyproject/master/content.json")
    fun contentAsync(): Deferred<ContentEntity>
}
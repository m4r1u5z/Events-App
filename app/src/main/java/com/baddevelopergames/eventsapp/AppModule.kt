package com.baddevelopergames.eventsapp

import com.baddevelopergames.eventsapp.data.api.ApiFactory
import com.baddevelopergames.eventsapp.data.repository.ItemsRepository
import com.baddevelopergames.eventsapp.data.repository.ItemsRepositoryImpl
import com.baddevelopergames.eventsapp.data.repository.RemoteRepository
import com.baddevelopergames.eventsapp.data.usecase.UseCaseFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    factory {
        @Suppress("USELESS_CAST")
        ItemsRepositoryImpl(androidContext()) as ItemsRepository
    }
    single { ApiFactory.service }
    factory { RemoteRepository(api = get()) }
    factory { UseCaseFactory(repository = get(), localRepository = get()) }
}
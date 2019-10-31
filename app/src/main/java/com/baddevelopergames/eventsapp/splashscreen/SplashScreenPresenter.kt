package com.baddevelopergames.eventsapp.splashscreen

import com.baddevelopergames.eventsapp.data.Items
import com.baddevelopergames.eventsapp.data.model.Error
import com.baddevelopergames.eventsapp.data.model.Success
import com.baddevelopergames.eventsapp.data.usecase.UseCaseFactory

class SplashScreenPresenter(
    private val view: SplashScreenView,
    private val useCaseFactory: UseCaseFactory
) {
    fun fetchContent() {
        val result = useCaseFactory.getLecturesUseCase()

        if (result is Error) {
            view.showError(result.message)
            return
        }

        val data = (result as Success).data
        Items.items = data.events
        view.openMainActivity()
    }
}
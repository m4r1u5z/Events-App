package com.baddevelopergames.eventsapp.splashscreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.baddevelopergames.eventsapp.R
import com.baddevelopergames.eventsapp.main.MainActivity
import com.baddevelopergames.eventsapp.utils.SnackbarHelper
import com.baddevelopergames.eventsapp.utils.injectActivity
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity(), SplashScreenView {
    private val presenter: SplashScreenPresenter by injectActivity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        presenter.fetchContent()
    }

    override fun openMainActivity() {
        MainActivity.navigateTo(this)
    }

    override fun showError(message: String) {
        SnackbarHelper.showSnackbar(snackbar_container, message, getString(R.string.splash_screen_retry)) {
            presenter.fetchContent()
        }
    }
}
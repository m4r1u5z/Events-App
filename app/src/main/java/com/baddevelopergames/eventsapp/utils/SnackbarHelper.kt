package com.baddevelopergames.eventsapp.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

class SnackbarHelper {
    companion object {
        fun showSnackbar(view: View, message: String) {
            Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
        }

        fun showSnackbar(view: View, message: String, actionText: String, action: () -> (Unit)) {
            val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE)
            snackbar.setAction(actionText) {
                action()
            }
            snackbar.show()
        }
    }
}
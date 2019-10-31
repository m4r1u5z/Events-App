package com.baddevelopergames.eventsapp.eventslist

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.content.pm.PackageManager
import android.net.Uri

class ActivitiesUtils {
    companion object {
        fun openActivityUrl(context: Context, url: String) {
            try {
                context.startActivity(
                    getActivityFbUrl(
                        context,
                        url
                    )
                )
            } catch (ignored: ActivityNotFoundException) {
                // no-op
            }
        }

        private fun getActivityFbUrl(context: Context, url: String): Intent {
            var uri = Uri.parse(url)
            try {
                val applicationInfo = context.packageManager.getApplicationInfo("com.facebook.katana", 0)
                if (applicationInfo.enabled) {
                    uri = Uri.parse("fb://facewebmodal/f?href=$url")
                }
            } catch (ignored: PackageManager.NameNotFoundException) {
                // no-op
            }
            return Intent(Intent.ACTION_VIEW, uri).apply {
                flags = FLAG_ACTIVITY_NEW_TASK
            }
        }

        fun openTicketsUrl(context: Context, url: String) {
            try {
                context.startActivity(getUrlIntent(url))
            } catch (ignored: ActivityNotFoundException) {
                // no-op
            }
        }

        private fun getUrlIntent(url: String): Intent {
            val uri = Uri.parse(url)
            return Intent(Intent.ACTION_VIEW, uri).apply {
                flags = FLAG_ACTIVITY_NEW_TASK
            }
        }

        fun openMap(context: Context, address: String) {
            val intent = getMapIntent(address)
            if (intent.resolveActivity(context.packageManager) != null) {
                try {
                    context.startActivity(intent)
                } catch (ignoted: ActivityNotFoundException) {
                    // no-op
                }
            }
        }

        private fun getMapIntent(address: String): Intent {
            val gmmIntentUri = Uri.parse("geo:0,0?q=$address")
            return Intent(Intent.ACTION_VIEW, gmmIntentUri).apply {
                `package` = "com.google.android.apps.maps"
                flags = FLAG_ACTIVITY_NEW_TASK
            }
        }
    }
}
package com.arsoft.arp.app

import android.app.Application
import com.arsoft.arp.helpers.Prefs
import dagger.hilt.android.HiltAndroidApp

val prefs: Prefs by lazy {
    App.prefs!!
}

@HiltAndroidApp
class App: Application() {
    companion object {
        var prefs: Prefs? = null
    }

}
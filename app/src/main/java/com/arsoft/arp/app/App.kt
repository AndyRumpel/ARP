package com.arsoft.arp.app

import android.app.Application
import com.arsoft.arp.helpers.Prefs

val prefs: Prefs by lazy {
    App.prefs!!
}

class App: Application() {

    companion object {
        var prefs: Prefs? = null
    }
}
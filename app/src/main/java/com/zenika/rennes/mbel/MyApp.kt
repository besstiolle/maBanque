package com.zenika.rennes.mbel

import android.app.Application
import android.os.SystemClock


/**
 * Override la configuration par défaut (comme Spring boot le ferait)
 */
class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        SystemClock.sleep(2000)
    }
}
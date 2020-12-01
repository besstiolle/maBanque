package com.zenika.rennes.mbel

import android.app.Application
import android.os.SystemClock




class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        SystemClock.sleep(2000)
    }
}
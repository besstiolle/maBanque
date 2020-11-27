package com.zenika.rennes.nettracker

import android.content.Context

interface NetMeter {

    abstract fun isOnline(context: Context): Boolean;
}
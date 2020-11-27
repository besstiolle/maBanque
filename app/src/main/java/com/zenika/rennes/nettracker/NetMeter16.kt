package com.zenika.rennes.nettracker

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class NetMeter16 : NetMeter {

    /**
     * Appel API compatible API 23. Current targeted max version : 16
     */
    override fun isOnline(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        return isConnected
    }
}
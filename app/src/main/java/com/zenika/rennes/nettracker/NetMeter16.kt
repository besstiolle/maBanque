package com.zenika.rennes.nettracker

import android.content.Context
import android.net.ConnectivityManager

@Suppress("DEPRECATION")
class NetMeter16 : NetMeter {

    /**
     * Appel API compatible API 23. Current targeted max version : 16
     */
    override fun isOnline(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: android.net.NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

    override fun getCapabilities(context: Context): MutableCollection<String> {

        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        val values = mutableListOf<String>()

        if (activeNetwork != null) {
            if(activeNetwork.type == ConnectivityManager.TYPE_MOBILE) {
                values.add("CELLULAR")
            }
            if(activeNetwork.type == ConnectivityManager.TYPE_WIFI) {
                values.add("WIFI")
            }
            if(activeNetwork.type == ConnectivityManager.TYPE_ETHERNET) {
                values.add("ETHERNET")
            }
        }

        return values
    }
}
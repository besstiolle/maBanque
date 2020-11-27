package com.zenika.rennes.nettracker

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi

class NetMeter23 : NetMeter {

    /**
     * Version minimal : API 23. Current targeted min version : 16
     */
    @RequiresApi(Build.VERSION_CODES.M)
    override fun isOnline(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
        }

        return false
    }


    /**
     * Version minimal : API 23. Current targeted min version : 16
     */
    @RequiresApi(Build.VERSION_CODES.M)
    override fun getCapabilities(context: Context): MutableCollection<String> {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        val values = mutableListOf<String>()
        if (capabilities != null) {

            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                values.add("CELLULAR")
            }

            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                values.add("WIFI")
            }

            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                values.add("ETHERNET")
            }
        }

        return values
    }
}
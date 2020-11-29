package com.zenika.rennes.nettracker

import android.content.Context
import android.net.ConnectivityManager
import android.net.wifi.WifiManager


@Suppress("DEPRECATION")
/*
 * Implementation of NetMeter for API 16 -> 22
 */
open class NetMeter16 : NetMeter {

    /**
     * Appel API compatible API 23. Current targeted max version : 16
     */
    override fun isOnline(context: Context): Boolean {
        val connectivityManager = context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: android.net.NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

    override fun getCapabilities(context: Context): MutableCollection<CharSequence> {

        return mutableListOf("Need API 21+23")

        /*
        val connectivityManager = context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        val values = mutableListOf<CharSequence>()
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

        return values*/
    }

    override fun getCurrentNetwork(context: Context): CharSequence {

        val connectivityManager = context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo

        return when (activeNetwork?.type) {
            ConnectivityManager.TYPE_MOBILE -> {
                "CELLULAR"
            }
            ConnectivityManager.TYPE_WIFI -> {
                "WIFI"
            }
            ConnectivityManager.TYPE_ETHERNET -> {
                "ETHERNET"
            }
            else -> {
                "N/A"
            }
        }
    }

    override fun getSignalStrength(context: Context): CharSequence{

        val currentNetwork: CharSequence = this.getCurrentNetwork(context)

        if(currentNetwork == "WIFI"){
            val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

            val numberOfLevels = 5
            // Deprecated in API 30
            return when (WifiManager.calculateSignalLevel(wifiManager.connectionInfo.rssi, numberOfLevels)) {
                0 -> "none"
                1 -> "Poor"
                2 -> "Mediocre"
                3 -> "Average"
                4 -> "Good"
                else -> "N/A"
            }
        } else if(this.getCurrentNetwork(context) == "CELLULAR"){
            return "Cellular Need API 17+"
        }

        return "CurrentNetwork $currentNetwork not implemented"
    }
}
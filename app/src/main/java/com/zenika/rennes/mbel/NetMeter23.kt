package com.zenika.rennes.mbel

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.wifi.WifiManager
import android.os.Build
import androidx.annotation.RequiresApi

/*
 * Implementation of NetMeter for API 23 -> 30+
 * Cause of usage "connectivityManager.activeNetwork" (API 23)
 * Cause of usage "connectivityManager.getNetworkCapabilities" (API 21)
 * Cause of usage "capabilities.hasTransport" (API 21)
 */
@RequiresApi(Build.VERSION_CODES.M)
open class NetMeter23: NetMeter {


    override fun isOnline(context: Context): Boolean {
        val connectivityManager = context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
        }

        return false
    }

    override fun getCapabilities(context: Context): MutableCollection<CharSequence> {
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

        return values
    }
    override fun getCurrentNetwork(context: Context): CharSequence {

        /*val connectivityManager = context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager?.run {
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.run {
                when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return "WIFI"
                    }
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return "CELLULAR"
                    }
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return "ETHERNET"
                    }
                    else -> {
                        "N/A"
                    }
                }
            }
        }*/
        return "Not Implemented"

    }

    override fun getSignalStrength(context: Context): CharSequence{
        //val currentNetwork: CharSequence = this.getCurrentNetwork(context);
        //FIXME
        val currentNetwork: CharSequence = this.getCapabilities(context).joinToString();

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
        } else if(currentNetwork == "CELLULAR"){

            return "Cellular Need big brain time"
        }

        return "CurrentNetwork $currentNetwork not implemented"
    }


}
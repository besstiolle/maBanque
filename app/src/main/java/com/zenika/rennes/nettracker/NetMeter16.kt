package com.zenika.rennes.nettracker

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import androidx.annotation.RequiresApi

@Suppress("DEPRECATION")
/*
 * Implementation of NetMeter for API 16 -> 22
 */
class NetMeter16 : NetMeter {

    /**
     * Appel API compatible API 23. Current targeted max version : 16
     */
    override fun isOnline(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: android.net.NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

    override fun getCapabilities(context: Context): MutableCollection<CharSequence> {

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

       // val activeNetwork = connectivityManager.activeNetworkInfo
        val values = mutableListOf<CharSequence>("Not Implemented")
/*
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
        }*/

        return values
    }

    override fun getCurrentNetwork(context: Context): CharSequence {

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
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

    override fun getSignalStrength(applicationContext: Context?): CharSequence{
        return "To_IMPLEMENTS"
    }
}
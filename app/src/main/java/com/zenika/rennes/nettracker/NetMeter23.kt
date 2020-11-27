package com.zenika.rennes.nettracker

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi

/*
 * Implementation of NetMeter for API 23 -> 30+
 * Cause of usage "connectivityManager.activeNetwork" (API 23)
 * Cause of usage "connectivityManager.getNetworkCapabilities" (API 21)
 * Cause of usage "capabilities.hasTransport" (API 21)
 */
@RequiresApi(Build.VERSION_CODES.M)
class NetMeter23 : NetMeter {

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

    override fun getCapabilities(context: Context): MutableCollection<CharSequence> {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
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

    override fun isMetered(context: Context): Boolean {
        return true;
    }

    override fun getSignalStrength(applicationContext: Context?): CharSequence{
        return "To_IMPLEMENTS"
    }
}
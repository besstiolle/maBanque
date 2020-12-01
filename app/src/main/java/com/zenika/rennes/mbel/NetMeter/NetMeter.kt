package com.zenika.rennes.mbel.NetMeter

import android.content.Context
import android.net.ConnectivityManager

interface NetMeter {

    /**
     * Return true if connexion to internet is enabled
     */
    fun isOnline(context: Context): Boolean

    /**
     * @return MutableCollection<String> of type of capabilities
     *
     */
    fun getCapabilities(context: Context): MutableCollection<CharSequence>

    /**
     * @Return current type of Network or CharSequence "N/A"
     */
    fun getCurrentNetwork(context: Context): CharSequence

    /**
     * Return true if connectivity is metered
     */
    fun isMetered(context: Context): Boolean {
        val connectivityManager = context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.isActiveNetworkMetered
    }

    /**
     * Return true if connectivity is metered
     */
    fun getSignalStrength(context: Context): CharSequence
}
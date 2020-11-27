package com.zenika.rennes.nettracker

import android.content.Context
import android.net.ConnectivityManager

interface NetMeter {

    /**
     * Return true if connexion to internet is enabled
     */
    abstract fun isOnline(context: Context): Boolean

    /**
     * @return MutableCollection<String> of type of capabilities
     *
     */
    abstract fun getCapabilities(context: Context): MutableCollection<CharSequence>

    /**
     * @Return current type of Network or CharSequence "N/A"
     */
    abstract fun getCurrentNetwork(context: Context): CharSequence

    /**
     * Return true if connectivity is metered
     */
    fun isMetered(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.isActiveNetworkMetered
    }

    /**
     * Return true if connectivity is metered
     */
    abstract fun getSignalStrength(applicationContext: Context?): CharSequence
}
package com.zenika.rennes.nettracker

import android.content.Context

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
     * Return true if connectivity is metered
     */
    abstract fun isMetered(context: Context): Boolean

    /**
     * Return true if connectivity is metered
     */
    abstract fun getSignalStrength(applicationContext: Context?): CharSequence
}
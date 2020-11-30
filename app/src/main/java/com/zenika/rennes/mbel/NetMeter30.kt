package com.zenika.rennes.mbel

import android.content.Context
import android.net.wifi.WifiManager
import android.os.Build
import androidx.annotation.RequiresApi
import kotlin.math.ceil


/*
 * Implementation of NetMeter for API 30+
 * Cause of usage "WifiManager.calculateSignalLevel(rssi)" (API 30)
 */
@RequiresApi(Build.VERSION_CODES.R)
class NetMeter30 : NetMeter23(), NetMeter {

    override fun getSignalStrength(context: Context): CharSequence{
        //val currentNetwork: CharSequence = this.getCurrentNetwork(context)
        //FIXME
        val currentNetwork: CharSequence = super.getCapabilities(context).joinToString()

        if(currentNetwork == "WIFI"){
            val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

            val numberOfLevels = 5

            val level = wifiManager.calculateSignalLevel(33)
            val maxLevel = wifiManager.maxSignalLevel

            return when (ceil((level / maxLevel * numberOfLevels - 1).toDouble())) {
                0.0 -> "none"
                1.0 -> "Poor"
                2.0 -> "Mediocre"
                3.0 -> "Average"
                4.0 -> "Good"
                else -> "N/A"
            }
        } else if(currentNetwork == "CELLULAR"){

            return super.getSignalStrength(context)
        }

        return super.getSignalStrength(context)
    }

}
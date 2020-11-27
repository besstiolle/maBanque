package com.zenika.rennes.nettracker

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var netMeter: NetMeter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stand_by)

        // Initiate netMeter instance
        initNetMeter()

        checkIsOnline()

        //Plug button click event
        val button: Button = findViewById(R.id.button_refresh)
        button.setOnClickListener {
            checkIsOnline()
        }

    }

    private fun initNetMeter(){
        /* Make sure we're running on Marshmallow or higher to use NetworkCapabilities APIs */
        netMeter = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setContentView(R.layout.activity_main_23)
            NetMeter23()
        } else {
            setContentView(R.layout.activity_main_16)
            NetMeter16()
        }
    }

    private fun checkIsOnline(){
        val sdkVersion: Int = Build.VERSION.SDK_INT

        val onLine: Boolean = netMeter!!.isOnline(applicationContext)

        val capabilities: String = netMeter!!.getCapabilities(applicationContext).joinToString()

        val isMetered: Boolean = netMeter!!.isMetered(applicationContext)

        val strength: CharSequence = netMeter!!.getSignalStrength(applicationContext)


        updateRapport("Results : \n" +
                "> SDK Version : $sdkVersion \n" +
                "> Access to Internet :  $onLine \n" +
                "> Type :  $capabilities \n" +
                "> isMetered : $isMetered \n" +
                "> Signal Strength : $strength")
    }

    private fun updateRapport(textParam: CharSequence){
        val textView = findViewById<View>(R.id.rapport) as TextView
        textView.text = textParam
    }
}
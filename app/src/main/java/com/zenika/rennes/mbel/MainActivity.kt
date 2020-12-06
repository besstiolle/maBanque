package com.zenika.rennes.mbel

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.zenika.rennes.mbel.NetMeter.NetMeter
import com.zenika.rennes.mbel.NetMeter.NetMeter16
import com.zenika.rennes.mbel.NetMeter.NetMeter23
import com.zenika.rennes.mbel.NetMeter.NetMeter30
import com.zenika.rennes.mbel.api.model.ArticleApiServiceImpl
import com.zenika.rennes.mbel.api.service.ApiSingleton


class MainActivity : AppCompatActivity() {

    //SDK 16 by default
    private var netMeter: NetMeter = NetMeter16()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main_all)

        // Initiate netMeter instance
        initNetMeter()

        checkIsOnline()

        //Plug button click event
        val button: Button = findViewById(R.id.button_refresh)
        button.setOnClickListener {
            checkIsOnline()
            retrieveApi()
        }

        //Populate list with data from API
        retrieveApi()

    }

    private fun retrieveApi() {
        // Initiate Singleton to retrieve easily the api.
        // Pass some parameters like the layout to rendering : 2 or 3 datas ?
        ApiSingleton.init(applicationContext, findViewById<View>(R.id.listView) as ListView, R.layout.article_3_points)
        // Call the Api getAllArticles.
        ArticleApiServiceImpl().getAllArticles()
    }

    private fun initNetMeter(){
        /* Make sure we're running on Marshmallow or higher to use NetworkCapabilities APIs */
        netMeter = when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.R -> NetMeter30()
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> NetMeter23()
            else -> NetMeter16()
        }
    }

    /**
     * Initiate news layout with/out image if we are in wifi or not
     *//*
    private fun initNewsLayout(){
        layout = when{
            foo -> R.layout.article_3_points
            foo -> R.layout.article_2_points
        }
    }*/



    private fun checkIsOnline(){
        val sdkVersion: Int = Build.VERSION.SDK_INT

        val onLine: Boolean = netMeter.isOnline(applicationContext)

        val currentNetwork: CharSequence = netMeter.getCurrentNetwork(applicationContext)

        val capabilities: String = netMeter.getCapabilities(applicationContext).joinToString()

        val isMetered: Boolean = netMeter.isMetered(applicationContext)

        val strength: CharSequence = netMeter.getSignalStrength(applicationContext)


        updateRapport(
            "Results : \n" +
                    "> SDK Version : $sdkVersion \n" +
                    "> Access to Internet :  $onLine \n" +
                    "> Current Co. :  $currentNetwork \n" +
                    "> Co. available :  $capabilities \n" +
                    "> isMetered : $isMetered \n" +
                    "> Signal Strength : $strength"
        )
    }

    private fun updateRapport(textParam: CharSequence){
        val textView = findViewById<View>(R.id.rapport) as TextView
        textView.text = textParam
    }
}

package com.zenika.rennes.mbel

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import com.zenika.rennes.mbel.NetMeter.NetMeter
import com.zenika.rennes.mbel.NetMeter.NetMeter16
import com.zenika.rennes.mbel.NetMeter.NetMeter23
import com.zenika.rennes.mbel.NetMeter.NetMeter30
import com.zenika.rennes.mbel.api.model.ArticleApiServiceImpl
import com.zenika.rennes.mbel.api.service.ApiSingleton
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable


class MainActivity : AppCompatActivity() {

    //SDK 16 by default
    private var netMeter: NetMeter = NetMeter16()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // SDK 16 View by default
    //    setContentView(R.layout.activity_main_16)

        setContentView(R.layout.activity_main_23)
        val listView = findViewById<View>(R.id.listView) as ListView
        ApiSingleton.init(applicationContext, listView)


        var service = ArticleApiServiceImpl()
        service.getAllArticles()

/*
        var i=0
        while(ApiSingleton.datas == null) {
            Log.e("WAIT", "$i")
            i++
        }
        val listView = findViewById<View>(R.id.listView) as ListView
        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            applicationContext, android.R.layout.simple_list_item_1, ApiSingleton.datas!!
        )
        listView.adapter = arrayAdapter*/
/*
        // Initiate netMeter instance
        initNetMeter()

        checkIsOnline()

        //Plug button click event
        val button: Button = findViewById(R.id.button_refresh)
        button.setOnClickListener {
            checkIsOnline()
        }*/

    }

    private fun initNetMeter(){
        /* Make sure we're running on Marshmallow or higher to use NetworkCapabilities APIs */
        netMeter = when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.R -> {
                setContentView(R.layout.activity_main_23)
                NetMeter30()
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                setContentView(R.layout.activity_main_23)
                NetMeter23()
            }
            else -> {
                setContentView(R.layout.activity_main_16)
                NetMeter16()
            }
        }
    }



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

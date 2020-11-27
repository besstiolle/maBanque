package com.zenika.rennes.nettracker

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //newText("is Online + ")
        checkIsOnline()
    }

    private fun newText(textParam: CharSequence){

        val textView = findViewById<View>(R.id.textView3) as TextView
        textView.text = textParam
    }

    private fun checkIsOnline(){
        // Make sure we're running on Marshmallow or higher to use NetworkCapabilities APIs
        val state: CharSequence
        val netMeter : NetMeter = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            state = "UP"
            NetMeter23()
        } else {
            state = "BOTTOM"
            NetMeter16()
        }

        val onLine: Boolean = netMeter.isOnline(applicationContext)
        newText("Result is : $state $onLine")
    }
}
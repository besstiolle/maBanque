package com.zenika.rennes.mbel.api.service

import android.content.Context
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView

object ApiSingleton {

    init{
        Log.e("ApiSingleton", "Singleton class invoked")
    }

    var datas: Array<String>? = null
    var applicationContext: Context?= null
    var listView: ListView? = null

    fun init(context: Context, view: ListView){
        applicationContext = context
        listView = view
    }

    fun process(){
        if(datas == null || applicationContext == null || listView == null){
            Log.e("KEVIN","value Null")
            return
        }
        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            applicationContext!!, android.R.layout.simple_list_item_1, datas!!
        )
        listView!!.adapter = arrayAdapter
    }

}
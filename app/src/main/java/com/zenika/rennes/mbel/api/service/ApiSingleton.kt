package com.zenika.rennes.mbel.api.service

import android.content.Context
import android.util.Log
import android.widget.ListView
import com.zenika.rennes.mbel.api.model.Article
import com.zenika.rennes.mbel.api.model.ArticleArrayAdapter

object ApiSingleton {

    init{
        Log.e("ApiSingleton", "Singleton class invoked")
    }

    var applicationContext: Context?= null
    var listView: ListView? = null

    fun init(context: Context, view: ListView){
        applicationContext = context
        listView = view
    }

    fun process(articles: List<Article>){
        if(articles == null || applicationContext == null || listView == null){
            Log.e("KEVIN", "int wasn't made Null")
            return
        }
        listView!!.adapter = ArticleArrayAdapter(applicationContext!!, articles)
    }

}
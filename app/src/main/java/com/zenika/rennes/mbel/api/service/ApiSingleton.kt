package com.zenika.rennes.mbel.api.service

import android.content.Context
import android.util.Log
import android.widget.ListView
import com.zenika.rennes.mbel.api.model.ArticleArrayWithImageAdapter
import com.zenika.rennes.mbel.api.model.ArticleArrayWithoutImageAdapter
import com.zenika.rennes.mbel.api.model.ArticlesDataChildren
import kotlin.properties.Delegates

object ApiSingleton {

    init{
        Log.e("ApiSingleton", "Singleton class invoked")
    }

    private lateinit var applicationContext: Context
    private lateinit var listView: ListView
    private var isWifi by Delegates.notNull<Boolean>()

    fun init(c: Context, v: ListView, w: Boolean){
        applicationContext = c
        listView = v
        isWifi = w
    }

    fun process(articles: List<ArticlesDataChildren>){
        if(isWifi) listView.adapter = ArticleArrayWithImageAdapter(applicationContext, articles)
        else listView.adapter = ArticleArrayWithoutImageAdapter(applicationContext, articles)
    }

}


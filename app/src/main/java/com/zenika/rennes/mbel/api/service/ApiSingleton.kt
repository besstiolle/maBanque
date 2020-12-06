package com.zenika.rennes.mbel.api.service

import android.content.Context
import android.util.Log
import android.widget.ListView
import com.zenika.rennes.mbel.api.model.Article
import com.zenika.rennes.mbel.api.model.ArticleArrayAdapter
import kotlin.properties.Delegates

object ApiSingleton {

    init{
        Log.e("ApiSingleton", "Singleton class invoked")
    }

    private lateinit var applicationContext: Context
    private lateinit var listView: ListView
    private var layout by Delegates.notNull<Int>()

    fun init(c: Context, v: ListView, l: Int){
        applicationContext = c
        listView = v
        layout = l
    }

    fun process(articles: List<Article>){
        listView.adapter = ArticleArrayAdapter(applicationContext, articles, layout)
    }

}
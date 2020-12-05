package com.zenika.rennes.mbel.api.model

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import com.zenika.rennes.mbel.api.service.ApiService
import com.zenika.rennes.mbel.api.service.ApiSingleton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class ArticleApiServiceImpl() {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://mabanqueenligne.free.beeceptor.com/")
        .addConverterFactory(MoshiConverterFactory.create())
       // .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    fun getSingleArticle(){
        val service = retrofit.create(ApiService::class.java)
        val serviceCall: Call<Article> = service.getSingleArticle()
        serviceCall.enqueue(object : Callback<Article> {
            override fun onResponse(call: Call<Article>, response: Response<Article>) {
                val a = response.body()
                if (a != null) {
                    Log.e("KEVIN", "HERE is ONE ARTICLE:")
                    Log.e("KEVIN", " one article : ${a.title} : ${a.description} ")
                }

            }

            override fun onFailure(call: Call<Article>, t: Throwable) {
                error("KO")
            }
        })

    }

    fun getAllArticles(){
        val service = retrofit.create(ApiService::class.java)
        val serviceCall: Call<ArticlesWrapper> = service.getAllArticles()
        val mHandler = Handler(Looper.getMainLooper())

        serviceCall.enqueue(object : Callback<ArticlesWrapper> {
            override fun onResponse(call: Call<ArticlesWrapper>,response: Response<ArticlesWrapper>) {
                val articlesWrapper = response.body()
                if (articlesWrapper != null) {
                    Log.e("KEVIN", "HERE is ALL ARTICLES:")
                    val results = mutableListOf<String>()
                    for (a in articlesWrapper.articles) {
                        Log.e("KEVIN", " one article : ${a.title} : ${a.description} ")
                        a.title?.let { results.add(it) }
                    }
                    ApiSingleton.datas = results.toTypedArray()
                    ApiSingleton.process()
                }

               /* mHandler.post {Runnable() {
                    run() {
                        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
                            context, android.R.layout.simple_list_item_1, arrayOf<String>(
                                "boo",
                                "bee"
                            )
                        )
                        listView.adapter = arrayAdapter
                    }
                }*/





            }

            override fun onFailure(call: Call<ArticlesWrapper>, t: Throwable) {
                error("KO")
            }
        })

    }
}
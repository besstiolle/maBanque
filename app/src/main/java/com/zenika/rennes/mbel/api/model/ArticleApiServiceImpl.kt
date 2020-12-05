package com.zenika.rennes.mbel.api.model

import android.os.Handler
import android.os.Looper
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
        .build()

    fun getAllArticles(){
        val service = retrofit.create(ApiService::class.java)
        val serviceCall: Call<ArticlesWrapper> = service.getAllArticles()
        val mHandler = Handler(Looper.getMainLooper())

        serviceCall.enqueue(object : Callback<ArticlesWrapper> {
            override fun onResponse(call: Call<ArticlesWrapper>,response: Response<ArticlesWrapper>) {
                val articlesWrapper = response.body()
                if (articlesWrapper != null) {
                    ApiSingleton.process(articlesWrapper.articles)
                }
            }

            override fun onFailure(call: Call<ArticlesWrapper>, t: Throwable) {
                error("KO")
            }
        })

    }
}
package com.zenika.rennes.mbel.api.model

import android.util.Log
import com.zenika.rennes.mbel.api.service.ApiService
import com.zenika.rennes.mbel.api.service.ApiSingleton
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


class ArticleApiServiceImpl {

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(10, TimeUnit.SECONDS)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://www.reddit.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient)
        .build()

    fun getAllArticles(){
        val service = retrofit.create(ApiService::class.java)
        val serviceCall: Call<ArticlesWrapper> = service.getAllArticles()

        serviceCall.enqueue(object : Callback<ArticlesWrapper> {
            override fun onResponse(
                call: Call<ArticlesWrapper>,
                response: Response<ArticlesWrapper>
            ) {
                val articlesWrapper = response.body()
                if (articlesWrapper != null) {
                    ApiSingleton.process(articlesWrapper.data.children)
                }
            }

            override fun onFailure(call: Call<ArticlesWrapper>, t: Throwable) {
                Log.e("ArticleApiServiceImpl", "Exception : " + t.message)
                t.printStackTrace()
            }
        })

    }
}
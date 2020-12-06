package com.zenika.rennes.mbel.api.service

import com.zenika.rennes.mbel.api.model.ArticlesWrapper
import retrofit2.Call
import retrofit2.http.GET


interface ApiService {

    @GET("r/finance/new.json")
    fun getAllArticles(): Call<ArticlesWrapper>
}
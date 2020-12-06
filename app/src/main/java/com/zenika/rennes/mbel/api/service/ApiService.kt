package com.zenika.rennes.mbel.api.service

import com.zenika.rennes.mbel.api.model.Article
import com.zenika.rennes.mbel.api.model.ArticlesWrapper
import retrofit2.Call
import retrofit2.http.GET


interface ApiService {

    @GET("v1/single")
    fun getSingleArticle(): Call<Article>


    @GET("v1/news")
    fun getAllArticles(): Call<ArticlesWrapper>
}
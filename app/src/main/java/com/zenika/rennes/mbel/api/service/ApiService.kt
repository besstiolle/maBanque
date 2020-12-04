package com.zenika.rennes.mbel.api.service

import com.zenika.rennes.mbel.api.model.Article
import io.reactivex.Observable
import retrofit2.http.GET


interface ApiService {

    @GET("v1/single")
    fun getArticle(): Observable<Article>
}
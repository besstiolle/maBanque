package com.zenika.rennes.mbel.api.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Article {
    @Expose
    @SerializedName("author")
    private val author: String? = null

    @Expose
    @SerializedName("title")
    private val title: String? = null

    @Expose
    @SerializedName("description")
    private val description: String? = null

    @Expose
    @SerializedName("urlToImage")
    private val urlToImage: String? = null
}
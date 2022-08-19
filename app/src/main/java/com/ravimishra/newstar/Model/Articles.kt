package com.ravimishra.newstar.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Articles {
    @SerializedName("source")
    @Expose
    var source: Source? = null

    @SerializedName("publishedAt")
    @Expose
    var date: String? = null

    @SerializedName("author")
    @Expose
    var author: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("urlToImage")
    @Expose
    var urlToImage: String? = null
}
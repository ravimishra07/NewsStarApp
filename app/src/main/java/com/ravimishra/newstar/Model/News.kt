package com.ravimishra.newstar.Model

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import com.ravimishra.newstar.Model.Articles

class News(
    @field:Expose @field:SerializedName("status") var status: String,
    @field:Expose @field:SerializedName(
        "totalResult"
    ) var totalResult: String,
    @field:Expose @field:SerializedName("articles") var articles: List<Articles>
)
package com.ravimishra.newstar.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ModelNews(
    @field:Expose @field:SerializedName("title") var titlee: String,
    @field:Expose @field:SerializedName(
        "description"
    ) var desc: String,
    @field:Expose @field:SerializedName("urlToImage") var url: String
)
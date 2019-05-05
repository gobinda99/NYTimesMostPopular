package com.nytimes.sample.data.api

import com.google.gson.annotations.SerializedName
import com.nytimes.sample.data.model.News

data class Response(
    val status : String?,
    val numResults: Int?,
    val results : List<News>?
)

package com.nytimes.sample.data.api

import com.nytimes.sample.data.model.News

data class NewsResponse(
    val status : String?,
    val numResults: Int?,
    val results : List<News>?
)

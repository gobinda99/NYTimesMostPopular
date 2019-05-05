package com.nytimes.sample.data.api.response

import com.google.gson.annotations.SerializedName
import com.nytimes.sample.data.model.News

data class NewsResponse(

    val status : String,
    @SerializedName("num_results")
    val numResults: Int,
    val results : List<News>

)

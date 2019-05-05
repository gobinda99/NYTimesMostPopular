package com.nytimes.sample.data.model

import android.icu.text.MeasureFormat
import com.google.gson.annotations.SerializedName


data class News ( val id : Long,
                  val url: String,
                  @SerializedName("adx_keywords")
                  val adxKeywords: String,
                  val column: String,
                  val byline: String,
                  val type: String,
                  val title: String,
                  val abstract: String,
                  val published_date: String,
                  val source: String,
                  val asset_id : Long,
                  val views : Int,
//                  val des_facet: List<String>,
//                  val org_facet: String,
//                  val per_facet: String,
//                  val geo_facet: String,
                  val media: List<Media>,
                  val uri: String

)

data class Media(val type : String,
                 val subtype: String,
                 val caption: String,
                 val copyright: String,
                 val approved_for_syndication: String,
                 @SerializedName("media-metadata")
                 val mediaMetadata: List<MediaMetaData>)

data class MediaMetaData(val url: String,
                         val format: String,
                         val height: Int,
                         val width: Int)


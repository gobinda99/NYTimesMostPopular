package com.nytimes.sample.data.model

import android.os.Parcelable
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.nytimes.sample.util.EmptyToNull
import kotlinx.android.parcel.Parcelize

@Parcelize
data class News(
    val id: Long?,
    val url: String?,
    val adxKeywords: String?,
    val column: String?,
    val byline: String?,
    val type: String?,
    val title: String?,
    val abstract: String?,
    val publishedDate: String?,
    val source: String?,
    val assetId: Long?,
    val views: Int?,
    @JsonAdapter(EmptyToNull::class)
    val desFacet: List<String>?,
    @JsonAdapter(EmptyToNull::class)
    val orgFacet: List<String>?,
    @JsonAdapter(EmptyToNull::class)
    val perFacet: List<String>?,
    @JsonAdapter(EmptyToNull::class)
    val geoFacet: List<String>?,
    val media: List<Media>?,
    val uri: String?

) : Parcelable


@Parcelize
data class Media(
    val type: String?,
    val subtype: String?,
    val caption: String?,
    val copyright: String?,
    val approved_for_syndication: String?,
    @SerializedName("media-metadata")
    val mediaMetadata: List<MediaMetaData>
) : Parcelable


@Parcelize
data class MediaMetaData(
    val url: String?,
    val format: String?,
    val height: Int?,
    val width: Int?
) : Parcelable


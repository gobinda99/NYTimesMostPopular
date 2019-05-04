package com.nytimes.sample.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "flight_events")
data class Data(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @Transient
    val id : Int,

    @ColumnInfo(name = "flight_no")
    @SerializedName("Date")
    val flightNo: String?)
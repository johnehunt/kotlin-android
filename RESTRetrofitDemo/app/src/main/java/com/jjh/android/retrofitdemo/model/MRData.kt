package com.jjh.android.retrofitdemo.model

import com.google.gson.annotations.SerializedName

data class MRData(@SerializedName("xmlns") val xmlns: String,
                  @SerializedName("series") val series: String,
                  @SerializedName("url")  val url: String,
                  @SerializedName("limit") val limit: String,
                  @SerializedName("offset") val offset: String,
                  @SerializedName("total") val total: String,
                  @SerializedName("DriverTable") val driverTable: DriverTable)

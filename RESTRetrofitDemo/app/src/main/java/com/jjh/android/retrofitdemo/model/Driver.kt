package com.jjh.android.retrofitdemo.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Driver(@SerializedName("driverId") val driverId: String,
                  @SerializedName("permanentNumber") val permanentNumber: String,
                  @SerializedName("code") val code: String,
                  @SerializedName("url") val url: String,
                  @SerializedName("givenName") val givenName: String,
                  @SerializedName("familyName") val familyName: String,
                  @SerializedName("dateOfBirth") val dateOfBirth: String,
                  @SerializedName("nationality") val nationality: String
)
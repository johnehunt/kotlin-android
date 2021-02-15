package com.jjh.android.retrofitdemo.model

import com.google.gson.annotations.SerializedName

data class DriverTable (@SerializedName("season") val season: String,
                        @SerializedName("Drivers") val drivers: List<Driver>?)
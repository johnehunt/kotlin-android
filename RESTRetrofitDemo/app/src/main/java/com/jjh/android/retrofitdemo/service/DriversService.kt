package com.jjh.android.retrofitdemo.service

import com.jjh.android.retrofitdemo.model.Drivers
import retrofit2.Call
import retrofit2.http.GET

interface DriversService {
    @GET("api/f1/2020/drivers.json")
    fun getDrivers(): Call<Drivers>
}
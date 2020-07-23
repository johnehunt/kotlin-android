package com.jjh.android.retrofitdemo.service

import com.jjh.android.retrofitdemo.model.Person
import retrofit2.Call
import retrofit2.http.GET

interface PersonService {
    @GET("/users")
    fun getUsers(): Call<List<Person>>
}
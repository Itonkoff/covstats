package com.example.covstats.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://covid-193.p.rapidapi.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

object RetrofitClient {
    val API_SERVICE: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
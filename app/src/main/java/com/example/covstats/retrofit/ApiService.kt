package com.example.covstats.retrofit

import com.example.covstats.retrofit.models.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Headers


interface ApiService {

    @Headers(
        "x-rapidapi-host: covid-193.p.rapidapi.com",
        "x-rapidapi-key: 21a9c009bcmsh44173e11b0344edp17e255jsndcb297edb888"
    )
    @GET("statistics")
    suspend fun getAllStats(): ApiResponse
}
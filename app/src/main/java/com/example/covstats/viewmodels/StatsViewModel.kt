package com.example.covstats.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.covstats.retrofit.RetrofitClient
import com.example.covstats.retrofit.models.ApiResponse
import kotlinx.coroutines.launch

class StatsViewModel(application: Application) : AndroidViewModel(application) {
    var apiResponse: ApiResponse? = null

    fun getAllStats() {
        viewModelScope.launch {
            apiResponse = RetrofitClient.API_SERVICE.getAllStats()
            val a = 0
        }
    }

    fun check(){
        val a = 0
    }
}
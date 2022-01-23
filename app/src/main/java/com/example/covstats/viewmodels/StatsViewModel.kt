package com.example.covstats.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.covstats.retrofit.RetrofitClient
import com.example.covstats.retrofit.models.ApiResponse
import com.example.covstats.room.Db
import com.example.covstats.room.models.Statistic
import com.example.covstats.workers.ClearDbWorker
import kotlinx.coroutines.launch

class StatsViewModel(application: Application) : AndroidViewModel(application) {
    private val TAG = "StatsViewModel"
    var apiResponse = MutableLiveData<ApiResponse>()
    var dao = Db.getDatabase(application).statisticDao()

    private val workManager = WorkManager.getInstance(application)

    fun getAllStats() {
        Log.d(TAG, "getAllStats: Launching call to API")
        viewModelScope.launch {
            apiResponse.value = RetrofitClient.API_SERVICE.getAllStats()
            val a = 0
        }
    }

    fun clearDb() {
        Log.d(TAG, "clearDb: Launching call to clear database")
        workManager.enqueue(OneTimeWorkRequest.from(ClearDbWorker::class.java))
    }

    fun updateDb(response: ApiResponse?) {
        response?.response?.forEach {
            val statistic = Statistic(
                it.continent,
                it.country,
                it.cases.new,
                it.cases.active,
                it.cases.critical,
                it.cases.recovered,
                it.cases.total
            )
            viewModelScope.launch {
                Log.d(TAG, "updateDb: Inserting statistic for : ${statistic.country}")
                dao.insertStatistic(statistic)
            }
        }
    }
}
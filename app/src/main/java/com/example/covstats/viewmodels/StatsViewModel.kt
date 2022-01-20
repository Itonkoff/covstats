package com.example.covstats.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.covstats.retrofit.RetrofitClient
import com.example.covstats.retrofit.models.ApiResponse
import com.example.covstats.room.Db
import com.example.covstats.room.models.Case
import com.example.covstats.room.models.Statistic
import com.example.covstats.workers.ClearDbWorker
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class StatsViewModel(application: Application) : AndroidViewModel(application) {
    var apiResponse = MutableLiveData<ApiResponse>()
    var dao = Db.getDatabase(application).statisticDao()

    private val workManager = WorkManager.getInstance(application)

    fun getAllStats() {
        viewModelScope.launch {
            apiResponse.value = RetrofitClient.API_SERVICE.getAllStats()
            val a = 0
        }
    }



    fun updateDb(response: ApiResponse?) {
        var a = 0
        response?.response?.forEach {
            viewModelScope.launch {
//                Db.getDatabase(getApplication()).clearAllTables()
                val statistic = Statistic(it.continent, it.country)
                async { dao.insertStatistic(statistic) }
                val case = Case(
                    it.cases.new,
                    it.cases.active,
                    it.cases.critical,
                    it.cases.recovered,
                    it.cases.total
                )
                case.statisticCreatorId = statistic.statisticId
                async { dao.insertCase(case) }
            }
        }
    }

    fun clearDb() {
        workManager.enqueue(OneTimeWorkRequest.from(ClearDbWorker::class.java))
    }
}
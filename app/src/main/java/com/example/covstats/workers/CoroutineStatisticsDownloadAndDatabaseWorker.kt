package com.example.covstats.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.covstats.data.retrofit.RetrofitClient
import com.example.covstats.data.room.Db
import com.example.covstats.data.room.models.Statistic

class CoroutineStatisticsDownloadAndDatabaseWorker(private val context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {
    private val TAG = "GetUpdatedStatisticsWkr"

    override suspend fun doWork(): Result {
        return try {
            val allStats = RetrofitClient.API_SERVICE.getAllStats()
            Db.getDatabase(context).clearAllTables()
            val dao = Db.getDatabase(context).statisticDao()
            allStats.response.forEach {
                val statistic = Statistic(
                    it.continent,
                    it.country,
                    it.cases.new,
                    it.cases.active,
                    it.cases.critical,
                    it.cases.recovered,
                    it.cases.total
                )
                Log.d(TAG, "doWork: Inserting statistic for : ${statistic.country}")
                dao.insertStatistic(statistic)
            }
            Result.success()
        } catch (throwable: Throwable) {
            Log.e(TAG, "Error getting stats ${throwable.stackTrace}")
            Result.retry()
        }
    }
}
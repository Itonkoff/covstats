package com.example.covstats.data

import androidx.lifecycle.LiveData
import androidx.work.*
import com.example.covstats.data.room.Dao
import com.example.covstats.data.room.models.Statistic
import com.example.covstats.workers.CoroutineStatisticsDownloadAndDatabaseWorker
import java.util.concurrent.TimeUnit

class CovStatsRepository(private val dao: Dao, workManager: WorkManager) {

    private val TAG = "Repository"

    private val networkWorker = PeriodicWorkRequestBuilder<CoroutineStatisticsDownloadAndDatabaseWorker>(5, TimeUnit.MINUTES)
        .setConstraints(Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build())
        .setBackoffCriteria(BackoffPolicy.LINEAR,2,TimeUnit.MINUTES)
        .build()

    val worker = workManager.enqueue(networkWorker)

    fun selectAllStatistics(): LiveData<List<Statistic>> {
        return dao.selectAllStatistics()
    }
}
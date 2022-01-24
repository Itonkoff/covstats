package com.example.covstats

import android.app.Application
import androidx.work.WorkManager
import com.example.covstats.data.CovStatsRepository
import com.example.covstats.data.room.Db

class CovStatsApplication : Application() {
    private val database by lazy { Db.getDatabase(this) }
    private val workManager by lazy { WorkManager.getInstance(this) }
    val repository by lazy { CovStatsRepository(database.statisticDao(), workManager) }
}
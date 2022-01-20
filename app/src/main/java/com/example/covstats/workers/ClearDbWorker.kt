package com.example.covstats.workers

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.covstats.room.Db

class ClearDbWorker(context: Context,params:WorkerParameters): Worker(context,params) {
    private val TAG = "ClearDbWorker"
    override fun doWork(): Result {
        val appContext = applicationContext
        return try {
            Db.getDatabase(appContext).clearAllTables()
            Result.success()
        }catch (throwable: Throwable) {
            Log.e(TAG, "Error clearing db")
            Result.failure()
        }
    }
}
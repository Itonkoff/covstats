package com.example.covstats.room

import androidx.room.Dao
import androidx.room.Insert
import com.example.covstats.room.models.Statistic

@Dao
interface Dao {

    @Insert
    suspend fun insertStatistic(statistic: Statistic)
}
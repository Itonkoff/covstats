package com.example.covstats.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.covstats.room.models.Statistic

@Dao
interface Dao {

    @Query("SELECT * FROM Statistic")
    fun selectAllStatistics(): LiveData<List<Statistic>>

    @Insert
    suspend fun insertStatistic(statistic: Statistic)

    @Query("SELECT * FROM Statistic WHERE country LIKE :item")
    fun selectStatisticByCountry(item: String): LiveData<Statistic>
}
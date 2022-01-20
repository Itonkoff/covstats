package com.example.covstats.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.covstats.room.models.Case
import com.example.covstats.room.models.Statistic
import com.example.covstats.room.models.StatisticCases

@Dao
interface Dao {
    @Transaction
    @Query("SELECT * FROM Statistic")
    fun getUsersWithPlaylists(): List<StatisticCases>

    @Insert
    suspend fun insertStatistic(statistic: Statistic)

    @Insert
    suspend fun insertCase(case: Case)
}
package com.example.covstats.room

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.covstats.room.models.StatisticCases

@Dao
interface Dao {
    @Transaction
    @Query("SELECT * FROM Statistic")
    fun getUsersWithPlaylists(): List<StatisticCases>
}
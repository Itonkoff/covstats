package com.example.covstats.data.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Statistic(val continent: String?, val country: String, val newCases: String?, val active: Int, val critical: Int, val recovered: Int, val total: Int) {
    @PrimaryKey(autoGenerate = true)
    var statisticId: Int = 0
}
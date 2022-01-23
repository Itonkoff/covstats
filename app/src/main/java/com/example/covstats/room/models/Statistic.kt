package com.example.covstats.room.models

import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Statistic(val continent: String?, val country: String, val newCases: String?, val active: Int, val critical: Int, val recovered: Int, val total: Int) {
    @PrimaryKey(autoGenerate = true)
    var statisticId: Int = 0
}
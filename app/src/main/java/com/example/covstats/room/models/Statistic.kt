package com.example.covstats.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Statistic(val continent: String, val country: String) {
    @PrimaryKey(autoGenerate = true)
    var statisticId: Int = 0
}
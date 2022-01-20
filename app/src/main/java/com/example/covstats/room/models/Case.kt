package com.example.covstats.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Case(val newCases: String, val active: Int, val critical: Int, val recovered: Int, val total: Int) {
    @PrimaryKey(autoGenerate = true)
    var caseId: Int = 0
    var statisticCreatorId: Int = 0
}
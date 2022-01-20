package com.example.covstats.room.models

import androidx.room.Embedded
import androidx.room.Relation
import com.example.covstats.room.models.Case
import com.example.covstats.room.models.Statistic

data class StatisticCases(
    @Embedded val statistic: Statistic,
    @Relation(
        parentColumn = "statisticId",
        entityColumn = "statisticCreatorId"
    ) val cases: List<Case>
)

package com.example.covstats.viewmodels

import androidx.lifecycle.*
import com.example.covstats.data.CovStatsRepository
import com.example.covstats.data.room.models.Statistic

class CovStatsViewModel(covStatsRepository: CovStatsRepository) : ViewModel() {
    private val TAG = "StatsViewModel"
    val statistics = covStatsRepository.selectAllStatistics()
    var statistic = MutableLiveData<Statistic>()

    fun setSelectedCountry(item: Any) {
        val s = item as String
        statistic.value = statistics.value?.first { it.country.startsWith(s) }
    }
}

class CovStatsViewModelFactory(private val covStatsRepository: CovStatsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CovStatsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CovStatsViewModel(covStatsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
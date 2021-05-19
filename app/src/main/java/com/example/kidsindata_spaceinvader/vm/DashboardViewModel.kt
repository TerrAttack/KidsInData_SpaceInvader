package com.example.kidsindata_spaceinvader.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kidsindata_spaceinvader.model.ScoringTrend
import com.example.kidsindata_spaceinvader.repository.DashboardRepository
import com.example.kidsindata_spaceinvader.repository.TrophiesRepository
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {

    private val dashboardRepository = DashboardRepository()
    /**
     * This property points direct to the LiveData in the repository, that value
     */
    val playerScoringTrend = dashboardRepository.graphScoringTrend

    private val _errorText: MutableLiveData<String> = MutableLiveData()

    val errorText: LiveData<String>
        get() = _errorText

    fun getScoringTrend() {
        viewModelScope.launch {
            try {
                dashboardRepository.getPlayerScoringTrend()
            } catch (error: TrophiesRepository.TrophiesRefreshError) {
                _errorText.value = error.message
                Log.e("Game summary error", error.cause.toString())
            }
        }
    }
}
package com.example.kidsindata_spaceinvader.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kidsindata_spaceinvader.repository.DashboardRepository
import com.example.kidsindata_spaceinvader.repository.DataJourneyRepository
import kotlinx.coroutines.launch


class DashboardViewModel : ViewModel() {
    private val dashboardRepository = DashboardRepository()

    private var _spinner: MutableLiveData<Boolean> = MutableLiveData()

    private val _errorText: MutableLiveData<String> = MutableLiveData()

    val dashboardTopScore = dashboardRepository.dashboardTopScore

    /**
     * This property points direct to the LiveData in the repository, that value
     */
    val playerScoringTrend = dashboardRepository.graphScoringTrend

    val playerScoringTrendUsers = dashboardRepository.graphScoringTrendUser

    val errorText: LiveData<String>
        get() = _errorText

    fun getScoringTrend() {
        viewModelScope.launch {
            try {
                dashboardRepository.getPlayerScoringTrend()
            } catch (error: DashboardRepository.DashboardError) {
                _errorText.value = error.message
                Log.e("Game summary error", error.cause.toString())
            }
        }
    }

    fun getScoringTrendUsers(username: String) {
        viewModelScope.launch {
            try {
                dashboardRepository.getUsersScoringTrend(username)
            } catch (error: DashboardRepository.DashboardError) {
                _errorText.value = error.message
                Log.e("Game summary error", error.cause.toString())
            }
        }
    }

    fun getTopTenScores() {
        viewModelScope.launch {
            try {
                _spinner.value = true
                dashboardRepository.getLeaderboard()
            } catch (error: DataJourneyRepository.DataJourneyRefreshError) {
                _errorText.value = error.message
                Log.e("Topscore error", error.cause.toString())
            } finally {
                _spinner.value = false
            }
        }
    }
}


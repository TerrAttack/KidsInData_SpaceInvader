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

    val spinner: LiveData<Boolean>
        get() = _spinner


    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

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


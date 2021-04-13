package com.example.kidsindata_spaceinvader.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kidsindata_spaceinvader.repository.DataJourneyRepository
import com.example.kidsindata_spaceinvader.repository.HomeRepository
import com.example.kidsindata_spaceinvader.repository.TrophiesRepository
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application){

    private val homeRepository = HomeRepository()

    val homePlayerLatestScore = homeRepository.homePlayerLastScore

    private val _errorText: MutableLiveData<String> = MutableLiveData()

    val errorText: LiveData<String>
        get() = _errorText

    fun getPlayerLatestScore() {
        viewModelScope.launch {
            try {
                homeRepository.getLatestScore()
            } catch (error: HomeRepository.HomeRefreshError) {
                _errorText.value = error.message
                Log.e("Latest score error", error.cause.toString())
            }
        }
    }
}
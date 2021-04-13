package com.example.kidsindata_spaceinvader.ui.trophies

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kidsindata_spaceinvader.repository.TrophiesRepository
import kotlinx.coroutines.launch

class TrophiesViewModel(application: Application) : AndroidViewModel(application){

    private val trophiesRepository = TrophiesRepository()
    /**
     * This property points direct to the LiveData in the repository, that value
     */
    val trophiesPlayerRank = trophiesRepository.trophiesPlayerRanking
    val trophiesTopScore = trophiesRepository.trophiesTopScore
    val trophiesGameSummary = trophiesRepository.trophiesGameSummary
    val trophiesLearningCompletion = trophiesRepository.dataJourneyProgress

    private val _errorText: MutableLiveData<String> = MutableLiveData()

    /**
     * The viewModelScope is bound to Dispatchers.Main and will automatically be cancelled when the ViewModel is cleared.
     * Extension method of lifecycle-viewmodel-ktx library
     */
    fun getRank() {
        viewModelScope.launch {
            try {
                trophiesRepository.getPlayerRank()
            } catch (error: TrophiesRepository.TrophiesRefreshError) {
                _errorText.value = error.message
                Log.e("Rank error", error.cause.toString())
            }
        }
    }

    fun getTopScore() {
        viewModelScope.launch {
            try {
                trophiesRepository.getTopScore()
            } catch (error: TrophiesRepository.TrophiesRefreshError) {
                _errorText.value = error.message
                Log.e("Top score error", error.cause.toString())
            }
        }
    }

    fun getGameSummary() {
        viewModelScope.launch {
            try {
                trophiesRepository.getGameSummary()
            } catch (error: TrophiesRepository.TrophiesRefreshError) {
                _errorText.value = error.message
                Log.e("Game summary error", error.cause.toString())
            }
        }
    }

    fun getDataJourneyProgress() {
        viewModelScope.launch {
            try {
                //the dataJourneyRepository sets it's own livedata property
                //our own module property points to this one
                trophiesRepository.getDataJourneyProgress()
            } catch (error: TrophiesRepository.TrophiesRefreshError) {
                _errorText.value = error.message
                Log.e("Next completion error", error.cause.toString())
            }
        }
    }
}
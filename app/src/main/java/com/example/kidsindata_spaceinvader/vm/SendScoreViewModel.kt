package com.example.kidsindata_spaceinvader.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kidsindata_spaceinvader.repository.CreateUserRepository
import com.example.kidsindata_spaceinvader.repository.DataJourneyRepository
import com.example.kidsindata_spaceinvader.repository.SendScoreRepository
import kotlinx.coroutines.launch

class SendScoreViewModel(application: Application) : AndroidViewModel(application) {

    private val sendScoreRepository = SendScoreRepository()

    val sendScore = sendScoreRepository.sendScore

    private val _errorText: MutableLiveData<String> = MutableLiveData()

    fun postSendScore(playerScore: Int, gameDuration: Int) {
        viewModelScope.launch {
            try {
                //the dataJourneyRepository sets it's own livedata property
                //our own module property points to this one
                sendScoreRepository.postSendScore( playerScore, gameDuration)
            } catch (error: DataJourneyRepository.DataJourneyRefreshError) {
                _errorText.value = error.message
                Log.e("Send score error", error.cause.toString())
            }
        }
    }
}
package com.example.kidsindata_spaceinvader.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kidsindata_spaceinvader.api.KidsInDataApi
import com.example.kidsindata_spaceinvader.api.KidsInDataApiService
import com.example.kidsindata_spaceinvader.global_var.Global
import com.example.kidsindata_spaceinvader.model.User
import com.example.numberskotlin.BuildConfig
import kotlinx.coroutines.withTimeout

class SendScoreRepository {

    private val kidsInDataApiService: KidsInDataApiService = KidsInDataApi.createApi()

    private val _sendScore: MutableLiveData<Int> = MutableLiveData()


    val sendScore: LiveData<Int>
        get() = _sendScore

    suspend fun postSendScore(playerScore: Int, gameDuration: Int) {
        try {
            //timeout the request after 5 seconds
            val result = withTimeout(5_000) {
                kidsInDataApiService.postSendScore(
                    BuildConfig.ApiKey,
                    Global.username,
                    playerScore,
                    gameDuration
                )
            }
            _sendScore.value = result
        } catch (error: Throwable) {
            throw DataJourneyRepository.DataJourneyRefreshError(
                "Unable to send scorer",
                error
            )
        }
    }


}

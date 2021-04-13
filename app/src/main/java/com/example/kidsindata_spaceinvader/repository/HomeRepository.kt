package com.example.kidsindata_spaceinvader.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kidsindata_spaceinvader.api.KidsInDataApi
import com.example.kidsindata_spaceinvader.api.KidsInDataApiService
import com.example.kidsindata_spaceinvader.model.PlayerLatestScore
import kotlinx.coroutines.withTimeout

class HomeRepository {

    private val kidsInDataApiService: KidsInDataApiService = KidsInDataApi.createApi()
    private val _homePlayerLatestScore: MutableLiveData<PlayerLatestScore> = MutableLiveData()

    val homePlayerLastScore: LiveData<PlayerLatestScore>
        get() = _homePlayerLatestScore


    suspend fun getLatestScore() {
        try {
            val result = withTimeout(5_000) {
                kidsInDataApiService.getPlayerLatestScore()
            }

            _homePlayerLatestScore.value = result
        } catch (error: Throwable) {
            throw HomeRefreshError("Unable to refresh data", error)
        }
    }

    class HomeRefreshError(message: String, cause: Throwable) : Throwable(message, cause)
}
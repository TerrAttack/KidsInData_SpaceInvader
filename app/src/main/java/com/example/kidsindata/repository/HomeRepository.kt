package com.example.kidsindata.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kidsindata.global_var.Global
import com.example.kidsindata.api.KidsInDataApi
import com.example.kidsindata.api.KidsInDataApiService
import com.example.kidsindata.model.GameSummary
import com.example.kidsindata.model.PlayerLatestScore
import com.example.numberskotlin.BuildConfig
import kotlinx.coroutines.withTimeout

class HomeRepository {
    private val kidsInDataApiService: KidsInDataApiService = KidsInDataApi.createApi()

    private val _homePlayerLatestScore: MutableLiveData<PlayerLatestScore> = MutableLiveData()

    private val _gameSummary: MutableLiveData<GameSummary> = MutableLiveData()

    val homePlayerLastScore: LiveData<PlayerLatestScore>
        get() = _homePlayerLatestScore

    val homeGameSummary: LiveData<GameSummary>
        get() = _gameSummary


    suspend fun getLatestScore() {
        try {
            val result = withTimeout(5_000) {
                kidsInDataApiService.getPlayerLatestScore(BuildConfig.ApiKey, Global.username)
            }

            _homePlayerLatestScore.value = result
        } catch (error: Throwable) {
            throw HomeRefreshError("Unable to refresh data", error)
        }
    }

    suspend fun getGameSummary() {
        try {
            val result = withTimeout(5_000) {
                kidsInDataApiService.getGameSummary(BuildConfig.ApiKey, Global.username)
            }

            _gameSummary.value = result
        } catch (error: Throwable) {
            throw HomeRefreshError("Unable to refresh data", error)
        }
    }

    class HomeRefreshError(message: String, cause: Throwable) : Throwable(message, cause)
}
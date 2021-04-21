package com.example.kidsindata_spaceinvader.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kidsindata_spaceinvader.global_var.Global
import com.example.kidsindata_spaceinvader.api.KidsInDataApi
import com.example.kidsindata_spaceinvader.api.KidsInDataApiService
import com.example.kidsindata_spaceinvader.model.*
import com.example.numberskotlin.BuildConfig
import kotlinx.coroutines.withTimeout

class TrophiesRepository {

    private val kidsInDataApiService: KidsInDataApiService = KidsInDataApi.createApi()
    private val _trophiesPlayerRanking: MutableLiveData<Int> = MutableLiveData()
    private val _trophiesTopScore: MutableLiveData<Int> = MutableLiveData()
    private val _trophiesGameSummary: MutableLiveData<GameSummary> = MutableLiveData()


    /**
     * Expose non MutableLiveData via getter
     * Encapsulation :)
     */
    val trophiesPlayerRanking: LiveData<Int>
        get() = _trophiesPlayerRanking

    val trophiesTopScore: LiveData<Int>
        get() = _trophiesTopScore

    val trophiesGameSummary: LiveData<GameSummary>
        get() = _trophiesGameSummary


    /**
     * suspend function that calls a suspend function from the moduleApi call
     */
    suspend fun getPlayerRank() {
        try {
            //timeout the request after 5 seconds
            val result = withTimeout(5_000) {
                kidsInDataApiService.getPlayerRank(BuildConfig.ApiKey, Global.username)
            }

            _trophiesPlayerRanking.value = result
        } catch (error: Throwable) {
            throw TrophiesRefreshError("Unable to refresh trophies", error)
        }
    }

    suspend fun getTopScore() {
        try {
            //timeout the request after 5 seconds
            val result = withTimeout(5_000) {
                kidsInDataApiService.getTopScore(BuildConfig.ApiKey, Global.username)
            }

            _trophiesTopScore.value = result
        } catch (error: Throwable) {
            throw TrophiesRefreshError("Unable to refresh trophies", error)
        }
    }

    suspend fun getGameSummary() {
        try {
            //timeout the request after 5 seconds
            val result = withTimeout(5_000) {
                kidsInDataApiService.getGameSummary(BuildConfig.ApiKey, Global.username)
            }

            _trophiesGameSummary.value = result
        } catch (error: Throwable) {
            throw TrophiesRefreshError("Unable to refresh trophies", error)
        }
    }


    class TrophiesRefreshError(message: String, cause: Throwable) : Throwable(message, cause)
}
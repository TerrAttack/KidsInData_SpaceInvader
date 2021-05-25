package com.example.kidsindata_spaceinvader.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kidsindata_spaceinvader.global_var.Global
import com.example.kidsindata_spaceinvader.api.KidsInDataApi
import com.example.kidsindata_spaceinvader.api.KidsInDataApiService
import com.example.kidsindata_spaceinvader.model.*
import com.example.kidsindata_spaceinvader.model.TopScore
import com.example.numberskotlin.BuildConfig
import kotlinx.coroutines.withTimeout

class DashboardRepository {

    private val kidsInDataApiService: KidsInDataApiService = KidsInDataApi.createApi()
    private val _scoringTrend: MutableLiveData<ArrayList<ScoringTrend>> = MutableLiveData()
    private val _dashboardTopScore: MutableLiveData<List<TopScore>> = MutableLiveData()

    /**
     * Expose non MutableLiveData via getter
     * Encapsulation :)
     */
    val graphScoringTrend: MutableLiveData<ArrayList<ScoringTrend>>
        get() = _scoringTrend


    /**
     * suspend function that calls a suspend function from the moduleApi call
     */
    suspend fun getPlayerScoringTrend() {
        try {
            //timeout the request after 5 seconds
            val result = withTimeout(5_000) {
                kidsInDataApiService.getPlayerTrend(BuildConfig.ApiKey, Global.username)
            }
            _scoringTrend.value = result
        } catch (error: Throwable) {
            throw DashboardError("Unable to fetch data for dashboard", error)
        }
    }

    val dashboardTopScore: LiveData<List<TopScore>>
        get() = _dashboardTopScore

    suspend fun getLeaderboard(){
        try{
            val result = withTimeout(5_000) {
                kidsInDataApiService.getTopTenScores(BuildConfig.ApiKey)
            }
            _dashboardTopScore.value = result
        } catch (error: Throwable) {
            throw DataJourneyRepository.DataJourneyRefreshError("Unable to refresh data", error)
        }
    }

    class DashboardError(message: String, cause: Throwable) : Throwable(message, cause)
}
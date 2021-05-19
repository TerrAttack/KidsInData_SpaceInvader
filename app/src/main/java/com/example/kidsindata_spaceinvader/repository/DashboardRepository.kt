package com.example.kidsindata_spaceinvader.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kidsindata_spaceinvader.api.KidsInDataApi
import com.example.kidsindata_spaceinvader.api.KidsInDataApiService
import com.example.kidsindata_spaceinvader.model.TopScore
import com.example.numberskotlin.BuildConfig
import kotlinx.coroutines.withTimeout

class DashboardRepository {
    private val kidsInDataApiService: KidsInDataApiService = KidsInDataApi.createApi()

    private val _dashboardTopScore: MutableLiveData<List<TopScore>> = MutableLiveData()

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

}
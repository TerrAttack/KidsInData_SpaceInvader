package com.example.kidsindata_spaceinvader.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kidsindata_spaceinvader.api.DataJourneyApi
import com.example.kidsindata_spaceinvader.api.DataJourneyApiService
import com.example.kidsindata_spaceinvader.data.Module
import kotlinx.coroutines.withTimeout

class DataJourneyRepository {

    private val dataJourneyApiService: DataJourneyApiService = DataJourneyApi.createApi()

    private val _dataJourney: MutableLiveData<List<Module>> = MutableLiveData()

    /**
     * Expose non MutableLiveData via getter
     * Encapsulation :)
     */
    val dataJourney: LiveData<List<Module>>
        get() = _dataJourney

    /**
     * suspend function that calls a suspend function from the moduleApi call
     */
    suspend fun getModule() {
        try {
            //timeout the request after 5 seconds
            val result = withTimeout(5_000) {
                dataJourneyApiService.getModule()
            }

            _dataJourney.value = listOf(result)
        } catch (error: Throwable) {
            throw DataJourneyRefreshError("Unable to refresh module", error)
        }
    }

    class DataJourneyRefreshError(message: String, cause: Throwable) : Throwable(message, cause)
}
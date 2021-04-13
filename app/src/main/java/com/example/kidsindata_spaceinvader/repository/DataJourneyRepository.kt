package com.example.kidsindata_spaceinvader.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kidsindata_spaceinvader.api.KidsInDataApi
import com.example.kidsindata_spaceinvader.api.KidsInDataApiService
import com.example.kidsindata_spaceinvader.model.DataJourneyProgress
import com.example.kidsindata_spaceinvader.model.Module
import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeout

class DataJourneyRepository {

    private val kidsInDataApiService: KidsInDataApiService = KidsInDataApi.createApi()

    private val _dataJourneyModules: MutableLiveData<List<Module>> = MutableLiveData()

    private val _dataJourneyNextModule: MutableLiveData<Module> = MutableLiveData()

    private val _dataJourneyProgress:  MutableLiveData<DataJourneyProgress> = MutableLiveData()

    private val _dataJourneyCompletedModule:  MutableLiveData<Int> = MutableLiveData()

    /**
     * Expose non MutableLiveData via getter
     * Encapsulation :)
     */
    val dataJourneyModules: LiveData<List<Module>>
        get() = _dataJourneyModules

    val dataJourneyNextModule: LiveData<Module>
        get() = _dataJourneyNextModule

    val dataJourneyProgress: LiveData<DataJourneyProgress>
        get() = _dataJourneyProgress

    val dataJourneyCompletedModule: LiveData<Int>
        get() = _dataJourneyCompletedModule


    /**
     * suspend function that calls a suspend function from the moduleApi call
     */
    suspend fun getModules() {
        try {
            //timeout the request after 5 seconds
            val result = withTimeout(5_000) {
                kidsInDataApiService.getModule().sortedBy { it.moduleId }
            }

            _dataJourneyModules.value = result
        } catch (error: Throwable) {
            throw DataJourneyRefreshError("Unable to refresh modules", error)
        }
    }

    suspend fun getNextModule() {
        try {
            //timeout the request after 5 seconds
            val result = withTimeout(5_000) {
                kidsInDataApiService.getNextModule()
            }

            _dataJourneyNextModule.value = result
        } catch (error: Throwable) {
            throw DataJourneyRefreshError("Unable to refresh next module", error)
        }
    }

    suspend fun getDataJourneyProgress() {
        try {
            //timeout the request after 5 seconds
            val result = withTimeout(5_000) {
                kidsInDataApiService.getDataJourneyProgress()
            }

            _dataJourneyProgress.value = result
        } catch (error: Throwable) {
            throw DataJourneyRefreshError("Unable to refresh data journey progress", error)
        }
    }

    suspend fun postModuleCompleted(playerUsername: String, moduleId: Int) {
        try {
            //timeout the request after 5 seconds
            val result = withTimeout(5_000) {
                kidsInDataApiService.postModuleCompleted(playerUsername, moduleId)
            }

            _dataJourneyCompletedModule.value = result
        } catch (error: Throwable) {
            throw DataJourneyRefreshError("Unable to post module completed", error)
        }
    }

    class DataJourneyRefreshError(message: String, cause: Throwable) : Throwable(message, cause)

}
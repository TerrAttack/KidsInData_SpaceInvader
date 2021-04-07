package com.example.kidsindata_spaceinvader.ui.data_journey

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kidsindata_spaceinvader.repository.DataJourneyRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class DataJourneyViewModel(application: Application) : AndroidViewModel(application) {

    private val dataJourneyRepository = DataJourneyRepository()

    /**
     * This property points direct to the LiveData in the repository, that value
     */
    val dataJourneyModules = dataJourneyRepository.dataJourneyModules

    val dataJourneyNextModule = dataJourneyRepository.dataJourneyNextModule

    val dataJourneyProgress = dataJourneyRepository.dataJourneyProgress

    private val _errorText: MutableLiveData<String> = MutableLiveData()

    /**
     * Expose non MutableLiveData via getter
     * errorText can be observed from Activity for error showing
     * Encapsulation :)
     */
    val errorText: LiveData<String>
        get() = _errorText

    /**
     * The viewModelScope is bound to Dispatchers.Main and will automatically be cancelled when the ViewModel is cleared.
     * Extension method of lifecycle-viewmodel-ktx library
     */
    fun getModules() {
        viewModelScope.launch {
            try {
                //the dataJourneyRepository sets it's own livedata property
                //our own module property points to this one
                dataJourneyRepository.getModule()
            } catch (error: DataJourneyRepository.DataJourneyRefreshError) {
                _errorText.value = error.message
                Log.e("Modules error", error.cause.toString())
            }
        }
    }

    fun getNextModule() {
        viewModelScope.launch {
            try {
                //the dataJourneyRepository sets it's own livedata property
                //our own module property points to this one
                dataJourneyRepository.getNextModule()
            } catch (error: DataJourneyRepository.DataJourneyRefreshError) {
                _errorText.value = error.message
                Log.e("Next module error", error.cause.toString())
            }
        }
    }

    fun getDataJourneyProgress() {
        viewModelScope.launch {
            try {
                //the dataJourneyRepository sets it's own livedata property
                //our own module property points to this one
                dataJourneyRepository.getDataJourneyProgress()
            } catch (error: DataJourneyRepository.DataJourneyRefreshError) {
                _errorText.value = error.message
                Log.e("Next module error", error.cause.toString())
            }
        }
    }
}

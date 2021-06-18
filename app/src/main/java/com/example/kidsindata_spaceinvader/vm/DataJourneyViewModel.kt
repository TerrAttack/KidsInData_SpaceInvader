package com.example.kidsindata_spaceinvader.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kidsindata_spaceinvader.repository.DataJourneyRepository
import kotlinx.coroutines.launch


class DataJourneyViewModel(application: Application) : AndroidViewModel(application) {

    private val dataJourneyRepository = DataJourneyRepository()

    /**
     * This property points direct to the LiveData in the repository, that value
     */
    val dataJourneyModules = dataJourneyRepository.dataJourneyModules

    val dataJourneyNextModule = dataJourneyRepository.dataJourneyNextModule

    val dataJourneyProgress = dataJourneyRepository.dataJourneyProgress

    val dataJourneyModuleCompleted = dataJourneyRepository.dataJourneyCompletedModule

    val dataJourneyAllScores = dataJourneyRepository.dataJourneyAllScores


    private val _errorText: MutableLiveData<String> = MutableLiveData()

    private var _spinner: MutableLiveData<Boolean> = MutableLiveData()

    private var _connection: MutableLiveData<Boolean> = MutableLiveData()
    /**
     * Expose non MutableLiveData via getter
     * errorText can be observed from Activity for error showing
     * Encapsulation :)
     */
    val errorText: LiveData<String>
        get() = _errorText

    val connection: LiveData<Boolean>
        get() = _connection

    val spinner: LiveData<Boolean>
        get() = _spinner


    /**
     * The viewModelScope is bound to Dispatchers.Main and will automatically be cancelled when the ViewModel is cleared.
     * Extension method of lifecycle-viewmodel-ktx library
     */
    fun getModules() {
        viewModelScope.launch {
            try {
                //the dataJourneyRepository sets it's own livedata property
                //our own module property points to this one
                _spinner.value = true
                dataJourneyRepository.getModules()
            } catch (error: DataJourneyRepository.DataJourneyRefreshError) {
                _connection.value = false
                _errorText.value = error.message
                Log.e("Modules error", error.cause.toString())
            } finally {
                _spinner.value = false
            }
        }
    }

    fun getAllScores() {
        viewModelScope.launch {
            try {
                //the dataJourneyRepository sets it's own livedata property
                //our own module property points to this one
                _spinner.value = true
                dataJourneyRepository.getAllScores()
            } catch (error: DataJourneyRepository.DataJourneyRefreshError) {
                _connection.value = false
                _errorText.value = error.message
                Log.e("Modules error", error.cause.toString())
            } finally {
                _spinner.value = false
            }
        }
    }

    fun getNextModule() {
        viewModelScope.launch {
            try {
                //the dataJourneyRepository sets it's own livedata property
                //our own module property points to this one
                _spinner.value = true
                dataJourneyRepository.getNextModule()
            } catch (error: DataJourneyRepository.DataJourneyRefreshError) {
                _connection.value = false
                _errorText.value = error.message
                Log.e("Next module error", error.cause.toString())
            } finally {
                _spinner.value = false
            }
        }
    }

    fun getDataJourneyProgress() {
        viewModelScope.launch {
            try {
                //the dataJourneyRepository sets it's own livedata property
                //our own module property points to this one
                _spinner.value = true
                dataJourneyRepository.getDataJourneyProgress()
            } catch (error: DataJourneyRepository.DataJourneyRefreshError) {
                _connection.value = false
                _errorText.value = error.message
                Log.e("Next module error", error.cause.toString())
            } finally {
                _spinner.value = false
            }
        }
    }

    fun postModuleCompleted(moduleId: Int) {
        viewModelScope.launch {
            try {
                //the dataJourneyRepository sets it's own livedata property
                //our own module property points to this one
                _spinner.value = true
                dataJourneyRepository.postModuleCompleted(moduleId)
            } catch (error: DataJourneyRepository.DataJourneyRefreshError) {
                _connection.value = false
                _errorText.value = error.message
                Log.e("Module completed error", error.cause.toString())
            } finally {
                _spinner.value = false
            }
        }
    }
}

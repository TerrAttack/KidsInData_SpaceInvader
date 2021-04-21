package com.example.kidsindata_spaceinvader.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kidsindata_spaceinvader.repository.CreateUserRepository
import com.example.kidsindata_spaceinvader.repository.DataJourneyRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val createUserRepository = CreateUserRepository()

    val createUser = createUserRepository.createUser

    private val _errorText: MutableLiveData<String> = MutableLiveData()

    fun postCreateUser(playerUsername: String, avatarId: Int) {
        viewModelScope.launch {
            try {
                //the dataJourneyRepository sets it's own livedata property
                //our own module property points to this one
                createUserRepository.postCreateUser(playerUsername, avatarId)
            } catch (error: DataJourneyRepository.DataJourneyRefreshError) {
                _errorText.value = error.message
                Log.e("Create user error", error.cause.toString())
            }
        }
    }
}
package com.example.kidsindata_spaceinvader.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kidsindata_spaceinvader.api.KidsInDataApi
import com.example.kidsindata_spaceinvader.api.KidsInDataApiService
import com.example.kidsindata_spaceinvader.global_var.Global
import com.example.kidsindata_spaceinvader.model.Module
import com.example.kidsindata_spaceinvader.model.User
import com.example.numberskotlin.BuildConfig
import kotlinx.coroutines.withTimeout

class CreateUserRepository {

    private val kidsInDataApiService: KidsInDataApiService = KidsInDataApi.createApi()

    private val _createUser: MutableLiveData<User> = MutableLiveData()

    private val _chooseUser: MutableLiveData<List<User>> = MutableLiveData()

    val createUser: LiveData<User>
        get() = _createUser

    val chooseUser: LiveData<List<User>>
        get() = _chooseUser

    suspend fun postCreateUser(playerUsername: String, avatarId: Int) {
        try {
            //timeout the request after 5 seconds
            val result = withTimeout(5_000) {
                kidsInDataApiService.postCreateUser(
                    BuildConfig.ApiKey,
                    playerUsername,
                    avatarId
                )
            }
            _createUser.value = result
        } catch (error: Throwable) {
            throw DataJourneyRepository.DataJourneyRefreshError(
                "Unable to create user",
                error
            )
        }
    }

    suspend fun getUsers() {
        try {
            //timeout the request after 5 seconds
            val result = withTimeout(5_000) {
                kidsInDataApiService.getUsers(BuildConfig.ApiKey)
            }

            _chooseUser.value = result
        } catch (error: Throwable) {
            throw DataJourneyRepository.DataJourneyRefreshError("Unable to refresh users", error)
        }
    }
}
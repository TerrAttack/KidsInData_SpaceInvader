package com.example.kidsindata_spaceinvader.api

import com.example.kidsindata_spaceinvader.model.DataJourneyProgress
import com.example.kidsindata_spaceinvader.model.Module
import retrofit2.http.GET

interface KidsInDataApiService {

    // The GET method needed to retrieve a module from a user STILL HARDCODED!!!
    @GET("/api/spaceinvaders/modules?apikey=40440e4c-9148-11eb-8e8a-281878c7351f&playerusername=zep-135")
    suspend fun getModule(): List<Module>

    // The GET method needed to retrieve the next module from a user STILL HARDCODED!!!
    @GET("/api/spaceinvaders/nextlearningmodule?apikey=40440e4c-9148-11eb-8e8a-281878c7351f&playerusername=zep-135")
    suspend fun getNextModule(): Module

    // The GET method needed to retrieve the next module from a user STILL HARDCODED!!!
    @GET("/api/spaceinvaders/learningsummary?apikey=40440e4c-9148-11eb-8e8a-281878c7351f&playerusername=zep-135")
    suspend fun getDataJourneyProgress(): DataJourneyProgress


}
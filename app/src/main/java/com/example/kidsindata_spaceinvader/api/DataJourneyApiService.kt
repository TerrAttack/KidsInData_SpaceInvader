package com.example.kidsindata_spaceinvader.api

import com.example.kidsindata_spaceinvader.data.Module
import retrofit2.http.GET

interface DataJourneyApiService {

    // The GET method needed to retrieve a module
    @GET("/api/spaceinvaders/modules?apikey=40440e4c-9148-11eb-8e8a-281878c7351f&playerusername=zep-135")
    suspend fun getModule(): List<Module>
}
    

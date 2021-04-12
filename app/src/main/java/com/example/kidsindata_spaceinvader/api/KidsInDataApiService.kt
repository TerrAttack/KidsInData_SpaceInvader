package com.example.kidsindata_spaceinvader.api

import com.example.kidsindata_spaceinvader.model.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.net.URL

interface KidsInDataApiService {

    /**
     * Data Journey
     */
    // The GET method needed to retrieve a module from a user STILL HARDCODED!!!
    @GET("/api/spaceinvaders/modules?apikey=40440e4c-9148-11eb-8e8a-281878c7351f&playerusername=dumbledor-131")
    suspend fun getModule(): List<Module>

    // The GET method needed to retrieve the next module from a user STILL HARDCODED!!!
    @GET("/api/spaceinvaders/nextlearningmodule?apikey=40440e4c-9148-11eb-8e8a-281878c7351f&playerusername=cool-137")
    suspend fun getNextModule(): Module

    // The GET method needed to retrieve the data journey progress from a user STILL HARDCODED!!!
    @GET("/api/spaceinvaders/learningsummary?apikey=40440e4c-9148-11eb-8e8a-281878c7351f&playerusername=cool-137")
    suspend fun getDataJourneyProgress(): DataJourneyProgress

    // The GET method needed to retrieve the data journey progress from a user STILL HARDCODED!!!
    @POST("/workshop/modulecompleted?apikey=40440e4c-9148-11eb-8e8a-281878c7351f")
    suspend fun postModuleCompleted(@Query("playerusername") playerUsername: String, @Query("moduleid") moduleId: Int): Int

    /**
     * Trophies
     */
    @GET("/api/spaceinvaders/playerrank?apikey=40440e4c-9148-11eb-8e8a-281878c7351f&playerusername=aminhva-139")
    suspend fun getPlayerRank(): Int

    @GET("/api/spaceinvaders/playertopscore?apikey=40440e4c-9148-11eb-8e8a-281878c7351f&playerusername=aminhva-139")
    suspend fun getTopScore(): Int

    @GET("/api/spaceinvaders/gamesummary?apikey=40440e4c-9148-11eb-8e8a-281878c7351f&playerusername=aminhva-139")
    suspend fun getGameSummary(): GameSummary


    /**
     * Dashboard
     */
//    @GET("/api/spaceinvaders/playertrend?apikey=40440e4c-9148-11eb-8e8a-281878c7351f&playerusername=aminhva-139")
//    suspend fun getScoringTrend(): List<ScoringTrend>


}
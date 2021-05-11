package com.example.kidsindata_spaceinvader.api

import com.example.kidsindata_spaceinvader.model.*
import retrofit2.http.*

interface KidsInDataApiService {
    /**
     * Data Journey
     */
    // The GET method needed to retrieve a module from a user
    @GET("/api/spaceinvaders/modules")
    suspend fun getModule(
        @Query("apikey") apiKey: String,
        @Query("playerusername") username: String
    ): List<Module>

    // The GET method needed to retrieve the next module from a user
    @GET("/api/spaceinvaders/nextlearningmodule")
    suspend fun getNextModule(
        @Query("apikey") apiKey: String,
        @Query("playerusername") username: String
    ): Module

    // The GET method needed to retrieve the data journey progress from a user
    @GET("/api/spaceinvaders/learningsummary")
    suspend fun getDataJourneyProgress(
        @Query("apikey") apiKey: String,
        @Query("playerusername") username: String
    ): DataJourneyProgress

    // The POST method needed to set the moduleCompleted to "true" in the data journey
    @POST("/workshop/modulecompleted")
    suspend fun postModuleCompleted(
        @Query("apikey") apiKey: String,
        @Query("playerusername") playerUsername: String,
        @Query("moduleid") moduleId: Int
    ): Int

    // The GET method to retrieve data for all scores.
    @GET("/api/spaceinvaders/allscores")
    suspend fun getAllScores(
        @Query("apikey") apiKey: String,
    ): AllScores


    /**
     * Trophies
     */
    @GET("/api/spaceinvaders/playerrank")
    suspend fun getPlayerRank(
        @Query("apikey") apiKey: String,
        @Query("playerusername") username: String
    ): Int

    @GET("/api/spaceinvaders/playertopscore")
    suspend fun getTopScore(
        @Query("apikey") apiKey: String,
        @Query("playerusername") username: String
    ): Int

    @GET("/api/spaceinvaders/gamesummary")
    suspend fun getGameSummary(
        @Query("apikey") apiKey: String,
        @Query("playerusername") username: String
    ): GameSummary


    /**
     * Addition for homescreen
     */
    @GET("/api/spaceinvaders/playerlatestscore")
    suspend fun getPlayerLatestScore(
        @Query("apikey") apiKey: String,
        @Query("playerusername") username: String
    ): PlayerLatestScore

    /**
     * Dashboard
     */
//    @GET("/api/spaceinvaders/playertrend?apikey=40440e4c-9148-11eb-8e8a-281878c7351f&playerusername=aminhva-139")
//    suspend fun getScoringTrend(): List<ScoringTrend>

    /**
     * Create new user
     */

    @POST("/api/spaceinvaders/newplayer")
    suspend fun postCreateUser(
        @Query("apikey") apiKey: String,
        @Query("playerusername") playerUsername: String,
        @Query("avatarid") avatarId: Int
    ): User

    @GET("/api/spaceinvaders/workshopplayers")
    suspend fun getUsers(
        @Query("apikey") apiKey: String,
    ): List<User>


}
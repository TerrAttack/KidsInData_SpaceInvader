package com.example.kidsindata_spaceinvader.model

import com.google.gson.annotations.SerializedName

data class AllScores (

    @SerializedName("gameId") var gameId: Int,

    @SerializedName("playerUserName") var playerNameUserName: String,

    @SerializedName("playerScore") var playerScore: Int,

    @SerializedName("gameDuration") var gameDuration: Int
)
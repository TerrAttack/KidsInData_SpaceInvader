package com.example.kidsindata_spaceinvader.model

import com.google.gson.annotations.SerializedName

data class ScoringTrend (

    @SerializedName("game") var game: Int,

    @SerializedName("gameDateTime") var gameDateTime: String,

    @SerializedName("playerScore") var playerScore: Double
)
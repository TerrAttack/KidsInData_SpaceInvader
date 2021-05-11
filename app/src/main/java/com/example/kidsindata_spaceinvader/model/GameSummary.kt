package com.example.kidsindata_spaceinvader.model

import com.google.gson.annotations.SerializedName

data class GameSummary (

    @SerializedName("playerUserName") var playerUserName: String,

    @SerializedName("noOfGames") var noOfGames: Int,

    @SerializedName("lastPlayed") var lastPlayed: String,

)
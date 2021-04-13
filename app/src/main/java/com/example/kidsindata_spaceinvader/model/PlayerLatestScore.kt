package com.example.kidsindata_spaceinvader.model

import com.google.gson.annotations.SerializedName

class PlayerLatestScore (
        @SerializedName("gameId") var gameId: Int,
        @SerializedName("playerName") var playerName: String,
        @SerializedName("playerUsername") var playerUserName: String,
        @SerializedName("playerScore") var playerScore: Int,
        @SerializedName("playedDateTime") var playedDate: String,
        @SerializedName("playerAvatar") var playerAvatar: String,
        @SerializedName("gameDuration") var gameDuration: Int,
        @SerializedName("playerAgeGroup") var playerAgeGroup: Int,
        @SerializedName("playerLocation") var playerLocation: Int,
        @SerializedName("playerRole") var playerRole: Int
)

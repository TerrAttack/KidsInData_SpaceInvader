package com.example.kidsindata_spaceinvader.model

import com.google.gson.annotations.SerializedName
import java.sql.Time
import java.util.*

data class TopScore (
        @SerializedName("gameId") var gameId: Int,

        @SerializedName("playerName") var playerName: String,

        @SerializedName("playerUserName") var playerUserName: String,

        @SerializedName("playerScore") var playerScore: Int,

        @SerializedName("playedDateTime") var playedDateTime: Date,

        @SerializedName("gameDuration") var gameDuration: Time,

        @SerializedName("playerAvatar") var playerAvatar: String,

        @SerializedName("playerAvatarId") var playerAvatarId: Int,

        ) {
    fun getAvatarUrl() = "https://kid-trial.azurewebsites.net$playerAvatar"
}
package com.example.kidsindata_spaceinvader.model

import androidx.annotation.Nullable
import com.google.gson.annotations.SerializedName

data class User(

    @SerializedName("playerName") var playerName: String,

    @SerializedName("playerUserName") var playerUserName: String,

    @SerializedName("playerCreatedDateTime") var playerCreatedDateTime: String,

    @SerializedName("playerAvatar") var playerAvatar: String,

    @SerializedName("playerAvatarId") var playerAvatarId: Int,

    @SerializedName("deleted") var deleted: Boolean,

    @SerializedName("playerCreatedKey") var playerCreatedKey: String?,

    @SerializedName("playerWorkshopId") var playerWorkshopId: Int,

    @SerializedName("playerId") var playerId: Int
) {
    fun getAvatarUrl() = "https://kid-trial.azurewebsites.net$playerAvatar"
}
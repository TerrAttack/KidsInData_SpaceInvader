package com.example.kidsindata.model

import com.google.gson.annotations.SerializedName

data class User(

    @SerializedName("playerName") var playerName: String,

    @SerializedName("playerUsername") var playerUserName: String,

    @SerializedName("playerCreatedDateTime") var playerCreatedDateTime: String,

    @SerializedName("playerAvatar") var playerAvatar: String,

    @SerializedName("playerAvatarId") var playerAvatarId: Int,

    @SerializedName("deleted") var deleted: Boolean,

    @SerializedName("playerCreatedKey") var playerCreatedKey: String,

    @SerializedName("playerWorkshopId") var playerWorkshopId: Int,

    @SerializedName("playerId") var playerId: Int
) {
}
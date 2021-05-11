package com.example.kidsindata_spaceinvader.model

data class AllScores (
    var gameId: Int,
    var playerName: String,
    var playerUserName: String,
    var playerScore: Int,
    var playedDateTime: String,
    var playerAvatar: String,
    var gameDuration: Int,
    var playerAgeGroup: String?,
    var playerLocation: String?,
    var playerRole: String?
)
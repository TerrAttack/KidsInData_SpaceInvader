package com.example.kidsindata_spaceinvader.model

data class Trophy (
    var trophyId: Int,
    var trophyTitle: String,
    var trophyDesc: String,
    var trophyCompletion: Boolean
){
    companion object {
        val TROPHIES = arrayOf(
            Trophy(1, "Reach top 10 in the rankings", "You have to reach a game score above the 10th place to be in the top 10.", false),
            Trophy(2, "Play more than 5 games", "You need to have played more than 5 games.", false),
            Trophy(3, "Top score over 20.000", "Your score needs to be over 20.000.", false)
        )
    }
}
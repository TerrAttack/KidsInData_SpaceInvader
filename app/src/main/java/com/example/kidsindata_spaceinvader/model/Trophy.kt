package com.example.kidsindata_spaceinvader.model

data class Trophy (
    var trophyId: Int,
    var trophyTitle: String,
    var trophyDesc: String,
    var trophyCompletion: Boolean
){
    companion object {
        val TROPHIES = arrayOf(
            Trophy(1, "Reach top 100 in the rankings", "You have to reach a game score above the 10th place to be in the top 100.", false),
            Trophy(2, "Reach top 10 in the rankings", "You have to reach a game score above the 10th place to be in the top 10.", false),
            Trophy(3, "Play more than 5 games", "You need to have played more than 5 games.", false),
            Trophy(4, "Play more than 10 games", "You need to have played more than 5 games.", false),
            Trophy(5, "Top score over 10.000", "Your score needs to be over 10.000.", false),
            Trophy(6, "Top score over 20.000", "Your score needs to be over 20.000.", false),
            Trophy(7, "completed 20% of your data journey", "You need to complete 20% of your data journey modules", false),
            Trophy(8, "completed 40% of your data journey", "You need to complete 40% of your data journey modules", false),
            Trophy(9, "completed 60% of your data journey", "You need to complete 60% of your data journey modules", false),
            Trophy(10, "completed 80% of your data journey", "You need to complete 80% of your data journey modules", false),
            Trophy(11, "completed 100% of your data journey", "You need to complete all of your data journey modules", false),
        )
    }
}
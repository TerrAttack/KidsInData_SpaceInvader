package com.example.kidsindata_spaceinvader.model

import com.google.gson.annotations.SerializedName

data class DataJourneyProgress (

    @SerializedName("totalActiveMoudles") var totalActiveModules: Int,

    @SerializedName("modulesCompleted") var modulesCompleted: Int,

    @SerializedName("completedPercentage") var completedPercentage: Double,
)
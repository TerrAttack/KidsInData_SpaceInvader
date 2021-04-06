package com.example.kidsindata_spaceinvader.model

import com.google.gson.annotations.SerializedName

data class Module(

    @SerializedName("moduleId") var moduleId: Int,

    @SerializedName("moduleName") var moduleName: String,

    @SerializedName("moduleDescription") var moduleDescription: String,

    @SerializedName("interactive") var interactive: Int,

    @SerializedName("active") var active: Int,

    @SerializedName("time") var time: String,

    @SerializedName("moduleLastCompletedDatetime") var moduleLastCompletedDatetime: String,

    @SerializedName("moduleLastOpenDatetime") var moduleLastOpenDatetime: String,

    @SerializedName("moduleCompletedFlag") var moduleCompletedFlag: Boolean,

    @SerializedName("ModuleOpnedFlag") var moduleOpnedFlag: Boolean,
)




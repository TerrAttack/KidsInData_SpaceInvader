package com.example.kidsindata_spaceinvader.global_var

import android.app.Application

class Global : Application() {
    companion object {
        @JvmField
        var username : String = "defaultValue"

        var avatarId : Int = 1
    }
}
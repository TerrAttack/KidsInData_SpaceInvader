package com.example.kidsindata_spaceinvader

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.kidsindata_spaceinvader.ui.space_invader_game.UnityFragment
import com.example.numberskotlin.R
import com.example.numberskotlin.databinding.ActivityMainBinding
import com.example.numberskotlin.databinding.ActivitySpaceInvaderBinding
import com.unity3d.player.UnityPlayer

class SpaceInvaderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_space_invader)
    }

    companion object {
        @JvmStatic
        fun spaceInvaderScore(intData: String) {
            print("Int message received from Unity: $intData")
        }
    }
}
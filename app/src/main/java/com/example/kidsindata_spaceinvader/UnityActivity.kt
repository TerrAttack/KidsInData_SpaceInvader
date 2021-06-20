package com.example.kidsindata_spaceinvader

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.kidsindata_spaceinvader.global_var.Global
import com.example.kidsindata_spaceinvader.ui.space_invader_game.UnityFragment
import com.example.kidsindata_spaceinvader.vm.SendScoreViewModel
import com.example.numberskotlin.R
import java.util.EnumSet.of

class UnityActivity : AppCompatActivity() {

    companion object {
        lateinit var sendScoreViewModel: SendScoreViewModel

        private var scoreSend: Int = 0

        @JvmStatic
        fun spaceInvaderScore(score: String) {
            print("Int message received from Unity: $score")
            scoreSend = score.toInt()
        }

        @JvmStatic
        fun spaceInvaderGameDuration(gameDuration: String) {
            print("Int message received from Unity: $gameDuration")
            sendScoreViewModel.postSendScore(scoreSend, gameDuration.toInt())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unity)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        sendScoreViewModel = ViewModelProvider(this).get(SendScoreViewModel::class.java)

        var btn: ImageView = findViewById<ImageView>(R.id.stGame)

        btn.setOnClickListener {
            UnityFragment.quitUnityActivity()
        }
    }
}

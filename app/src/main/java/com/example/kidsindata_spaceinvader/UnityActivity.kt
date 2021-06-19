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
import com.example.kidsindata_spaceinvader.vm.SendScoreViewModel
import com.example.numberskotlin.R
import java.util.EnumSet.of

class UnityActivity : AppCompatActivity() {

//    private val sendScoreViewModel: SendScoreViewModel by viewModels()

    companion object {
        lateinit var sendScoreViewModel: SendScoreViewModel

        @JvmStatic
        fun spaceInvaderScore(intData: String) {
            print("Int message received from Unity: $intData")
            sendScoreViewModel.postSendScore(intData.toInt(), 10)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unity)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        sendScoreViewModel = ViewModelProvider(this).get(SendScoreViewModel::class.java)

        var btn: ImageView = findViewById<ImageView>(R.id.stGame)

        btn.setOnClickListener {
            val intent = Intent(applicationContext, SpaceInvaderActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
    }
}

package com.example.kidsindata_spaceinvader

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import com.example.kidsindata_spaceinvader.global_var.Global
import com.example.kidsindata_spaceinvader.vm.HomeViewModel
import com.example.kidsindata_spaceinvader.vm.TrophiesViewModel

import com.example.numberskotlin.R

class SplashScreenActivity : AppCompatActivity() {

    private val handler = Handler()

    private val trophiesViewModel: TrophiesViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        checkConnectivity()
    }

    private fun checkConnectivity() {
        val manager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = manager.activeNetworkInfo

        if (null == activeNetwork) {
            onPause()
            val dialogBuilder = AlertDialog.Builder(this)
            // set message of alert dialog
            dialogBuilder.setMessage("Make sure that WI-FI or mobile data is turned on, then try again")
                // if the dialog is cancelable
                .setCancelable(false)
                // positive button text and action
                .setPositiveButton("Retry", DialogInterface.OnClickListener { dialog, id ->
                    recreate()
                })
                // negative button text and action
                .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, id ->
                    finish()
                })

            // create dialog box
            val alert = dialogBuilder.create()
            // set title for alert dialog box
            alert.setTitle("No Internet Connection")
            alert.setIcon(R.drawable.kid_logo_inverted)
            // show alert dialog
            alert.show()
        } else {
            //Checks if the user already registers if not then he will go through register progress, else he wil go to homescreen.
            var isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isfirstrun", true)

            if (true) {
                handler.postDelayed({
                    startActivity(Intent(applicationContext, LoginActivity::class.java))
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                    finish()
                }, 2000)
            } else {
                handler.postDelayed({
                    val sharedPreferences =
                        applicationContext.getSharedPreferences("SHARED_PREFS", MODE_PRIVATE)
                    var username: String? = sharedPreferences?.getString("USERNAME_FILLED", "")
                    Global.username = username!!

                    homeViewModel.getGameSummary()
                    trophiesViewModel.getRank()
                    trophiesViewModel.getTopScore()
                    trophiesViewModel.getGameSummary()

                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                    finish()
                }, 2000)
            }
        }
    }
}

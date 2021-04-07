package com.example.kidsindata_spaceinvader

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.numberskotlin.R

class SplashScreenActivity : AppCompatActivity() {

    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
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
            alert.setIcon(R.mipmap.ic_launcher)
            // show alert dialog
            alert.show()
        }
        else {
            handler.postDelayed({
                startActivity(Intent(applicationContext, MainActivity::class.java))
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                finish()
            }, 2000)
        }
    }
}
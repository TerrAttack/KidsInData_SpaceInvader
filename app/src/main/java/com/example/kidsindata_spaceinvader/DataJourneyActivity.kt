package com.example.kidsindata_spaceinvader

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import com.example.kidsindata_spaceinvader.vm.DataJourneyViewModel
import com.example.numberskotlin.R
import com.example.numberskotlin.databinding.ActivityDataJourneyBinding


class DataJourneyActivity : AppCompatActivity() {

    private val viewModel: DataJourneyViewModel by viewModels()

    private lateinit var navController: NavController
    private lateinit var binding: ActivityDataJourneyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataJourneyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.customToolbar))
        supportActionBar?.hide()

        checkConnectivity()
        viewModel.getModules()
        viewModel.getDataJourneyProgress()
        viewModel.getNextModule()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_from_top, R.anim.slide_to_bottom)
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
        }
    }
}
package com.example.kidsindata_spaceinvader

import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.kidsindata_spaceinvader.ui.data_journey.DataJourneyViewModel
import com.example.kidsindata_spaceinvader.ui.trophies.TrophiesViewModel
import com.example.numberskotlin.R

class MainActivity : AppCompatActivity() {

    private val dataJourneyViewModel: DataJourneyViewModel by viewModels()
    private val trophiesViewModel: TrophiesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.customToolbar))
        supportActionBar?.hide()

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_space_invader, R.id.navigation_dashboard, R.id.navigation_data_journey, R.id.navigation_trophies))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        dataJourneyViewModel.getModule()
        dataJourneyViewModel.getNextModule()
        dataJourneyViewModel.getDataJourneyProgress()

        trophiesViewModel.getRank()
        trophiesViewModel.getTopScore()
        trophiesViewModel.getGameSummary()

    }
}
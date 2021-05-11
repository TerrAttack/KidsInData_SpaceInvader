package com.example.kidsindata_spaceinvader

import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.kidsindata_spaceinvader.ui.explanation.ExplanationDialogFragment
import com.example.kidsindata_spaceinvader.vm.HomeViewModel
import com.example.kidsindata_spaceinvader.vm.TrophiesViewModel
import com.example.numberskotlin.R
import com.example.numberskotlin.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val trophiesViewModel: TrophiesViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.customToolbar))
        supportActionBar?.hide()

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_trophies
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        checkFirstRun()

        homeViewModel.getGameSummary()
        trophiesViewModel.getRank()
        trophiesViewModel.getTopScore()
        trophiesViewModel.getGameSummary()
    }

    private fun checkFirstRun() {
        var isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .getBoolean("isfirstexplanation", true)

        if (isFirstRun) {
            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isfirstexplanation", false).apply()

            ExplanationDialogFragment().show(supportFragmentManager, "custom dialog")
        }
    }
}
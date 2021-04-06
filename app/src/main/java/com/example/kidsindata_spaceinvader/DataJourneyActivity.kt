package com.example.kidsindata_spaceinvader

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import com.example.kidsindata_spaceinvader.ui.data_journey.DataJourneyViewModel
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

        viewModel.getModules()
        viewModel.getDataJourneyProgress()
        viewModel.getNextModule()
    }
}
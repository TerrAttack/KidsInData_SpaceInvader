package com.example.kidsindata_spaceinvader.ui.home

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import com.example.kidsindata_spaceinvader.DataJourneyActivity
import com.example.kidsindata_spaceinvader.ui.data_journey.DataJourneyViewModel
import com.example.numberskotlin.R
import com.example.numberskotlin.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private val viewModel: DataJourneyViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonGame.setOnClickListener {
            goToGame()
        }

        binding.buttonDatajourney.setOnClickListener {
            goToDataJourney()
        }
    }

    private fun goToGame() {

    }

    private fun goToDataJourney() {
        val intent = Intent(activity, DataJourneyActivity::class.java)
        startActivity(intent)
        activity?.overridePendingTransition(R.anim.slide_from_bottom, R.anim.slide_to_top)

    }
}


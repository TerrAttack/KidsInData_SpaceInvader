package com.example.kidsindata_spaceinvader.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

import com.example.numberskotlin.R
import com.example.numberskotlin.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
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

        initViews()
    }

    private fun initViews() {
        val buttonDataJourney = binding.buttonDatajourney
        buttonDataJourney.setOnClickListener {
            goToDatajourney()
        }

    }

    private fun goToGame() {

    }

    private fun goToDatajourney() {
           findNavController().navigate(R.id.action_fragment_home_screen_to_navigation_data_journey)
    }
}
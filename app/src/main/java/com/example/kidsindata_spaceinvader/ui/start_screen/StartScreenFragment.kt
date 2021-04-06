package com.example.kidsindata_spaceinvader.ui.start_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.kidsindata_spaceinvader.R
import com.example.kidsindata_spaceinvader.databinding.FragmentStartScreenBinding
import com.example.kidsindata_spaceinvader.ui.dashboard.StartScreenViewModel

class StartScreenFragment : Fragment() {

    private var _binding: FragmentStartScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonNo.setOnClickListener {
            view.findNavController().navigate(R.id.action_start_screen_to_explanationFirstFragment)
        }
    }
}
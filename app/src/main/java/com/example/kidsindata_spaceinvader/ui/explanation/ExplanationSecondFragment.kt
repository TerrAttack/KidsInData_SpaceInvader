package com.example.kidsindata_spaceinvader.ui.explanation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kidsindata_spaceinvader.R
import com.example.kidsindata_spaceinvader.databinding.FragmentExplanationFirstBinding
import com.example.kidsindata_spaceinvader.databinding.FragmentExplanationSecondBinding
import com.example.kidsindata_spaceinvader.ui.dashboard.DashboardViewModel

class ExplanationSecondFragment : Fragment() {

    private var _binding: FragmentExplanationSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExplanationSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
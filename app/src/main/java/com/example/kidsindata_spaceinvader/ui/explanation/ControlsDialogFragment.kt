package com.example.kidsindata_spaceinvader.ui.explanation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.kidsindata_spaceinvader.databinding.FragmentControlsBinding

class ControlsDialogFragment : DialogFragment() {

    private var _binding: FragmentControlsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentControlsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setClickListeners()
    }

    private fun setClickListeners() {
        binding.btnCancelControls.setOnClickListener { dismiss() }
        binding.btnNextControls.setOnClickListener { dismiss() }
    }
}
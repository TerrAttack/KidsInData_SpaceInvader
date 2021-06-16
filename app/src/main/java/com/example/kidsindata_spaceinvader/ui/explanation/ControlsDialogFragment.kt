package com.example.kidsindata_spaceinvader.ui.explanation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.numberskotlin.databinding.FragmentControlsBinding

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setClickListeners() {
        binding.ivCloseControls.setOnClickListener { dismiss() }
        binding.btnCloseControls.setOnClickListener {dismiss() }
    }
}
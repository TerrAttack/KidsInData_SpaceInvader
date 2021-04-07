package com.example.kidsindata_spaceinvader.ui.start_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kidsindata_spaceinvader.ui.dashboard.StartScreenViewModel
import com.example.kidsindata_spaceinvader.ui.explanation.ExplanationDialogFragment
import com.example.numberskotlin.databinding.FragmentStartScreenBinding

class StartScreenFragment : Fragment() {

    private lateinit var startScreenViewModel: StartScreenViewModel

    private var _binding: FragmentStartScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        startScreenViewModel =
                ViewModelProvider(this).get(StartScreenViewModel::class.java)
        _binding = FragmentStartScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonNo.setOnClickListener{
            ExplanationDialogFragment().show(parentFragmentManager, "custom dialog")
        }
    }
}
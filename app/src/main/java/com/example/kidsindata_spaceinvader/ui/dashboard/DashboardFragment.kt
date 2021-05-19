package com.example.kidsindata_spaceinvader.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.kidsindata_spaceinvader.vm.DashboardViewModel
import com.example.numberskotlin.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private val viewModel: DashboardViewModel by activityViewModels()

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getScoringTrend()

        observeValues()
    }

    private fun observeValues() {
        viewModel.playerScoringTrend.observe(viewLifecycleOwner, {
           binding.textView13.text = it.size.toString()
        })

        // Observe the error message.
        viewModel.errorText.observe(viewLifecycleOwner, {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })
    }
}
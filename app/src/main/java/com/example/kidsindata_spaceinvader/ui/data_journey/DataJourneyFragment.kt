package com.example.kidsindata_spaceinvader.ui.data_journey

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kidsindata_spaceinvader.R
import com.example.kidsindata_spaceinvader.databinding.FragmentDataJourneyBinding


class DataJourneyFragment : Fragment() {

    private var _binding: FragmentDataJourneyBinding? = null
    private val binding get() = _binding!!

    private val modules = arrayListOf<Module>()
    private val dataJourneyAdapter = DataJourneyAdapter(modules)

    private val AMOUNT_OF_MODULES = 5
    private var modulesCompleted = 0
    private var progress = 0

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDataJourneyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        updateProgress()

        binding.tenPlus.setOnClickListener {
            moduleCompleted()
            updateProgress()
        }
    }

    private fun initViews() {
        binding.rvModules.layoutManager =
                LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvModules.adapter = dataJourneyAdapter

        for (i in Module.MODULE_NAME.indices) {
            modules.add(
                    Module(i + 1,
                            Module.MODULE_NAME[i],
                            Module.MODULE_DESCRIPTION[i],
                            Module.INTERACTIVE[i],
                            Module.MODULE_COMPLETED_FLAG[i])
            )
        }
        dataJourneyAdapter.notifyDataSetChanged()
    }

    private fun updateProgress() {
        binding.progressBar.progress = progress
        binding.tvProgress.text = "$progress%"
        binding.tvCompletedModules.text = getString(R.string.modules_completed, modulesCompleted, AMOUNT_OF_MODULES)
    }

    private fun moduleCompleted() {
        if (progress < 100) {
            modulesCompleted++
            progress = (modulesCompleted * 100) / AMOUNT_OF_MODULES
        }
    }
}
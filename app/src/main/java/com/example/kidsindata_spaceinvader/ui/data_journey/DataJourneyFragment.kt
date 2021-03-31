package com.example.kidsindata_spaceinvader.ui.data_journey

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kidsindata_spaceinvader.data.Module
import com.example.numberskotlin.R
import com.example.numberskotlin.databinding.FragmentDataJourneyBinding


class DataJourneyFragment : Fragment() {

    private val viewModel: DataJourneyViewModel by activityViewModels()

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
        viewModel.getModule()
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

        viewModel.dataJourney.observe(viewLifecycleOwner, {
            for (i in it.indices) {
                modules.add(
                    Module(
                        it[i].moduleId,
                        it[i].moduleName,
                        it[i].moduleDescription,
                        it[i].interactive,
                        it[i].active,
                        it[i].time,
                        it[i].moduleLastCompletedDatetime,
                        it[i].moduleLastOpenDatetime,
                        it[i].moduleCompletedFlag,
                        it[i].moduleOpnedFlag
                    )
                )
            }
        })
        dataJourneyAdapter.notifyDataSetChanged()
    }

    private fun updateProgress() {
        binding.progressBar.progress = progress
        binding.tvProgress.text = "$progress%"
        binding.tvCompletedModules.text =
            getString(R.string.modules_completed, modulesCompleted, AMOUNT_OF_MODULES)
    }

    private fun moduleCompleted() {
        if (progress < 100) {
            modulesCompleted++
            progress = (modulesCompleted * 100) / AMOUNT_OF_MODULES
        }
    }
}
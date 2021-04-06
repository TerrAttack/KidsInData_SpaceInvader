package com.example.kidsindata_spaceinvader.ui.data_journey

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kidsindata_spaceinvader.model.Module
import com.example.numberskotlin.R
import com.example.numberskotlin.databinding.FragmentDataJourneyBinding
import kotlin.math.roundToInt


class DataJourneyFragment : Fragment() {

    private val viewModel: DataJourneyViewModel by activityViewModels()

    private var _binding: FragmentDataJourneyBinding? = null
    private val binding get() = _binding!!

    private val modules = arrayListOf<Module>()
    private val dataJourneyAdapter = DataJourneyAdapter(modules)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDataJourneyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getModule()
        viewModel.getNextModule()
        viewModel.getDataJourneyProgress()
        initViews()
    }

    private fun initViews() {
        binding.rvModules.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvModules.adapter = dataJourneyAdapter


        updateProgress()
        updateNextModule()
        setModulesList()

        dataJourneyAdapter.notifyDataSetChanged()
    }

    private fun setModulesList() {
        viewModel.dataJourneyModules.observe(viewLifecycleOwner, {
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
            modules.sortWith(compareBy { it.moduleId })
        })
    }

    private fun updateNextModule() {
        viewModel.dataJourneyNextModule.observe(viewLifecycleOwner, {
            binding.moduleNumber.text = it.moduleId.toString()
            binding.moduleTitle.text = it.moduleName
            binding.moduleDescription.text = it.moduleDescription

            if (it.interactive == 1)
                binding.interactiveStar.visibility = View.VISIBLE
            else
                binding.interactiveStar.visibility = View.GONE

            if (it.moduleCompletedFlag) {
                binding.completedFlag.setText(R.string.completed)
                binding.completedFlag.setBackgroundResource(R.color.green)
            } else {
                binding.completedFlag.setText(R.string.notCompleted)
                binding.completedFlag.setBackgroundResource(R.color.redKidsInData)
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun updateProgress() {
        viewModel.dataJourneyProgress.observe(viewLifecycleOwner, {
            binding.progressBar.progress = it.completedPercentage.roundToInt()
            binding.tvProgress.text = it.completedPercentage.roundToInt().toString() + "%"
            binding.tvCompletedModules.text =
                getString(R.string.modules_completed, it.modulesCompleted, it.totalActiveModules)
        })
    }
}
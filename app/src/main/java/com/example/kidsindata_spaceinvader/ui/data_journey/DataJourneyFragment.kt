package com.example.kidsindata_spaceinvader.ui.data_journey

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kidsindata_spaceinvader.MainActivity
import com.example.kidsindata_spaceinvader.model.Module
import com.example.numberskotlin.R
import com.example.numberskotlin.databinding.FragmentDataJourneyBinding
import java.util.*
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
        initViews()
    }

    private fun initViews() {
        binding.rvModules.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvModules.adapter = dataJourneyAdapter

        updateProgress()
        updateNextModule()
        setModulesList()

        binding.homeImage.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
            activity?.overridePendingTransition(R.anim.slide_from_top, R.anim.slide_to_bottom)
        }
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
            dataJourneyAdapter.notifyDataSetChanged()
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
            dataJourneyAdapter.notifyDataSetChanged()
        })
    }

    @SuppressLint("SetTextI18n")
    private fun updateProgress() {
        viewModel.dataJourneyProgress.observe(viewLifecycleOwner, {
            val mProgressBar = binding.progressBar
            val progressAnimator = ObjectAnimator.ofInt(
                mProgressBar,
                "progress",
                0,
                it.completedPercentage.roundToInt()
            )
            progressAnimator.duration = 600
            progressAnimator.setAutoCancel(true)
            progressAnimator.interpolator = LinearInterpolator()
            progressAnimator.start()

            binding.tvProgress.text = it.completedPercentage.roundToInt().toString() + "%"
            binding.tvCompletedModules.text =
                getString(R.string.modules_completed, it.modulesCompleted, it.totalActiveModules)

            dataJourneyAdapter.notifyDataSetChanged()
        })
    }
}
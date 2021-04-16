package com.example.kidsindata_spaceinvader.ui.data_journey.modules

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.kidsindata_spaceinvader.vm.DataJourneyViewModel
import com.example.numberskotlin.R
import com.example.numberskotlin.databinding.FragmentWhatIsDataModuleBinding

class WhatIsDataModuleFragment : Fragment() {

    private val viewModel: DataJourneyViewModel by activityViewModels()

    private var _binding: FragmentWhatIsDataModuleBinding? = null
    private val binding get() = _binding!!

    private var page: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWhatIsDataModuleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
        setPage(page)
    }

    private fun setClickListeners() {
        binding.backBtn.setOnClickListener {
            page -= 1
            setPage(page)
        }

        binding.nextBtn.setOnClickListener {
            page += 1
            setPage(page)
        }

        binding.backToOverview.setOnClickListener {
            findNavController().navigate(R.id.action_moduleFragment_to_dataJourneyFragment)
        }

        binding.moduleDoneBtn.setOnClickListener {
            viewModel.postModuleCompleted( 1)
            viewModel.dataJourneyModuleCompleted.observe(viewLifecycleOwner, {
                findNavController().navigate(R.id.action_moduleFragment_to_dataJourneyFragment)
            })
        }
    }

    private fun setPage(page: Int) {
        var fadeIn = AnimationUtils.loadAnimation(activity?.baseContext,R.anim.fade_in)
        binding.moduleDoneBtn.visibility = View.GONE
        when (page) {
            0 -> {
                binding.moduleBox.startAnimation(fadeIn)
                binding.tvmoduleTitle.setText(R.string.p1_title)
                binding.moduleTvBox1.setText(R.string.p1_b1)
                binding.moduleWhiteBox1.setText(R.string.p1_b2)
                binding.moduleWhiteBox2.visibility = View.GONE
                binding.moduleTvBox2.setText(R.string.p1_b3)
                binding.moduleTvBox3.visibility = View.GONE
                binding.backBtn.visibility = View.GONE
            }
            1 -> {
                binding.moduleBox.startAnimation(fadeIn)
                binding.tvmoduleTitle.setText(R.string.p2_title)
                binding.moduleTvBox1.setText(R.string.p2_b1)
                binding.moduleWhiteBox1.visibility = View.GONE
                binding.moduleWhiteBox2.visibility = View.GONE
                binding.moduleTvBox2.visibility = View.GONE
                binding.moduleTvBox3.visibility = View.GONE
                binding.backBtn.visibility = View.VISIBLE
            }
            2 -> {
                binding.moduleBox.startAnimation(fadeIn)
                binding.tvmoduleTitle.setText(R.string.p3_title)
                binding.moduleTvBox1.setText(R.string.p3_b1)
                binding.moduleWhiteBox1.visibility = View.VISIBLE
                binding.moduleWhiteBox2.visibility = View.VISIBLE
                binding.moduleTvBox2.visibility = View.VISIBLE
                binding.moduleTvBox3.visibility = View.VISIBLE
                binding.moduleWhiteBox1.setText(R.string.p3_b2)
                binding.moduleTvBox2.setText(R.string.p3_b3)
                binding.moduleWhiteBox2.setText(R.string.p3_b4)
                binding.moduleTvBox3.setText(R.string.p3_b5)
            }
            3 -> {
                binding.moduleBox.startAnimation(fadeIn)
                binding.tvmoduleTitle.setText(R.string.p4_title)
                binding.moduleTvBox1.setText(R.string.p4_b1)
                binding.moduleWhiteBox1.setText(R.string.p4_b2)
                binding.moduleTvBox2.setText(R.string.p4_b3)
                binding.moduleWhiteBox2.setText(R.string.p4_b4)
                binding.moduleTvBox3.setText(R.string.p4_b5)
                binding.moduleWhiteBox2.visibility = View.VISIBLE
                binding.moduleTvBox3.visibility = View.VISIBLE
            }
            4 -> {
                binding.moduleBox.startAnimation(fadeIn)
                binding.tvmoduleTitle.setText(R.string.p5_title)
                binding.moduleTvBox1.setText(R.string.p5_b1)
                binding.moduleWhiteBox1.setText(R.string.p5_b2)
                binding.moduleTvBox2.setText(R.string.p5_b3)
                binding.moduleWhiteBox2.visibility = View.GONE
                binding.moduleTvBox3.visibility = View.GONE
            }
            5 -> {
                binding.moduleBox.startAnimation(fadeIn)
                binding.tvmoduleTitle.setText(R.string.p6_title)
                binding.moduleTvBox1.setText(R.string.p6_b1)
                binding.moduleWhiteBox1.setText(R.string.p6_b2)
                binding.moduleTvBox2.setText(R.string.p6_b3)
            }
            6 -> {
                binding.moduleBox.startAnimation(fadeIn)
                binding.tvmoduleTitle.setText(R.string.p7_title)
                binding.moduleTvBox1.setText(R.string.p7_b1)
                binding.moduleWhiteBox1.setText(R.string.p7_b2)
                binding.moduleTvBox2.setText(R.string.p7_b3)
            }
            7 -> {
                binding.moduleBox.startAnimation(fadeIn)
                binding.tvmoduleTitle.setText(R.string.p8_title)
                binding.moduleTvBox1.setText(R.string.p8_b1)
                binding.moduleWhiteBox1.setText(R.string.p8_b2)
                binding.moduleTvBox2.setText(R.string.p8_b3)
                binding.moduleWhiteBox2.setText(R.string.p8_b4)
                binding.moduleTvBox3.setText(R.string.p8_b5)
                binding.moduleWhiteBox1.visibility = View.VISIBLE
                binding.moduleWhiteBox2.visibility = View.VISIBLE
                binding.moduleTvBox2.visibility = View.VISIBLE
                binding.moduleTvBox3.visibility = View.VISIBLE
            }
            8 -> {
                binding.moduleBox.startAnimation(fadeIn)
                binding.tvmoduleTitle.setText(R.string.p9_title)
                binding.moduleTvBox1.setText(R.string.p9_b1)
                binding.moduleWhiteBox1.visibility = View.GONE
                binding.moduleWhiteBox2.visibility = View.GONE
                binding.moduleTvBox2.visibility = View.GONE
                binding.moduleTvBox3.visibility = View.GONE
                binding.nextBtn.setText(R.string.back_to_overview)
                binding.moduleDoneBtn.visibility = View.VISIBLE
                binding.nextBtn.visibility = View.GONE

            }
        }

    }

}
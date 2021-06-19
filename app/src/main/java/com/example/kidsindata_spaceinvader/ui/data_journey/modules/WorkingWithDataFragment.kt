package com.example.kidsindata_spaceinvader.ui.data_journey.modules

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.kidsindata_spaceinvader.vm.DataJourneyViewModel
import com.example.numberskotlin.R

import com.example.numberskotlin.databinding.FragmentWorkingWithDataModuleBinding
import com.google.firebase.firestore.FirebaseFirestore

class WorkingWithDataFragment : Fragment() {

    private val viewModel: DataJourneyViewModel by activityViewModels()

    private var _binding: FragmentWorkingWithDataModuleBinding? = null
    private val binding get() = _binding!!

    private var page: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWorkingWithDataModuleBinding.inflate(inflater, container, false)
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

        binding.btnShow.setOnClickListener {
            page += 1
            setPage(page)
        }

        binding.backToOverview.setOnClickListener {
            findNavController().navigate(R.id.dataJourneyFragment)
        }

        binding.moduleDoneBtn.setOnClickListener {
            viewModel.postModuleCompleted(2)
            viewModel.dataJourneyModuleCompleted.observe(viewLifecycleOwner) {
                findNavController().navigate(R.id.action_workingWithDataFragment_to_dataJourneyFragment)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setPage(page: Int) {

        var fadeIn = AnimationUtils.loadAnimation(activity?.baseContext, R.anim.fade_in)
        binding.moduleDoneBtn.visibility = View.GONE

        //Set up Firebase
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("modules").document("2")

        docRef.get()
            .addOnSuccessListener { document ->
                when (page) {
                    0 -> {
                        binding.moduleBox.startAnimation(fadeIn)
                        binding.moduleBox.visibility = View.VISIBLE
                        binding.tvmoduleTitle.text = document.getString("p1_title")
                        binding.moduleTvBox1.text = document.getString("p1_b1") + "\n\n" +
                                "\u2022 " + document.getString("p1_b2") + "\n" +
                                "\u2022 " + document.getString("p1_b3") + "\n\n" +
                                document.getString("p1_b4")
                        binding.moduleWhiteBox1.visibility = View.GONE
                        binding.moduleWhiteBox2.visibility = View.GONE
                        binding.moduleTvBox2.visibility = View.GONE
                        binding.moduleTvBox3.visibility = View.GONE
                        binding.backBtn.visibility = View.GONE
                        binding.btnShow.visibility = View.GONE
                        binding.tableShow.visibility = View.GONE
                        binding.barShow.visibility = View.GONE
                        binding.lineShow.visibility = View.GONE
                        binding.pieShow.visibility = View.GONE
                    }
                    1 -> {
                        binding.moduleBox.startAnimation(fadeIn)
                        binding.tvmoduleTitle.text = document.getString("p2_title")
                        binding.moduleTvBox1.text = document.getString("p2_b1") + "\n\n" +
                                document.getString("p2_b2")
                        binding.moduleWhiteBox1.visibility = View.VISIBLE
                        binding.moduleWhiteBox1.text = document.getString("p2_b3")
                        binding.moduleTvBox2.visibility = View.VISIBLE
                        binding.moduleTvBox2.text = document.getString("p2_b4") + "\n\n" +
                                document.getString("p2_b5") + "\n\n" +
                                document.getString("p2_b6")
                        binding.backBtn.visibility = View.VISIBLE
                    }
                    2 -> {
                        binding.moduleBox.startAnimation(fadeIn)
                        binding.tvmoduleTitle.text = document.getString("p3_title")
                        binding.moduleTvBox1.text = document.getString("p3_b1")
                        binding.moduleWhiteBox1.text = document.getString("p3_b2")
                        binding.moduleTvBox2.text = document.getString("p3_b3") + "\n\n" +
                                document.getString("p2_b4")
                        binding.moduleWhiteBox1.visibility = View.VISIBLE
                        binding.moduleTvBox2.visibility = View.VISIBLE
                        binding.btnShow.visibility = View.GONE
                        binding.nextBtn.isEnabled = true
                        binding.whatIsDataImage.setImageResource(R.drawable.working_with_data)
                        binding.tableShow.visibility = View.GONE
                        binding.barShow.visibility = View.GONE
                        binding.lineShow.visibility = View.GONE
                        binding.pieShow.visibility = View.GONE
                    }
                    3 -> {
                        binding.tvmoduleTitle.text = document.getString("p4_title")
                        binding.moduleTvBox1.text = document.getString("p4_b1")
                        binding.moduleWhiteBox1.visibility = View.GONE
                        binding.moduleTvBox2.visibility = View.GONE
                        binding.btnShow.visibility = View.VISIBLE
                        binding.nextBtn.isEnabled = false
                        binding.whatIsDataImage.setImageResource(R.drawable.working_with_data)
                    }
                    4 -> {
                        binding.whatIsDataImage.setImageResource(R.drawable.kid_e_learn_table)
                        binding.whatIsDataImage.startAnimation(fadeIn)
                        binding.tableShow.text = "Tables"
                        binding.tableShow.visibility = View.VISIBLE
                        binding.tableShow.startAnimation(fadeIn)
                    }
                    5 -> {
                        binding.whatIsDataImage.setImageResource(R.drawable.kid_e_learn_bar_chart)
                        binding.whatIsDataImage.startAnimation(fadeIn)
                        binding.barShow.text = "Bar charts"
                        binding.barShow.visibility = View.VISIBLE
                        binding.barShow.startAnimation(fadeIn)

                    }
                    6 -> {
                        binding.whatIsDataImage.setImageResource(R.drawable.kid_e_learn_line_chart)
                        binding.whatIsDataImage.startAnimation(fadeIn)
                        binding.lineShow.startAnimation(fadeIn)
                        binding.lineShow.text = "Line charts"
                        binding.lineShow.visibility = View.VISIBLE

                    }
                    7 -> {
                        binding.whatIsDataImage.setImageResource(R.drawable.kid_e_learn_pie_chart)
                        binding.whatIsDataImage.startAnimation(fadeIn)
                        binding.btnShow.visibility = View.GONE
                        binding.nextBtn.isEnabled = true
                        binding.pieShow.text = "Pie charts"
                        binding.pieShow.visibility = View.VISIBLE
                        binding.pieShow.startAnimation(fadeIn)
                        binding.moduleWhiteBox1.visibility = View.GONE
                        binding.moduleTvBox2.visibility = View.GONE
                        binding.moduleWhiteBox2.visibility = View.GONE
                    }
                    8 -> {
                        binding.moduleBox.startAnimation(fadeIn)
                        binding.whatIsDataImage.setImageResource(R.drawable.working_with_data)
                        binding.tableShow.visibility = View.GONE
                        binding.barShow.visibility = View.GONE
                        binding.lineShow.visibility = View.GONE
                        binding.pieShow.visibility = View.GONE
                        binding.tvmoduleTitle.text = document.getString("p5_title")
                        binding.moduleTvBox1.text = document.getString("p5_b1")
                        binding.moduleWhiteBox1.text = document.getString("p5_b2") + "\n" +
                                document.getString("p5_b3") + "\n" +
                                document.getString("p5_b4") + "\n" +
                                document.getString("p5_b5")
                        binding.moduleTvBox2.text = document.getString("p5_b6")
                        binding.moduleWhiteBox2.text = document.getString("p5_b7") + "\n" +
                                document.getString("p5_b8")
                        binding.moduleWhiteBox1.visibility = View.VISIBLE
                        binding.moduleWhiteBox2.visibility = View.VISIBLE
                        binding.moduleTvBox2.visibility = View.VISIBLE
                        binding.backToMenuText.visibility = View.GONE
                        binding.nextBtn.visibility = View.VISIBLE
                        binding.moduleDoneBtn.visibility = View.GONE
                    }
                    9 -> {
                        binding.moduleBox.startAnimation(fadeIn)
                        binding.moduleWhiteBox1.visibility = View.GONE
                        binding.moduleWhiteBox2.visibility = View.GONE
                        binding.moduleTvBox2.visibility = View.GONE
                        binding.nextBtn.visibility = View.GONE
                        binding.tvmoduleTitle.text = document.getString("p6_title")
                        binding.moduleTvBox1.text = document.getString("p6_b1") + "\n" +
                                document.getString("p6_b2") + "\n\n" +
                                "\u2022 " + document.getString("p6_b3") + "\n" +
                                "\u2022 " + document.getString("p6_b4") + "\n" +
                                "\u2022 " + document.getString("p6_b5") + "\n" +
                                "\u2022 " + document.getString("p6_b6")
                        binding.backToMenuText.visibility = View.VISIBLE
                        binding.backToMenuText.text = document.getString("p6_b7")
                        binding.moduleDoneBtn.visibility = View.VISIBLE
                    }
                }
            }
    }
}
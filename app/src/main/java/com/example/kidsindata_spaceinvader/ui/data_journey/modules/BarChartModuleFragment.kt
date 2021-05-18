package com.example.kidsindata_spaceinvader.ui.data_journey.modules

import android.annotation.SuppressLint
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
import com.example.numberskotlin.databinding.FragmentBarChartModuleBinding
import com.example.numberskotlin.databinding.FragmentWhatIsDataModuleBinding
import com.google.firebase.firestore.FirebaseFirestore

class BarChartModuleFragment : Fragment() {
    private val viewModel: DataJourneyViewModel by activityViewModels()

    private var _binding: FragmentBarChartModuleBinding? = null
    private val binding get() = _binding!!

    private var page: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBarChartModuleBinding.inflate(inflater, container, false)
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
            findNavController().navigate(R.id.dataJourneyFragment)
        }

        binding.moduleDoneBtn.setOnClickListener {
            viewModel.postModuleCompleted(4)
            viewModel.dataJourneyModuleCompleted.observe(viewLifecycleOwner, {
                findNavController().navigate(R.id.action_barChartModule_to_dataJourneyFragment)
            })
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setPage(page: Int) {
        var fadeIn = AnimationUtils.loadAnimation(activity?.baseContext, R.anim.fade_in)
        binding.moduleDoneBtn.visibility = View.GONE

        //Setup Firebase
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("modules").document("4")
        docRef.get()
            .addOnSuccessListener { document ->
                when (page) {
                    0 -> {
                        binding.moduleBox.visibility = View.VISIBLE
                        binding.moduleBox.startAnimation(fadeIn)
                        binding.tvmoduleTitle.text = document.getString("p1_title")
                        binding.moduleTvBox1.text = document.getString("p1_b1") + "\n\n" +
                                document.getString("p1_b2") + "\n\n" + document.getString("p1_b3") + "\n\n" +
                                document.getString("p1_b4")
                        binding.moduleWhiteBox1.visibility = View.GONE
                        binding.moduleWhiteBox2.visibility = View.GONE
                        binding.moduleWhiteBox3.visibility = View.GONE
                        binding.moduleWhiteBox4.visibility = View.GONE
                        binding.backBtn.visibility = View.GONE
                    }
                    1 -> {
                        binding.moduleBox.visibility = View.VISIBLE
                        binding.moduleBox.startAnimation(fadeIn)
                        binding.tvmoduleTitle.text = document.getString("p2_title")
                        binding.moduleTvBox1.text = document.getString("p2_b1")
                        binding.moduleWhiteBox1.text = document.getString("p2_b2")
                        binding.moduleWhiteBox2.text = document.getString("p2_b4")
                        binding.moduleWhiteBox3.text = document.getString("p2_b6")
                        binding.moduleWhiteBox4.text = document.getString("p2_b8")
                        binding.moduleWhiteBox1.visibility = View.VISIBLE
                        binding.moduleWhiteBox2.visibility = View.VISIBLE
                        binding.moduleWhiteBox3.visibility = View.VISIBLE
                        binding.moduleWhiteBox4.visibility = View.VISIBLE
                        binding.backBtn.visibility = View.VISIBLE
                    }
                    2 -> {
                        binding.tvmoduleTitle.text = document.getString("p3_title")
                        binding.moduleTvBox1.text = document.getString("p3_b1")
                        binding.moduleWhiteBox2.text = document.getString("p3_b2")
                        binding.moduleWhiteBox1.visibility = View.GONE
                        binding.moduleWhiteBox3.visibility = View.GONE
                        binding.moduleWhiteBox4.visibility = View.GONE

                    }
                    3 -> {
                        binding.tvmoduleTitle.text = document.getString("p4_title")
                        binding.moduleTvBox1.text = document.getString("p4_b1")
                        binding.moduleWhiteBox2.text = document.getString("p4_b2")
                        binding.moduleWhiteBox1.visibility = View.GONE
                        binding.moduleWhiteBox2.visibility = View.VISIBLE
                    }
                    4 -> {
                        binding.moduleBox.startAnimation(fadeIn)
                        binding.tvmoduleTitle.text = document.getString("p5_title")
                        binding.moduleTvBox1.text = document.getString("p5_b1") + "\n\n" +
                                document.getString("p5_b2") + "\n\n" + document.getString("p5_b3")
                        binding.moduleWhiteBox1.visibility = View.VISIBLE
                        binding.moduleWhiteBox2.visibility = View.GONE
                        binding.moduleWhiteBox1.text = document.getString("p5_b4")
                    }
                    5 -> {
                        binding.moduleBox.startAnimation(fadeIn)
                        binding.tvmoduleTitle.text = document.getString("p6_title")
                        binding.moduleTvBox1.text = document.getString("p6_b1") + "\n\n" +
                                document.getString("p6_b2")
                        binding.moduleWhiteBox1.text = document.getString("p6_b3")
                        binding.moduleWhiteBox1.visibility = View.VISIBLE
                        binding.moduleDoneBtn.visibility = View.GONE
                        binding.nextBtn.visibility = View.VISIBLE
                        binding.backToMenuText.visibility = View.GONE
                    }
                    6 -> {
                        binding.moduleBox.startAnimation(fadeIn)
                        binding.tvmoduleTitle.text = document.getString("p7_title")
                        binding.moduleTvBox1.text = document.getString("p7_b1") + "\n\n" +
                                "\u2022 " + document.getString("p7_b2") + "\n" +
                                "\u2022 " + document.getString("p7_b3") + "\n" +
                                "\u2022 " + document.getString("p7_b4") + "\n" +
                                "\u2022 " + document.getString("p7_b5") + "\n" +
                                "\u2022 " + document.getString("p7_b6") + "\n"
                        binding.moduleWhiteBox1.visibility = View.GONE
                        binding.moduleDoneBtn.visibility = View.VISIBLE
                        binding.nextBtn.visibility = View.GONE
                        binding.backToMenuText.visibility = View.VISIBLE
                        binding.backToMenuText.text = document.getString("p9_b6")
                        binding.moduleWhiteBox1.visibility = View.GONE
                    }

                }
            }
    }
}
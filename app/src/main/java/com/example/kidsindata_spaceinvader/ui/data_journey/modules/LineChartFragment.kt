package com.example.kidsindata_spaceinvader.ui.data_journey.modules

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.kidsindata_spaceinvader.vm.DashboardViewModel
import com.example.kidsindata_spaceinvader.vm.DataJourneyViewModel
import com.example.numberskotlin.R
import com.example.numberskotlin.databinding.FragmentLineChartModuleBinding
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.firebase.firestore.FirebaseFirestore

class LineChartFragment : Fragment() {

    private val viewModel: DataJourneyViewModel by activityViewModels()

    private var _binding: FragmentLineChartModuleBinding? = null
    private val binding get() = _binding!!

    private var page: Int = 0

    private val dashboardViewModel: DashboardViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLineChartModuleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dashboardViewModel.getScoringTrend()
        setLineChart()
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
            viewModel.postModuleCompleted(5)
            viewModel.dataJourneyModuleCompleted.observe(viewLifecycleOwner) {
                findNavController().navigate(R.id.action_lineChartFragment_to_dataJourneyFragment)
            }
        }
    }

    private fun setLineChart() {
        dashboardViewModel.playerScoringTrend.observe(viewLifecycleOwner, Observer {
            val entries: ArrayList<Entry> = arrayListOf()

            for (i in it.indices) {
                entries.add(Entry(it[i].game.toFloat(), it[i].playerScore.toFloat()))
            }

            var dataSet = LineDataSet(entries, "Test")
            dataSet.fillColor = R.color.titleKidsInData
            dataSet.valueTextSize = 10f
            dataSet.setColors(
                intArrayOf(
                    R.color.titleKidsInData,
                ), context
            )
            dataSet.valueTextColor = R.color.titleKidsInData
            dataSet.lineWidth = 1f
            dataSet.valueTextColor = R.color.redKidsInData

            var legened: Legend = binding.lineChartModule.legend

            legened.isEnabled = false

            val lineData = LineData(dataSet)
            binding.lineChartModule.data = lineData


            binding.lineChartModule.axisRight.isEnabled = false;

            binding.lineChartModule.description.text = ""

            binding.lineChartModule.getAxis(YAxis.AxisDependency.LEFT)

            val xAxis: XAxis = binding.lineChartModule.xAxis
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.textSize = 10f
            xAxis.textColor = R.color.titleKidsInData
            xAxis.setDrawAxisLine(true)
            xAxis.axisMaximum = 5f

            binding.lineChartModule.invalidate() // refresh

        })
    }


    @SuppressLint("SetTextI18n")
    private fun setPage(page: Int) {
        var fadeIn = AnimationUtils.loadAnimation(activity?.baseContext, R.anim.fade_in)
        binding.moduleDoneBtn.visibility = View.GONE

        //Setup Firebase
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("modules").document("5")
        docRef.get()
            .addOnSuccessListener { document ->
                when (page) {
                    0 -> {
                        binding.moduleBox.visibility = View.VISIBLE
                        binding.moduleBox.startAnimation(fadeIn)
                        binding.tvmoduleTitle.text = document.getString("p1_title")
                        binding.moduleTvBox1.text = document.getString("p1_b1")
                        binding.moduleWhiteBox1.visibility = View.GONE
                        binding.moduleWhiteBox2.visibility = View.GONE
                        binding.moduleWhiteBox3.visibility = View.GONE
                        binding.moduleWhiteBox4.visibility = View.GONE
                        binding.moduleWhiteBox5.visibility = View.GONE
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
                        binding.moduleWhiteBox5.text = document.getString("p2_b10")
                        binding.moduleWhiteBox1.visibility = View.VISIBLE
                        binding.moduleWhiteBox2.visibility = View.VISIBLE
                        binding.moduleWhiteBox3.visibility = View.VISIBLE
                        binding.moduleWhiteBox4.visibility = View.VISIBLE
                        binding.moduleWhiteBox5.visibility = View.VISIBLE
                        binding.backBtn.visibility = View.VISIBLE
                    }
                    2 -> {
                        binding.tvmoduleTitle.text = document.getString("p3_title")
                        binding.moduleTvBox1.text = document.getString("p3_b1")
                        binding.moduleWhiteBox2.text = document.getString("p3_b2") + "\n\n" +
                                document.getString("p3_b3")
                        binding.moduleWhiteBox1.visibility = View.GONE
                        binding.moduleWhiteBox3.visibility = View.GONE
                        binding.moduleWhiteBox4.visibility = View.GONE
                        binding.moduleWhiteBox5.visibility = View.GONE
                    }
                    3 -> {
                        binding.tvmoduleTitle.text = document.getString("p4_title")
                        binding.moduleTvBox1.text = document.getString("p4_b1")
                        binding.moduleWhiteBox2.text = document.getString("p4_b2") + "\n\n" +
                                document.getString("p4_b3")
                        binding.moduleWhiteBox2.visibility = View.VISIBLE
                        binding.moduleWhiteBox1.visibility = View.GONE
                    }
                    4 -> {
                        binding.moduleBox.startAnimation(fadeIn)
                        binding.tvmoduleTitle.text = document.getString("p5_title")
                        binding.moduleTvBox1.text = document.getString("p5_b1") + "\n\n" +
                                document.getString("p5_b2") + "\n\n" + document.getString("p5_b3")
                        binding.moduleWhiteBox1.visibility = View.VISIBLE
                        binding.moduleWhiteBox2.visibility = View.GONE
                        binding.moduleWhiteBox1.text = document.getString("p5_b4")
                        binding.moduleDoneBtn.visibility = View.GONE
                        binding.nextBtn.visibility = View.VISIBLE
                        binding.backToMenuText.visibility = View.GONE
                    }
                    5 -> {
                        binding.moduleBox.startAnimation(fadeIn)
                        binding.tvmoduleTitle.text = document.getString("p6_title")
                        binding.moduleTvBox1.text = document.getString("p6_b1") + "\n\n" +
                                "\u2022 " + document.getString("p6_b2") + "\n" +
                                "\u2022 " + document.getString("p6_b3") + "\n" +
                                "\u2022 " + document.getString("p6_b4") + "\n" +
                                "\u2022 " + document.getString("p6_b5") + "\n" +
                                "\u2022 " + document.getString("p6_b6") + "\n"
                        binding.moduleWhiteBox1.visibility = View.GONE
                        binding.moduleDoneBtn.visibility = View.VISIBLE
                        binding.nextBtn.visibility = View.GONE
                        binding.backToMenuText.visibility = View.VISIBLE
                        binding.backToMenuText.text = document.getString("p6_b7")
                    }
                }
            }
    }
}
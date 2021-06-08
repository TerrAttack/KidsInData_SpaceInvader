package com.example.kidsindata_spaceinvader.ui.dashboard

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.kidsindata_spaceinvader.vm.DashboardViewModel
import com.example.numberskotlin.R
import com.example.numberskotlin.databinding.FragmentControlsBinding
import com.example.numberskotlin.databinding.FragmentDashboardBinding
import com.example.numberskotlin.databinding.FragmentDetailScoreBinding
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*

class DetailScoreFragment(var username: String) : DialogFragment() {

    private val dashboardViewModel: DashboardViewModel by activityViewModels()

    private var _binding: FragmentDetailScoreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailScoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dashboardViewModel.getScoringTrendUsers(username)

        setLineChart()
        setBarChart()
        switchChart()
        binding.barChart.visibility = View.GONE

        binding.closeDetail.setOnClickListener {
            dismiss()
        }
    }

    private fun switchChart() {
        var fadeIn = AnimationUtils.loadAnimation(activity?.baseContext, R.anim.fade_in)

        binding.lineChartBtn.setOnClickListener {
            binding.lineChart.startAnimation(fadeIn)
            binding.barChart.visibility = View.GONE
            binding.lineChart.visibility = View.VISIBLE
            binding.barChartBtn.setBackgroundResource(R.drawable.button_chart_border)
            binding.lineChartBtn.setBackgroundResource(R.drawable.button_chart_border_seleted)
            binding.barChartBtn.setTextColor(Color.GRAY)
            binding.lineChartBtn.setTextColor(Color.WHITE)
        }

        binding.barChartBtn.setOnClickListener {
            binding.barChart.startAnimation(fadeIn)
            binding.barChart.visibility = View.VISIBLE
            binding.lineChart.visibility = View.GONE
            binding.barChartBtn.setBackgroundResource(R.drawable.button_chart_border_seleted)
            binding.lineChartBtn.setBackgroundResource(R.drawable.button_chart_border)
            binding.barChartBtn.setTextColor(Color.WHITE)
            binding.lineChartBtn.setTextColor(Color.GRAY)
        }
    }

    private fun setLineChart() {
        binding.scoringTrendUser.text = getString(R.string.scoring_trend_for_s, username.substringBefore("-"))

        dashboardViewModel.playerScoringTrendUsers.observe(viewLifecycleOwner, Observer {
            val entries: ArrayList<Entry> = arrayListOf()

            for (i in it.indices) {
                entries.add(Entry(it[i].game.toFloat(), it[i].playerScore.toFloat()))
            }

            var dataSet = LineDataSet(entries, "Dialog")
            dataSet.fillColor = R.color.titleKidsInData
            dataSet.valueTextSize = 8f
            dataSet.setColors(
                intArrayOf(
                    R.color.titleKidsInData,
                ), context
            )
            dataSet.valueTextColor = R.color.titleKidsInData
            dataSet.lineWidth = 1f
            dataSet.valueTextColor = R.color.redKidsInData

            var legened: Legend = binding.lineChart.legend

            legened.isEnabled = false

            val lineData = LineData(dataSet)
            binding.lineChart.data = lineData


            binding.lineChart.axisRight.isEnabled = false;

            binding.lineChart.description.text = ""

            binding.lineChart.getAxis(YAxis.AxisDependency.LEFT)

            val xAxis: XAxis = binding.lineChart.xAxis
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.textSize = 8f
            xAxis.textColor = R.color.titleKidsInData
            xAxis.axisMaximum = 5f

            binding.lineChart.invalidate() // refresh

        })
    }

    private fun setBarChart() {
        dashboardViewModel.playerScoringTrendUsers.observe(viewLifecycleOwner, Observer {
            val entries: ArrayList<BarEntry> = arrayListOf()

            for (i in it.indices) {
                entries.add(BarEntry(it[i].game.toFloat(), it[i].playerScore.toFloat()))
            }

            var dataSet = BarDataSet(entries, "Test")
            dataSet.valueTextColor = R.color.redKidsInData;
            dataSet.valueTextSize = 8f
            dataSet.setColors(
                intArrayOf(
                    R.color.titleKidsInData,
                ), context
            )


            val barData = BarData(dataSet)
            binding.barChart.data = barData

            var legened: Legend = binding.barChart.legend

            legened.isEnabled = false

            binding.barChart.axisRight.isEnabled = false;

            binding.barChart.description.text = ""

            binding.barChart.getAxis(YAxis.AxisDependency.LEFT)

            val xAxis: XAxis = binding.barChart.xAxis
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.textSize = 8f
            xAxis.textColor = R.color.titleKidsInData
            xAxis.setCenterAxisLabels(true)
            xAxis.setDrawAxisLine(true)

            binding.barChart.invalidate() // refresh
        })
    }

}
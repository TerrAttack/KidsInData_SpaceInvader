package com.example.kidsindata_spaceinvader.ui.dashboard

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kidsindata_spaceinvader.global_var.Global
import com.example.kidsindata_spaceinvader.model.Module
import com.example.kidsindata_spaceinvader.vm.DashboardViewModel
import com.example.numberskotlin.databinding.FragmentDashboardBinding
import com.example.kidsindata_spaceinvader.model.TopScore
import com.example.kidsindata_spaceinvader.ui.data_journey.DataJourneyAdapter
import com.example.kidsindata_spaceinvader.ui.explanation.ControlsDialogFragment
import com.example.kidsindata_spaceinvader.vm.TrophiesViewModel
import com.example.numberskotlin.R
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*

class DashboardFragment : Fragment() {
    private val viewModelThrophy: TrophiesViewModel by activityViewModels()

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private val topScores = arrayListOf<TopScore>()
    private val dashboardTopScoreAdapter =
        DashboardTopScoreAdapter(topScores) { topScore: TopScore -> topScoreItemClick(topScore) }

    private val dashboardViewModel: DashboardViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dashboardViewModel.getScoringTrend()
        dashboardViewModel.getTopTenScores()
        setLineChart()
        setBarChart()
        switchChart()
        initViews()
        observeValues()
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

    private fun initViews() {
        binding.rvtTopPlayers.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvtTopPlayers.adapter = dashboardTopScoreAdapter
        binding.barChart.visibility = View.GONE
        setTopTenScores()
        setTopScore()
        setRanking()
        setGamesPlayed()
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

            var legened: Legend = binding.lineChart.legend

            legened.isEnabled = false

            val lineData = LineData(dataSet)
            binding.lineChart.data = lineData


            binding.lineChart.axisRight.isEnabled = false;

            binding.lineChart.description.text = ""

            binding.lineChart.getAxis(YAxis.AxisDependency.LEFT)

            val xAxis: XAxis = binding.lineChart.xAxis
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.textSize = 10f
            xAxis.textColor = R.color.titleKidsInData
            xAxis.setDrawAxisLine(true)

            binding.lineChart.invalidate() // refresh

        })
    }

    private fun setBarChart() {
        dashboardViewModel.playerScoringTrend.observe(viewLifecycleOwner, Observer {
            val entries: ArrayList<BarEntry> = arrayListOf()

            for (i in it.indices) {
                entries.add(BarEntry(it[i].game.toFloat(), it[i].playerScore.toFloat()))
            }

            var dataSet = BarDataSet(entries, "Test")
            dataSet.valueTextColor = R.color.redKidsInData;
            dataSet.valueTextSize = 10f
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
            xAxis.textSize = 10f
            xAxis.textColor = R.color.titleKidsInData
            xAxis.setCenterAxisLabels(true)
            xAxis.setDrawAxisLine(true)

            binding.barChart.invalidate() // refresh
        })
    }

    private fun setTopTenScores() {
        dashboardViewModel.dashboardTopScore.observe(viewLifecycleOwner) {
            topScores.clear()
            for (i in it.indices) {
                topScores.add(
                    TopScore(
                        it[i].gameId,
                        it[i].playerName,
                        it[i].playerUserName,
                        it[i].playerScore,
                        it[i].playedDateTime,
                        it[i].gameDuration,
                        it[i].playerAvatar,
                        it[i].playerAvatarId
                    )
                )
            }
            dashboardTopScoreAdapter.notifyDataSetChanged()
        }
    }


    private fun setTopScore() {
        viewModelThrophy.trophiesTopScore.observe(viewLifecycleOwner) {
            binding.tvTopScore.text = it.toString()
        }
    }

    private fun setRanking() {
        viewModelThrophy.trophiesPlayerRank.observe(viewLifecycleOwner) {
            binding.tvRanking.text = it.toString()
        }
    }

    private fun observeValues() {
        dashboardViewModel.playerScoringTrend.observe(viewLifecycleOwner) {
            //            binding.textView13.text = it.size.toString()
        }

        // Observe the error message.
        dashboardViewModel.errorText.observe(viewLifecycleOwner) {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setGamesPlayed() {
        viewModelThrophy.trophiesGameSummary.observe(viewLifecycleOwner) {
            binding.tvGamesPlayed.text = it.noOfGames.toString()
        }
    }

    private fun topScoreItemClick(topScore: TopScore) {
        DetailScoreFragment(topScore.playerUserName).show(parentFragmentManager, "Detail topscore")

    }
}
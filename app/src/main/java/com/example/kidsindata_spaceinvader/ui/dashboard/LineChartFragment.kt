package com.example.kidsindata_spaceinvader.ui.dashboard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.kidsindata_spaceinvader.model.ScoringTrend
import com.example.kidsindata_spaceinvader.vm.DashboardViewModel
import com.example.numberskotlin.databinding.FragmentDashboardBinding
import com.example.numberskotlin.databinding.FragmentDashboardGraphBinding
import io.data2viz.charts.chart.Chart
import io.data2viz.charts.chart.chart
import io.data2viz.charts.chart.discrete
import io.data2viz.charts.chart.mark.line
import io.data2viz.charts.chart.quantitative
import io.data2viz.geom.Size
import io.data2viz.timer.now
import io.data2viz.viz.VizContainerView

class LineChartFragment: Fragment() {
    private var _binding: FragmentDashboardGraphBinding? = null
    private val binding get() = _binding!!

    private val dashboardViewModel: DashboardViewModel by activityViewModels()


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDashboardGraphBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val drawChart = DrawChart(requireContext())
        dashboardViewModel.playerScoringTrend.observe(viewLifecycleOwner, Observer {
             drawChart.scores = it
        })


        binding.graphContainer.addView(drawChart)


    }
}

class DrawChart(context: Context) : VizContainerView(context) {

    private val vizSize = 500.0

    var scores = arrayListOf<ScoringTrend>()

    private val chart: Chart<ScoringTrend> = chart(scores) {
        size = Size(vizSize, vizSize)
        title = "Scoring trends of player"

        // Create a discrete dimension for the year of the census
        val gameNumber = discrete({ domain.game })

        // Create a continuous numeric dimension for the population
        val gameScore = quantitative({ domain.playerScore })

        // Using a discrete dimension for the X-axis and a continuous one for the Y-axis
        line(gameNumber, gameScore)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        chart.size = Size(vizSize, vizSize * h / w)
    }
}
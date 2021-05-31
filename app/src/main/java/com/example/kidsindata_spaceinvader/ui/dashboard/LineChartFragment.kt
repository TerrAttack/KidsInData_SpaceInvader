package com.example.kidsindata_spaceinvader.ui.dashboard

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kidsindata_spaceinvader.api.KidsInDataApi
import com.example.kidsindata_spaceinvader.api.KidsInDataApiService
import com.example.kidsindata_spaceinvader.global_var.Global
import com.example.kidsindata_spaceinvader.model.ScoringTrend
import com.example.kidsindata_spaceinvader.model.TopScore
import com.example.kidsindata_spaceinvader.repository.DashboardRepository
import com.example.kidsindata_spaceinvader.vm.DashboardViewModel
import com.example.numberskotlin.BuildConfig
import com.example.numberskotlin.databinding.FragmentDashboardBinding
import io.data2viz.charts.chart.Chart
import io.data2viz.charts.chart.chart
import io.data2viz.charts.chart.discrete
import io.data2viz.charts.chart.mark.line
import io.data2viz.charts.chart.quantitative
import io.data2viz.geom.Size
import io.data2viz.timer.now
import io.data2viz.viz.VizContainerView
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout

class LineChartFragment: Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return DrawChart(this.requireContext())
    }
}

class DrawChart(context: Context) : VizContainerView(context) {

    private val vizSize = 500.0

    private val scores = listOf(
            ScoringTrend(1, now().toString(), 100.0),
            ScoringTrend(2, now().toString(), 150.0),
            ScoringTrend(3, now().toString(), 160.0),
            ScoringTrend(4, now().toString(), 110.0),
            ScoringTrend(5, now().toString(), 120.0),
            ScoringTrend(6, now().toString(), 140.0)
    )

    private val chart: Chart<ScoringTrend> = chart(scores) {
        size = Size(vizSize, vizSize)
        title = "Scoring trends of player"

        // Create a discrete dimension for the year of the census
        val gameNumber = discrete({ domain.game }) {
            name = "Game"
        }

        // Create a continuous numeric dimension for the population
        val gameScore = quantitative({ domain.playerScore }) {
            name = "Score per game"
        }

        // Using a discrete dimension for the X-axis and a continuous one for the Y-axis
        line(gameNumber, gameScore)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        chart.size = Size(vizSize, vizSize * h / w)
    }
}
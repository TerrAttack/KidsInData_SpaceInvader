package com.example.kidsindata_spaceinvader.ui.dashboard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import com.example.kidsindata_spaceinvader.model.ScoringTrend
import com.example.kidsindata_spaceinvader.vm.DashboardViewModel
import io.data2viz.charts.chart.Chart
import io.data2viz.charts.chart.chart
import io.data2viz.charts.chart.discrete
import io.data2viz.charts.chart.mark.line
import io.data2viz.charts.chart.quantitative
import io.data2viz.geom.Size
import io.data2viz.viz.VizContainerView


class LineChartFragment: Fragment() {

    inner class DrawChart(context: Context) : VizContainerView(context){
        val viewModel: DashboardViewModel by activityViewModels()

        private val vizSize = 500.0
//        private val scores = arrayListOf<ScoringTrend>()


        private fun setScoringTrend(): ArrayList<ScoringTrend> {
            val scores = arrayListOf<ScoringTrend>()
            viewModel.playerScoringTrend.observe(viewLifecycleOwner) {
                for (i in it.indices) {
                    scores.add(
                        ScoringTrend(
                            it[i].game,
                            it[i].gameDateTime,
                            it[i].playerScore
                        )
                    )
                }
            }
            return scores
        }


        private val chart: Chart<ScoringTrend> = chart(setScoringTrend()) {
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
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return DrawChart(this.requireContext())
    }

}


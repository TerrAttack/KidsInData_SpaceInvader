package com.example.kidsindata_spaceinvader.charts

import android.content.Context
import io.data2viz.charts.chart.*
import io.data2viz.charts.chart.mark.*
import io.data2viz.geom.*
import io.data2viz.viz.VizContainerView

const val vizSize = 400.0

class DashboardCharts(context: Context) : VizContainerView(context) {
    val barChart: Chart<ScoreCount> = chart(gameScore) {
        size = Size(vizSize, vizSize)
        title = "Test"

        val gameNumber = discrete({ domain.gameNumber })

        val gameScore = quantitative({ domain.gameScore })

        area(gameNumber, gameScore)
    }
}

data class ScoreCount(val gameNumber: Int, val gameScore: Double)

val gameScore = listOf(
    ScoreCount(1, 1000.0),
    ScoreCount(2, 2000.0),
    ScoreCount(3, 3000.0),
    ScoreCount(4, 3000.0),
    ScoreCount(5, 2000.0),
    ScoreCount(6, 7000.0),
    ScoreCount(7, 6000.0),
    ScoreCount(8, 5000.0),
    ScoreCount(9, 0.0),
    ScoreCount(10, 10000.0),
    ScoreCount(11, 5000.0),
    ScoreCount(12, 7000.0),
    ScoreCount(13, 3000.0),
    ScoreCount(14, 5000.0),
    ScoreCount(15, 3000.0),
    ScoreCount(16, 5000.0)
)

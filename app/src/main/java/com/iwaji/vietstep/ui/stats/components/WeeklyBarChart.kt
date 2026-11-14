package com.iwaji.vietstep.ui.stats.components

import kotlin.collections.map
import kotlin.collections.mapIndexed
import kotlin.collections.maxOrNull

import android.graphics.Color as AndroidColor
import android.view.View

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter

@Composable
fun WeeklyBarChart(
    weeklyData: List<Pair<String, Int>>
) {
    val labels = weeklyData.map { it.first }
    val values = weeklyData.map { it.second.toFloat() }

    AndroidView(
        factory = { context ->
            BarChart(context).apply {
                id = View.generateViewId()
                description = Description().apply { text = "" }
                axisRight.isEnabled = false
                xAxis.granularity = 1f
            }
        },
        update = { chart ->
            val maxValue = values.maxOrNull() ?: 0f

            val entries = values.mapIndexed { index, v ->
                BarEntry(index.toFloat(), v)
            }

            val dataSet = BarDataSet(entries, "").apply {
                colors = values.map { v ->
                    if (v == maxValue) AndroidColor.rgb(255, 87, 34)  // 最大値だけオレンジ
                    else AndroidColor.rgb(33, 150, 243)               // 通常バーは青
                }
                valueTextSize = 14f
                valueFormatter = object : ValueFormatter() {
                    override fun getFormattedValue(value: Float): String {
                        return value.toInt().toString()
                    }
                }
            }

            chart.data = BarData(dataSet)
            chart.xAxis.valueFormatter = IndexAxisValueFormatter(labels)
            chart.xAxis.setLabelCount(labels.size, true)
            chart.invalidate()
            chart.animateY(1000)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    )

}

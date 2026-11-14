package com.iwaji.vietstep.ui.stats.components

import android.view.View
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import android.graphics.Color as AndroidColor

@Composable
fun PieLevelChart(
    levelCounts: List<Int>
) {
        AndroidView(
            factory = { context ->
                PieChart(context).apply {
                    id = View.generateViewId()
                    description = Description().apply { text = "" }
                }
            },
            update = { chart ->
                val entries = listOf(
                    PieEntry(levelCounts[4].toFloat(), "Lv5"),
                    PieEntry(levelCounts[3].toFloat(), "Lv4"),
                    PieEntry(levelCounts[2].toFloat(), "Lv3"),
                    PieEntry(levelCounts[1].toFloat(), "Lv2"),
                    PieEntry(levelCounts[0].toFloat(), "Lv1")
                )

                val pastelColors = listOf(
                    AndroidColor.rgb(244, 143, 177), // ピンク
                    AndroidColor.rgb(129, 212, 250), // 水色
                    AndroidColor.rgb(165, 214, 167), // 緑
                    AndroidColor.rgb(255, 204, 128), // オレンジ
                    AndroidColor.rgb(206, 147, 216)  // 紫
                )

                val dataSet = PieDataSet(entries, "").apply {
                    colors = pastelColors
                    valueTextSize = 16f
                }

                chart.data = PieData(dataSet)
                chart.centerText = "理解度"
                chart.invalidate()
                chart.animateY(1000)
                chart.setCenterTextSize(24f)
                chart.setCenterTextColor(AndroidColor.BLACK)
                chart.isRotationEnabled = false
                chart.isHighlightPerTapEnabled = false
                chart.legend.isEnabled = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )

        Spacer(Modifier.height(12.dp))
        Text("Lv5: ${levelCounts[4]}")
        Text("Lv4: ${levelCounts[3]}")
        Text("Lv3: ${levelCounts[2]}")
        Text("Lv2: ${levelCounts[1]}")
        Text("Lv1: ${levelCounts[0]}")
}

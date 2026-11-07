package com.iwaji.vietstep.ui.stats

import android.view.View
import android.graphics.Color as AndroidColor

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults

import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter

import com.iwaji.vietstep.data.model.DailyStat
import com.iwaji.vietstep.ui.chunk.ChunkUiState
import com.iwaji.vietstep.ui.stats.components.TodayResultBar


@Composable
fun ChunkStatsPage(
    uiState: ChunkUiState,
    todayStat: DailyStat?,
    weeklyData: List<Pair<String, Int>>
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // -----------------------------
        // ✅ 今日の結果（割合バー）
        // -----------------------------

        Text("\uD83D\uDCCA 今日の統計", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        Spacer(Modifier.height(16.dp))

        TodayResultBar(todayStat)

        Spacer(Modifier.height(16.dp))

        // -----------------------------
        // ✅ 週間学習数（BarChart）
        // -----------------------------
        Spacer(Modifier.height(24.dp))
        Text("\uD83D\uDCC5 週間学習数", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        val labels = weeklyData.map { it.first }
        val values = weeklyData.map { it.second.toFloat() }
        val totalWeek = values.sum()

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                if (totalWeek > 0f) {
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
                } else {
                    Text("週間学習データがありません")
                }
            }
        }

        // -----------------------------
        // ✅ チャンクレベル分布（PieChart）
        // -----------------------------
        Spacer(Modifier.height(24.dp))
        Text("\uD83D\uDCDA チャンクレベル分布", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                if (uiState.chunks.isNotEmpty()) {
                    val levelCounts = IntArray(5)
                    uiState.chunks.forEach {
                        val level = it.level.coerceIn(1, 5)
                        levelCounts[level - 1]++
                    }

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
                } else {
                    Text("チャンクデータがありません")
                }
            }
        }
    }
}

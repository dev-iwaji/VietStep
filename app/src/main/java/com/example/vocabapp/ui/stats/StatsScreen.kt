package com.example.vocabapp.ui.stats
import android.util.Log

import android.view.View

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import android.graphics.Color as AndroidColor

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

import kotlin.math.max

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.getValue

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember

import androidx.compose.ui.text.font.FontWeight

import com.example.vocabapp.ui.word.WordViewModel
import com.example.vocabapp.data.model.DailyStat

@Composable
fun StatsScreen(
    wordViewModel: WordViewModel,
    refreshKey: Int
) {

    val uiState by
    wordViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // -----------------------------
        // 今日の結果（割合バー）
        // -----------------------------
        val todayStat =
            remember(refreshKey) {
                wordViewModel.getTodayStat()
            }

        Text("\uD83D\uDCCA 今日の統計", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        Spacer(Modifier.height(16.dp))

        TodayResultBar(todayStat)

        Spacer(Modifier.height(16.dp))

        // -----------------------------
        // 週間学習数（BarChart）
        // -----------------------------
        Spacer(Modifier.height(24.dp))
        Text("\uD83D\uDCC5 週間学習数", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        val weeklyData =
            remember (refreshKey) {
                wordViewModel.getLast7DaysData()
            }
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
        // 単語レベル分布（PieChart）
        // -----------------------------
        Spacer(Modifier.height(24.dp))
        Text("\uD83D\uDCDA 単語レベル分布", fontSize = 20.sp, fontWeight = FontWeight.Bold)

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
                if (uiState.words.isNotEmpty()) {
                    val levelCounts = IntArray(5)
                    uiState.words.forEach {
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
                    Text("単語データがありません")
                }
            }
        }
    }
}

@Composable
fun TodayResultBar(todayStat: DailyStat?) {

    val correct = todayStat?.correct ?:0
    val incorrect = todayStat?.incorrect ?:0
    val total = correct + incorrect
    if (total == 0) {
        Text("今日の統計データがありません")
        return
    }

    val correctRatio = correct.toFloat() / total

    val animatedCorrect by animateFloatAsState(
        targetValue = correctRatio,
        animationSpec = tween(durationMillis = 1200)
    )
    val animatedIncorrect = 1f - animatedCorrect

    val safeCorrect = max(animatedCorrect, 0.0001f)
    val safeIncorrect = max(animatedIncorrect, 0.0001f)

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
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color.LightGray)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(safeCorrect)
                        .background(Color(0xFF4CAF50))
                )
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(safeIncorrect)
                        .background(Color(0xFFF44336))
                )
            }

            Spacer(Modifier.height(12.dp))

            Text(
                "正解 $correct (${ "%.1f".format(correctRatio * 100) }%) : ミス $incorrect (${ "%.1f".format((1 - correctRatio) * 100) }%)",
                fontSize = 16.sp
            )
        }
    }
}

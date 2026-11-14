package com.iwaji.vietstep.ui.stats

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text

import com.iwaji.vietstep.data.model.CategoryStats
import com.iwaji.vietstep.data.model.DailyStat
import com.iwaji.vietstep.ui.stats.components.PieLevelChart
import com.iwaji.vietstep.ui.stats.components.StatsSection
import com.iwaji.vietstep.ui.stats.components.TodayResultBar
import com.iwaji.vietstep.ui.stats.components.WeeklyBarChart


@Composable
fun ChunkStatsPage(
    stats: CategoryStats,
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
        StatsSection("\uD83D\uDCCA 今日の統計") {
            val correct = todayStat?.correct ?: 0
            val incorrect = todayStat?.incorrect ?: 0

            if (correct + incorrect > 0) {
                TodayResultBar(
                    correct = correct,
                    incorrect = incorrect
                )
            } else {
                Text("今日の統計データがありません")
            }
        }

        // -----------------------------
        // ✅ 週間学習数（BarChart）
        // -----------------------------
        StatsSection("📅 週間学習数") {
            val values = weeklyData.map { it.second.toFloat() }
            val totalWeek = values.sum()

            if (totalWeek > 0f) {
                WeeklyBarChart(weeklyData)
            } else {
                Text("週間学習データがありません")
            }
        }

        // -----------------------------
        // ✅ チャンクレベル分布（PieChart）
        // -----------------------------
        StatsSection("📚 チャンクレベル分布") {
            if (stats.total > 0) {
                PieLevelChart(
                    levelCounts = stats.levelCounts
                )
            } else {
                Text("チャンクデータがありません")
            }
        }
    }
}

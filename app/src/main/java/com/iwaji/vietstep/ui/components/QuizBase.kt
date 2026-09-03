package com.iwaji.vietstep.ui.components

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.material3.ButtonDefaults

import com.iwaji.vietstep.util.playSound
import com.iwaji.vietstep.R
import com.iwaji.vietstep.data.model.QuizStats

@Composable
fun GenericQuizUI(
    soundEnabled: Boolean,
    soundVolume: Float,
    question: String,
    correctAnswer: String,
    allOptions: List<String>,
    quizStats: QuizStats,
    onQuizResult: (Boolean) -> Unit,
    onAnswer: (Boolean) -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var options by remember { mutableStateOf(listOf<String>()) }
    var selected by remember { mutableStateOf<String?>(null) }
    var isCorrect by remember { mutableStateOf<Boolean?>(null) }

    // ✅ コンボと統計
    val streak = quizStats.streak
    val bestStreak = quizStats.bestStreak
    val total = quizStats.total
    val correctCount = quizStats.correctCount

    val comboColor = when {
        streak >= 20 -> Color(0xFFFF1744)   // 🔴 超連続（赤）
        streak >= 10 -> Color(0xFFFF9800)   // 🟠 高コンボ（オレンジ）
        else -> Color(0xFFFF5722)
    }

    val comboText = when {
        streak >= 20 -> "🔥🔥 GOD COMBO $streak"
        streak >= 10 -> "🔥 GREAT COMBO $streak"
        streak > 1 -> "🔥 コンボ: $streak"
        else -> ""
    }

    val comboScale by animateFloatAsState(
        targetValue = if (streak >= 10) 1.2f else 1f,
        label = ""
    )

    // ✅ 選択肢生成
    LaunchedEffect(question) {
        val wrong = allOptions
            .filter { it != correctAnswer }
            .shuffled()
            .take(3)

        options = (wrong + correctAnswer).shuffled()

        // ✅ 次問題リセット
        selected = null
        isCorrect = null
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        // ✅ 問題
        Text(
            text = "Q. $question",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(8.dp))

        // ✅ 正解／不正解メッセージ
        when (isCorrect) {
            true -> Text("正解！", color = Color.Green)
            false -> Text("不正解…", color = Color.Red)
            else -> {}
        }

        Spacer(Modifier.height(12.dp))

        // ✅ コンボ表示
        if (streak > 1) {
            Text(
                text = comboText,
                color = comboColor,
                fontSize = if (streak >= 10) 22.sp else 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.graphicsLayer {
                    scaleX = comboScale
                    scaleY = comboScale
                }
            )
        }

        Spacer(Modifier.height(12.dp))

        // ✅ 正解率
        if (total > 0) {
            val rate = (correctCount * 100 / total)
            Text("正解率: $rate%  ($correctCount / $total)")
        }

        Spacer(Modifier.height(16.dp))

        // ✅ 選択肢
        options.forEach { option ->

            val scale by animateFloatAsState(
                targetValue = if (selected == option && isCorrect == true) 1.1f else 1f,
                label = ""
            )

            Button(
                onClick = {

                    if (selected != null) return@Button

                    val correct = option == correctAnswer

                    selected = option
                    isCorrect = correct

                    onQuizResult(correct)

                    if (soundEnabled) {
                        if (correct) {
                            playSound(context, R.raw.correct, soundVolume)
                        } else {
                            playSound(context, R.raw.wrong, soundVolume)
                        }
                    }

                    scope.launch {
                        delay(700)
                        onAnswer(correct)
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = when {
                        selected == null -> Color(0xFF6750A4)
                        option == selected && isCorrect == true -> Color(0xFF4CAF50)
                        option == selected && isCorrect == false -> Color(0xFFF44336)
                        else -> Color.LightGray
                    }
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .graphicsLayer(
                        scaleX = scale,
                        scaleY = scale
                    )
            ) {
                Text(option)
            }
        }

        Spacer(Modifier.height(16.dp))

        // ✅ 最高連続
        Text(
            text = "最高連続: $bestStreak",
            fontSize = 16.sp
        )
    }
}

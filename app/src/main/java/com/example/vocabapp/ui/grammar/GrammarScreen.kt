package com.example.vocabapp.ui.grammar

import android.speech.tts.TextToSpeech
import android.content.SharedPreferences

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material3.Slider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vocabapp.data.source.GrammarItem

import com.example.vocabapp.data.source.grammarList
import com.example.vocabapp.ui.components.GenericQuizUI
import com.example.vocabapp.manager.TtsManager
import com.example.vocabapp.ui.sync.SyncViewModel

@Composable
fun GrammarScreen(
    soundEnabled: Boolean,
    soundVolume: Float,
    tts: TextToSpeech?,
    viewModel: GrammarViewModel
) {

    val uiState by
    viewModel.uiState.collectAsState()

    var showSettings by remember {mutableStateOf(false)}

    val themes = remember {
        grammarList
            .map { it.theme }
            .distinct()
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            "  文法 > ${uiState.selectedTheme}",
            fontSize = 16.sp,
            color = Color.Gray
        )

        IconButton(
            onClick = {
                showSettings = true
            }
        ) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "設定"
            )
        }
    }

    if (showSettings) {
        GrammarSettingDialog(
            uiState = uiState,
            themeList = themes,
            updateTheme = {
                viewModel.setTheme(it)
            },
            updateStudyMode = {
                viewModel.setStudyMode(it)
            },
            onDismiss = {
                showSettings = false
            }
        )
    }

    // ✅ 共通出題リスト
    val quizSource =
         remember(uiState.selectedTheme) {

            grammarList
                .filter {
                    it.theme == uiState.selectedTheme
                }
                .groupBy { it.pattern }
                .values
                .map { it.random() }
        }

    // ✅ 残り問題
    var remaining by remember(quizSource) {
        mutableStateOf(quizSource.toMutableList())
    }

    // ✅ 現在問題
    var current by remember(quizSource) {
        mutableStateOf(quizSource.firstOrNull())
    }

    var showAnswer by remember { mutableStateOf(false) }

    LaunchedEffect(current) {
        showAnswer = false
    }

    // ✅ 共通 next()
    fun next() {
        current?.let {
            remaining.remove(current)
        }

        if (remaining.isEmpty()) {
            remaining =
                createGrammarQuizSource(
                    uiState.selectedTheme,
                    grammarList
                ).toMutableList()
        }

        current = remaining.randomOrNull()
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (uiState.studyMode == "quiz") {

            // =====================
            // ✅ クイズモード
            // =====================

            current?.let { currentItem ->
                GenericQuizUI(
                    soundEnabled = soundEnabled,
                    soundVolume = soundVolume,
                    question = currentItem.vietnamese,
                    correctAnswer = currentItem.japanese,
                    allOptions = quizSource.map { it.japanese },

                    quizStats = uiState.quizStats,
                    onQuizResult = { correct ->
                        viewModel.updateQuizStats(
                            correct
                        )
                    },
                    onAnswer = {
                        next()
                    }
                )
            }

        } else {

            // =====================
            // ✅ カードモード
            // =====================

            current?.let { currentItem ->
                Text(currentItem.vietnamese, fontSize = 26.sp)

                Spacer(Modifier.height(8.dp))

                Text(currentItem.pattern, fontSize = 18.sp, color = Color.Gray)

                Box(
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.TopCenter
                ) {
                    androidx.compose.animation.AnimatedVisibility(
                        visible = showAnswer,
                        enter = fadeIn() + slideInVertically { 20 },
                        exit = fadeOut() + slideOutVertically { 20 }
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(currentItem.memo, fontSize = 16.sp, color = Color.Gray)
                            Text(currentItem.japanese, fontSize = 20.sp)
                        }
                    }
                }

            Spacer(Modifier.height(12.dp))

            // ✅ ボタン
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {

                Button(
                    onClick = {
                        tts?.setSpeechRate(uiState.speechRate)
                        TtsManager.speak(tts, currentItem.vietnamese)
                    },
                    modifier = Modifier.width(120.dp).height(45.dp)
                ) {
                    Text("🔊 発音", fontSize = 16.sp)
                }

                Button(
                    onClick = { showAnswer = !showAnswer },
                    modifier = Modifier.width(120.dp).height(45.dp)
                ) {
                    Text(
                        if (showAnswer) "隠す" else "日本語",
                        fontSize = 16.sp
                    )
                }
            }

            Spacer(Modifier.height(8.dp))

            var lastPlayTime by remember { mutableStateOf(0L) }

            // ✅ 速度スライダー
            Slider(
                value = uiState.speechRate,
                onValueChange = { value ->
                    val now = System.currentTimeMillis()

                    viewModel.setSpeechRate(value)

                    if (now - lastPlayTime > 200) {
                        tts?.setSpeechRate(value)
                        TtsManager.speak(tts, currentItem.vietnamese)
                        lastPlayTime = now
                    }

                    // ✅ 即時プレビュー
                    tts?.setSpeechRate(value)
                    TtsManager.speak(tts, currentItem.vietnamese)
                },
                valueRange = 0.5f..1.2f,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            )

            Text(
                text = when {
                    uiState.speechRate < 0.7f -> "🐢 ゆっくり"
                    uiState.speechRate < 1.0f -> "🙂 普通"
                    else -> "🚀 速い"
                }
            )

            Spacer(Modifier.height(24.dp))

            // ✅ 次へボタン（統一ロジック）
            Button(
                onClick = {
                    showAnswer = false
                    next()
                },
                modifier = Modifier.width(100.dp).height(50.dp)
            ) {
                Text("次へ", fontSize = 18.sp)
            }
            }
        }
    }
}

fun createGrammarQuizSource(
    theme: String?,
    source: List<GrammarItem>
): List<GrammarItem> {

    return source
        .filter {
            it.theme == theme
        }
        .groupBy {
            it.pattern
        }
        .values
        .map {
            it.random()
        }
}
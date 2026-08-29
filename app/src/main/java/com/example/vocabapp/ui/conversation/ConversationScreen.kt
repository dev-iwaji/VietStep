package com.example.vocabapp.ui.conversation

import kotlinx.coroutines.launch

import android.speech.tts.TextToSpeech

import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.ExitTransition
import androidx.compose.material3.Slider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color

import com.example.vocabapp.data.source.conversationList
import com.example.vocabapp.ui.components.GenericQuizUI
import com.example.vocabapp.manager.TtsManager
import android.util.Log

@Composable
fun ConversationScreen(
    soundEnabled: Boolean,
    soundVolume: Float,
    tts: TextToSpeech?,
    viewModel: ConversationViewModel
) {

    val uiState by viewModel.uiState.collectAsState()

    val scope = rememberCoroutineScope()

    var showSettings by remember {mutableStateOf(false)}

    val themes = remember {
        conversationList
            .map { it.theme }
            .distinct()
    }

    val currentFilter =
        if (uiState.selectedPart == "全部") {
            "  会話 > ${uiState.selectedTheme}"
        } else {
            "  会話 > ${uiState.selectedTheme} > ${uiState.selectedPart}"
        }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = currentFilter,
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

    val currentItem =
        uiState.deck.getOrNull(
            uiState.deckIndex
        )

    var showAnswer by remember { mutableStateOf(false) }

    LaunchedEffect(currentItem) {
        showAnswer = false
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

            currentItem?.let { currentItem ->
                GenericQuizUI(
                    soundEnabled = soundEnabled,
                    soundVolume = soundVolume,
                    question = currentItem.vietnamese,
                    correctAnswer = currentItem.japanese,
                    allOptions = uiState.deck.map { it.japanese },

                    quizStats = uiState.quizStats,
                    onQuizResult = { correct ->
                        viewModel.updateQuizStats(
                            correct
                        )
                    },
                    onAnswer = {
                        viewModel.nextCard()
                    }
                )
            }

        } else {

            // =====================
            // ✅ カードモード
            // =====================

            currentItem?.let { currentItem ->
                Box(
                    modifier = Modifier
                        .height(100.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = currentItem.vietnamese,
                            fontSize = 26.sp,
                            lineHeight = 30.sp
                        )

                        Spacer(Modifier.height(8.dp))

                        Text(
                            text = currentItem.pattern,
                            fontSize = 18.sp,
                            color = Color.Gray
                        )
                    }
                }

                Spacer(Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.TopCenter
                ) {
                    androidx.compose.animation.AnimatedVisibility(
                        visible = showAnswer,
                        enter = fadeIn() + slideInVertically { 20 },
                        exit = ExitTransition.None
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = currentItem.memo,
                                fontSize = 16.sp,
                                color = Color.Gray
                            )
                            Text(
                                text = currentItem.japanese,
                                fontSize = 20.sp
                            )
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
                        Text("🔊 発話", fontSize = 16.sp)
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
                        scope.launch {
                            showAnswer = false

                            // 日本語を消した状態を1フレーム描画
                            withFrameNanos { }

                            viewModel.nextCard()
                        }
                    },
                    modifier = Modifier.width(100.dp).height(50.dp)
                ) {
                    Text("次へ", fontSize = 18.sp)
                }
            }
        }
    }

    //
    // ✅ 設定ダイアログ
    //
    if (showSettings) {
        ComversationSettingDialog(
            uiState = uiState,
            themeList = themes,
            onApply = {
                    theme,
                    part,
                    studyMode ->

                viewModel.applySettings(
                    theme,
                    part,
                    studyMode
                )
            },
            onDismiss = {
                showSettings = false
            }
        )
    }
}

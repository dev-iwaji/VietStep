package com.iwaji.vietstep.ui.chunk

import android.speech.tts.TextToSpeech

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

import com.iwaji.vietstep.R
import com.iwaji.vietstep.util.playSound
import com.iwaji.vietstep.ui.components.SwipeCard
import com.iwaji.vietstep.ui.components.GenericQuizUI

@Composable
fun ChunkScreen(
    soundEnabled: Boolean,
    soundVolume: Float,
    tts: TextToSpeech?,
    viewModel: ChunkViewModel
) {

    val uiState by
        viewModel.uiState.collectAsState()

    val context = LocalContext.current

    var completedLap by rememberSaveable { mutableStateOf(false) }

    // ✅ 学習対象チャンク
    val targetChunks = viewModel.getFilteredChunks()

    // ✅ 現在のチャンク
    val currentChunk = uiState.deck.getOrNull(uiState.deckIndex)// ?: return

    // ✅ 学習モード（カード or クイズ）
    var showAnswer by remember { mutableStateOf(false) }

    var showSettings by remember {mutableStateOf(false)}

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        var categoryText = ""
        if (currentChunk != null )
            categoryText = " > ${currentChunk.difficulty?: ""}" + " > ${currentChunk.category?: ""}"

        Text(
            "  チャンク" + categoryText,
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

    Spacer(Modifier.height(4.dp))

    Column(
        modifier = Modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        when {

            currentChunk == null -> {
                Spacer(Modifier.height(20.dp))

                Text(
                    "❗ 条件に一致する文型がありません",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(16.dp))

                Text(
                    "カテゴリ、重点項目を変更してみてください",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            else -> {

                // ✅ 進捗計算
                val progress =
                    if (uiState.deck.isEmpty()) 0f
                    else (uiState.deckIndex + 1).toFloat() / uiState.deck.size.toFloat()

                // ✅ アニメーション
                val animatedProgress by animateFloatAsState(
                    targetValue = progress,
                    label = ""
                )

                // ✅ 色変化
                val progressColor = when {
                    progress < 0.3f -> Color.Red
                    progress < 0.7f -> Color(0xFFFFC107)
                    else -> Color(0xFF4CAF50)
                }

                // ✅ プログレスバー
                LinearProgressIndicator(
                    progress = animatedProgress,
                    color = progressColor,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                )

                Spacer(Modifier.height(4.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    // ✅ 左エリア（中央寄せ）
                    Box(
                        modifier = Modifier
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        // ✅ 🎉 1周完了メッセージ
                        if (uiState.deckIndex == 0 && uiState.deck.size > 0 && completedLap) {
                            Text(
                                text = "🎉 1周完了！",
                                color = Color(0xFF4CAF50),
                                fontSize = 14.sp
                            )
                        } else {
                            Spacer(Modifier.height(20.dp))
                        }
                    }

                    // ✅ 右エリア（縦＋右寄せ）
                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        // ✅ 数字表示
                        Text("${uiState.deckIndex + 1} / ${uiState.deck.size}", fontSize = 12.sp)

                        // ✅ 残り表示
                        Text(
                            "残り: ${uiState.deck.size - (uiState.deckIndex + 1)}",
                            fontSize = 12.sp
                        )
                    }
                }

                if (uiState.studyMode == "card") {

                    // =====================
                    // ✅ カードモード
                    // =====================

                    currentChunk.let { currentChunk ->

                        Spacer(Modifier.height(12.dp))

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .padding(16.dp)
                        ) {

                            // ✅ スワイプカード
                            SwipeCard(
                                onRight = {
                                    if (soundEnabled) {
                                        playSound(context, R.raw.correct, soundVolume)
                                    }

                                    val isLastCard =
                                        uiState.deckIndex == uiState.deck.lastIndex

                                    viewModel.answerChunk(currentChunk, true)

                                    showAnswer = false
                                    completedLap = isLastCard
                                },
                                onLeft = {
                                    if (soundEnabled) {
                                        playSound(context, R.raw.wrong, soundVolume)
                                    }

                                    val isLastCard =
                                        uiState.deckIndex == uiState.deck.lastIndex

                                    viewModel.answerChunk(currentChunk, false)

                                    showAnswer = false
                                    completedLap = isLastCard
                                }
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(12.dp)
                                ) {

                                    // ✅ 文型
                                    Text(
                                        text = currentChunk.pattern,
                                        fontSize = 14.sp,
                                        color = Color.Gray
                                    )

                                    // ✅ 日本語の説明
                                    Text(
                                        text = currentChunk.memo,
                                        fontSize = 12.sp,
                                        color = Color.Gray
                                    )

                                    // ✅ 上の説明とベトナム語の間
                                    Spacer(
                                        modifier = Modifier.height(8.dp)
                                    )

                                    // ✅ 残り領域の中央にベトナム語
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .weight(1f),
                                        contentAlignment = Alignment.TopCenter
                                    ) {

                                        Text(
                                            text = currentChunk.vietnamese,
                                            fontSize = 32.sp,
                                            lineHeight = 38.sp,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center,
                                            maxLines = 2
                                        )
                                    }
                                }
                            }
                        }
                    }

                    Spacer(Modifier.height(4.dp))

                    // ✅ 下テキスト
                    Text(
                        "← 未習得　　覚えた →",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )

                    Spacer(Modifier.height(12.dp))

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // ✅ 発音
                        Button(
                            onClick = {
                                tts?.speak(
                                    currentChunk.vietnamese,
                                    TextToSpeech.QUEUE_FLUSH,
                                    null,
                                    null
                                )
                            },
                            modifier = Modifier.width(120.dp).height(50.dp)
                        ) {
                            Text("🔊 発音", fontSize = 18.sp)
                        }

                        // ✅ 表示切替
                        Button(
                            onClick = { showAnswer = !showAnswer },
                            modifier = Modifier.width(120.dp).height(50.dp)
                        ) {
                            Text(
                                if (showAnswer) "隠す" else "日本語",
                                fontSize = 18.sp
                            )
                        }
                    }

                    Spacer(Modifier.height(12.dp))

                    // ✅ 日本語表示
                    if (showAnswer) {
                        Text(currentChunk.japanese, fontSize = 28.sp)
                    }
                } else {

                    // =====================
                    // ✅ クイズモード
                    // =====================

                    GenericQuizUI(
                        soundEnabled = soundEnabled,
                        soundVolume = soundVolume,
                        question = currentChunk.vietnamese,
                        correctAnswer = currentChunk.japanese,
                        allOptions = targetChunks.map { it.japanese },

                        quizStats = uiState.quizStats,
                        onQuizResult = { correct ->
                            viewModel.updateQuizStats(
                                correct
                            )
                        },
                        onAnswer = { correct ->
                            val isLastCard =
                                uiState.deckIndex == uiState.deck.lastIndex

                            // ✅ 学習データ更新
                            viewModel.answerChunk(currentChunk, correct)
                            completedLap = isLastCard
                        }
                    )
                }
            }
        }
    }

    //
    // ✅ 設定ダイアログ
    //
    if (showSettings) {

        ChunkSettingDialog(
            soundEnabled = soundEnabled,
            soundVolume = soundVolume,
            uiState = uiState,
            onApply = {
                      category,
                      difficulty,
                      weakMode,
                      studyMode ->

                viewModel.applySettings(
                    category,
                    difficulty,
                    weakMode,
                    studyMode
                )
                completedLap = false
            },
            onRebuildDeck = {
                viewModel.rebuildDeck()
                completedLap = false
            },
            onDismiss = {
                showSettings = false
            }
        )
    }
}

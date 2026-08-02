package com.example.vocabapp.ui.chunk

import android.content.SharedPreferences
import android.speech.tts.TextToSpeech
import android.util.Log

import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import com.example.vocabapp.R
import com.example.vocabapp.util.playSound
import com.example.vocabapp.ui.components.SwipeCard
import com.example.vocabapp.ui.components.GenericQuizUI

@Composable
fun ChunkScreen(
    soundEnabled: Boolean,
    soundVolume: Float,
    prefs: SharedPreferences,
    tts: TextToSpeech?
) {

    val viewModel: ChunkViewModel =
        viewModel()

    val uiState by
        viewModel.uiState.collectAsState()

    val context = LocalContext.current

    // ✅ 初期化
//    var initialized by remember {mutableStateOf(false)}

    var completedLap by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.initialize(prefs)
        viewModel.load()
        Log.d("ChunkScreen", "load called")
    }
/*
    // ✅ フィルター変更によるデッキ再生成
    LaunchedEffect(
        uiState.selectedCategory,
        uiState.selectedDifficulty
    ) {
        completedLap = false

        if (initialized == false)
            initialized = true
        else
            viewModel.rebuildDeck(viewModel.getFilteredChunks())
    }
*/
    // ✅ 学習対象チャンク
    val targetChunks = viewModel.getFilteredChunks()

    // ✅ 現在のチャンク
    val currentChunk = uiState.deck.getOrNull(uiState.deckIndex) ?: return

    // ✅ 学習モード（カード or クイズ）
    var showAnswer by remember { mutableStateOf(false) }

    var showSettings by remember {mutableStateOf(false)}

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            "  チャンク" +
            " > ${currentChunk.difficulty?: ""}" +
            " > ${currentChunk.category?: ""}",
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
                Text("残り: ${uiState.deck.size - (uiState.deckIndex + 1)}", fontSize = 12.sp)
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

                            viewModel.answerChunk(currentChunk, true)

                            showAnswer = false
                            completedLap = true
                        },
                        onLeft = {
                            if (soundEnabled) {
                                playSound(context, R.raw.wrong, soundVolume)
                            }

                            viewModel.answerChunk(currentChunk, false)

                            showAnswer = false
                            completedLap = true
                        }
                    ) {

                        // ✅ 中央の単語
                        Column(
                            modifier = Modifier.align(Alignment.Center),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {

                                Spacer(Modifier.width(8.dp))

                                // ★ ベトナム語（大きく）
                                Text(
                                    text = currentChunk.vietnamese,
                                    fontSize = 30.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Column(
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(12.dp)
                        ) {
                            Text(
                                text = currentChunk.pattern,
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                            Text(
                                currentChunk.memo,
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
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

                onAnswer = { correct ->
                    // ✅ 学習データ更新
                    viewModel.answerChunk(currentChunk, correct)
                }
            )
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
            onChangeWeakMode = {
                viewModel.setWeakMode(it)
            },
            onChangeStudyMode = {
                viewModel.setStudyMode(it)
            },
            onRebuildDeck = {
                viewModel.rebuildDeck()
                completedLap = false
            },
            onApply = { category, difficulty ->
                if (category != uiState.selectedCategory) {
                    viewModel.setCategory(
                        category
                    )
                }
                if (difficulty != uiState.selectedDifficulty) {
                    viewModel.setDifficulty(
                        difficulty
                    )
                }
                completedLap = false
            },
            onDismiss = {
                showSettings = false
            }
        )
    }
}

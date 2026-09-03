package com.iwaji.vietstep.ui.word

import kotlinx.coroutines.delay

import android.content.Context
import android.content.Intent
import android.provider.OpenableColumns
import android.speech.tts.TextToSpeech
import android.net.Uri

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.animation.core.animateFloatAsState

import com.iwaji.vietstep.R
import com.iwaji.vietstep.util.playSound
import com.iwaji.vietstep.data.model.Word
import com.iwaji.vietstep.data.model.deckKey
import com.iwaji.vietstep.data.model.getPosColor
import com.iwaji.vietstep.ui.components.SwipeCard
import com.iwaji.vietstep.ui.components.GenericQuizUI

@Composable
fun WordScreen(
    soundEnabled: Boolean,
    soundVolume: Float,
    tts: TextToSpeech?,
    wordViewModel: WordViewModel
) {

    val context = LocalContext.current

    val uiState by
    wordViewModel.uiState.collectAsState()

    var completedLap by rememberSaveable { mutableStateOf(false) }

    // ✅ 学習対象単語
    val targetWords = wordViewModel.getFilteredWords()

    // ✅ 現在の単語
    val currentWord = uiState.deck.getOrNull(uiState.deckIndex)

    // ✅ CSV追加
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.OpenDocument()
    ) {
        it?.let { uri ->
            uri.let {
                context.contentResolver
                    .takePersistableUriPermission(
                        uri,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION
                    )

                var name = "unknown.csv"
                context.contentResolver.query(
                    uri,
                    null,
                    null,
                    null,
                    null
                )?.use { cursor ->
                    val index =
                        cursor.getColumnIndex(
                            OpenableColumns.DISPLAY_NAME
                        )
                    if (
                        index >= 0 &&
                        cursor.moveToFirst()
                    ) {
                        name = cursor.getString(index)
                    }
                }

                wordViewModel.addCsvFile(uri.toString(), name, context)
            }
        }
    }

    var showAnswer by remember { mutableStateOf(false) }

    var showSettings by remember {mutableStateOf(false)}

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            "  単語",
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

            !uiState.isInitialized -> {
                // 何も表示しない、または小さいProgressIndicator
            }

            currentWord == null -> {
                Spacer(Modifier.height(20.dp))

                Text(
                    "❗ 条件に一致する単語がありません",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(16.dp))

                Text(
                    "品詞フィルター、重点項目を変更してみてください",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            else -> {

                // ✅ 進捗計算
                val progress =
                    if (uiState.deck.size == 0) 0f
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
                                text = "\uD83C\uDF89 1周完了！",
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

                    currentWord.let { word ->

                        // ✅ スケール状態（?用）
                        var starScale by remember { mutableStateOf(1f) }

                        // ✅ 元に戻す
                        LaunchedEffect(starScale) {
                            if (starScale > 1f) {
                                delay(100)
                                starScale = 1f
                            }
                        }

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

                                    wordViewModel.answerWord(currentWord, true)

                                    showAnswer = false
                                    completedLap = isLastCard
                                },
                                onLeft = {
                                    if (soundEnabled) {
                                        playSound(context, R.raw.wrong, soundVolume)
                                    }

                                    val isLastCard =
                                        uiState.deckIndex == uiState.deck.lastIndex

                                    wordViewModel.answerWord(currentWord, false)

                                    showAnswer = false
                                    completedLap = isLastCard
                                }
                            ) {
                                var isTwoLines by remember { mutableStateOf(false)}

                                // ✅ 中央の単語
                                Column(
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                        .fillMaxWidth()
                                        .padding(horizontal = if (isTwoLines) 60.dp else 24.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center
                                    ) {

                                        val vietnameseFontSize =
                                            when {
                                                currentWord.vietnamese.length >= 16 -> 32.sp
                                                currentWord.vietnamese.length >= 12 -> 36.sp
                                                else -> 40.sp
                                            }

                                        val vietnameseLineHeight =
                                            when {
                                                currentWord.vietnamese.length >= 16 -> 38.sp
                                                currentWord.vietnamese.length >= 12 -> 42.sp
                                                else -> 46.sp
                                            }

                                        // ✅ 品詞（色付き・小さく）
                                        Text(
                                            text = currentWord.partOfSpeech,
                                            color = getPosColor(currentWord.partOfSpeech),
                                            fontSize = 20.sp
                                        )

                                        Spacer(Modifier.width(8.dp))

                                        // ✅ ベトナム語（大きく）
                                        Text(
                                            text = currentWord.vietnamese,
                                            fontSize = vietnameseFontSize,
                                            lineHeight = vietnameseLineHeight,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Start,
                                            maxLines = 2,
                                            onTextLayout = {result->
                                                isTwoLines = result.lineCount >= 2
                                            }
                                        )
                                    }
                                }

                                // ✅ お気に入り
                                Text(
                                    text = if (uiState.favorites.contains(currentWord.deckKey())) "★" else "☆",
                                    fontSize = 28.sp,
                                    color = if (uiState.favorites.contains(currentWord.deckKey()))
                                        Color(0xFFFFC107) else Color.Gray,
                                    modifier = Modifier
                                        .align(Alignment.TopStart)
                                        .padding(start = 16.dp)
                                        .clickable {

                                            wordViewModel.toggleFavorites(currentWord)
                                        }
                                )
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
                                    currentWord.vietnamese,
                                    TextToSpeech.QUEUE_FLUSH,
                                    null,
                                    null
                                )
                            },
                            modifier = Modifier
                                .width(120.dp)
                                .height(50.dp)
                        ) {
                            Text("🔊 発音", fontSize = 18.sp)
                        }

                        // ✅ 表示切替
                        Button(
                            onClick = { showAnswer = !showAnswer },
                            modifier = Modifier
                                .width(120.dp)
                                .height(50.dp)
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
                        Text(currentWord.japanese, fontSize = 28.sp)
                    }
                } else {

                    // =====================
                    // ✅ クイズモード
                    // =====================

                    GenericQuizUI(
                        soundEnabled = soundEnabled,
                        soundVolume = soundVolume,
                        question = currentWord.vietnamese,
                        correctAnswer = currentWord.japanese,
                        allOptions = targetWords.map { it.japanese },

                        quizStats = uiState.quizStats,
                        onQuizResult = { correct ->
                            wordViewModel.updateQuizStats(
                                correct
                            )
                        },
                        onAnswer = { correct ->
                            val isLastCard =
                                uiState.deckIndex == uiState.deck.lastIndex

                            // ✅ 学習データ更新
                            wordViewModel.answerWord(currentWord, correct)
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

        WordSettingDialog(
            soundEnabled = soundEnabled,
            soundVolume = soundVolume,
            uiState = uiState,
            launcher = launcher,
            onApply = {
                    pos,
                    weakMode,
                    favoriteOnly,
                    studyMode ->

                wordViewModel.applySettings(
                    pos = pos,
                    weakMode = weakMode,
                    favoriteOnly = favoriteOnly,
                    studyMode = studyMode
                )

                completedLap = false
            },
            onUpdatetDeck = {
                wordViewModel.rebuildDeck()
                completedLap = false
            },
            onToggleCsvFile = { file ->
                wordViewModel.toggleCsvFile(file, context)
                completedLap = false
            },
            onRemoveCsvFile = { file ->
                wordViewModel.removeCsvFile(file, context)
                completedLap = false
            },
            onDismiss = {
                showSettings = false
            }
        )
    }
}

fun parseCsv(
    context: Context, uri: Uri
): List<Word> {
    val list = mutableListOf<Word>()

    context.contentResolver
        .openInputStream(uri)
        ?.bufferedReader()
        ?.forEachLine { line ->

            val parts = line.split(",")

            if (parts.size >= 3) {

                val id = parts[0]
                    .trim()
                    .trim('"')
                val pos = parts[1]
                    .trim()
                    .trim('"')
                val vi = parts[2]
                    .trim()
                    .trim('"')
                val ja = parts[3]
                    .trim()
                    .trim('"')

                if (vi.isNotEmpty() && ja.isNotEmpty()) {
                    list.add(
                        Word(
                            categoryId = id,
                            partOfSpeech = pos,
                            vietnamese = vi,
                            japanese = ja,
                            level = 5
                        )
                    )
                }
            }
        }

    return list
}

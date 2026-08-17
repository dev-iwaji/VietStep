package com.example.vocabapp.ui.word

import kotlinx.coroutines.delay

import androidx.activity.result.ActivityResultLauncher

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

import com.example.vocabapp.R
import com.example.vocabapp.data.model.CsvFile
import com.example.vocabapp.util.playSound
import android.util.Log

@Composable
fun WordSettingDialog(
    soundEnabled: Boolean,
    soundVolume: Float,
    uiState: WordUiState,
    launcher: ActivityResultLauncher<Array<String>>,
    onChangeWeakMode: (Boolean) -> Unit,
    onChangeFavoriteOnly: (Boolean) -> Unit,
    onChangeStudyMode: (String) -> Unit,
    onSelectedPos: (Set<String>) -> Unit,
    onUpdatetDeck: () -> Unit,
    onToggleCsvFile: (CsvFile) -> Unit,
    onRemoveCsvFile: (CsvFile) -> Unit,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current

    // シャッフルボタンのアニメーション用の状態
    var offsetX by remember { mutableStateOf(0.dp) }
    var scale by remember { mutableStateOf(1f) }
    var rotation by remember { mutableStateOf(0f) }
    var shake by remember { mutableStateOf(false) }

    var tempSelectedPos by remember {
        mutableStateOf(uiState.selectedPos)
    }

    // シャッフルボタンのアニメーション
    LaunchedEffect(shake) {
        if (shake) {
            // 一瞬だけ拡大
            scale = 1.15f
            rotation = 3f
            delay(80)
            scale = 1f
            rotation = 0f

            // 左右に揺れる
            repeat(3) {
                offsetX = (-10).dp
                delay(50)
                offsetX = (10).dp
                delay(50)
            }
            offsetX = 0.dp

            shake = false
        }
    }

    AlertDialog(
        onDismissRequest = onDismiss,

        title = {
            Text("単語設定")
        },

        text = {

            Column(
                modifier = Modifier
                    .heightIn(max = 500.dp)
                    .verticalScroll(
                        rememberScrollState()
                    )
            ) {

                // ======================
                // ✅ シャッフルボタン
                // ======================


                FilledTonalButton(
                    onClick = {
                        onUpdatetDeck()
                        if (soundEnabled) {
                            playSound(context, R.raw.shuffle, soundVolume)
                        }
                        shake = true
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(x = offsetX)
                        .graphicsLayer(
                            scaleX = scale,
                            scaleY = scale,
                            rotationZ = rotation
                        )
                ) {
                    Text("デッキを再シャッフル")
                }

                Spacer(Modifier.height(20.dp))

                val tempTargetCount =
                    uiState.words.count {
                        tempSelectedPos.isEmpty() ||
                        tempSelectedPos.contains(
                            it.partOfSpeech
                        )
                    }

                Text(
                    "対象語数: $tempTargetCount"
                )

                Spacer(Modifier.height(16.dp))

                // ======================
                // ✅ フィルター
                // ======================
                Text("品詞フィルター")

                val posList = listOf(
                    "動","名","形","副",
                    "前","代","疑","助","接","感","類"
                )

                posList.chunked(4).forEach { rowItems ->

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {

                        rowItems.forEach { pos ->

                            Row(verticalAlignment = Alignment.CenterVertically) {

                                Checkbox(
                                    checked = tempSelectedPos.contains(pos),
                                    onCheckedChange = {
                                        tempSelectedPos =
                                            if (tempSelectedPos.contains(pos)) {
                                                tempSelectedPos - pos
                                            } else {
                                                tempSelectedPos + pos
                                            }
                                    }
                                )

                                Text(pos)
                            }
                        }
                    }
                }

                Spacer(Modifier.height(8.dp))

                // ======================
                // ✅ モード管理
                // ======================

                Text("重点項目")

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = uiState.favoriteOnly,
                            onCheckedChange = {
                                onChangeFavoriteOnly(it)
                            }
                        )
                        Text("お気に入り")
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Checkbox(
                            checked = uiState.weakMode,
                            onCheckedChange = {
                                onChangeWeakMode(it)
                            }
                        )

                        Text("苦手語")
                    }
                }

                Spacer(Modifier.height(8.dp))

                // ======================
                // ✅ 学習モード
                // ======================

                Text("学習モード")

                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        RadioButton(
                            selected = uiState.studyMode == "card",
                            onClick = {
                                onChangeStudyMode("card")
                            }
                        )

                        Text("カード")
                    }

                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        RadioButton(
                            selected = uiState.studyMode == "quiz",
                            onClick = {
                                onChangeStudyMode("quiz")
                            }
                        )

                        Text("クイズ")
                    }
                }

                Spacer(Modifier.height(8.dp))

                // ======================
                // ✅ CSV管理
                // ======================

                Text("CSV管理")

                uiState.csvList.forEach { file ->

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Row(verticalAlignment = Alignment.CenterVertically) {

                            Checkbox(
                                checked = file.enabled,
                                onCheckedChange = {

                                    onToggleCsvFile(file)
                                }
                            )

                            Text(file.name)
                        }

                        TextButton(
                            onClick = {
                                onRemoveCsvFile(file)
                            }
                        ) {
                            Text("削除")
                        }
                    }
                }

                Spacer(Modifier.height(8.dp))

                Button(
                    onClick = {
                        launcher.launch(
                            arrayOf("text/*")
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("CSV追加")
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onSelectedPos(tempSelectedPos)
                    onDismiss()
                }
            ) {
                Text("適用")
            }
        }
    )
}

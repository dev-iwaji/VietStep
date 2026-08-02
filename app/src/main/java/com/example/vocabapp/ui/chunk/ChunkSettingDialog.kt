package com.example.vocabapp.ui.chunk

import kotlinx.coroutines.delay

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.rememberModalBottomSheetState

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
import com.example.vocabapp.util.playSound

@Composable
fun ChunkSettingDialog(
    soundEnabled: Boolean,
    soundVolume: Float,
    uiState: ChunkUiState,
    onChangeWeakMode: (Boolean) -> Unit,
    onChangeStudyMode: (String) -> Unit,
    onRebuildDeck: () -> Unit,
    onApply: (
        Set<String>,
        Set<String>
    ) -> Unit,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current

    var editingCategory by remember {
        mutableStateOf(
            uiState.selectedCategory
        )
    }

    var editingDifficulty by remember {
        mutableStateOf(
            uiState.selectedDifficulty
        )
    }

    val targetCount =
        uiState.chunks
            .filter {
                editingCategory.isEmpty() ||
                        editingCategory.contains(it.category)
            }
            .filter {
                editingDifficulty.isEmpty() ||
                        editingDifficulty.contains(it.difficulty)
            }
            .size

    val visibleCategories =
        uiState.chunks
            .filter {
                editingDifficulty.contains(
                    it.difficulty
                )
            }
            .map {
                it.category
            }
            .distinct()

    // シャッフルボタンのアニメーション用の状態
    var offsetX by remember { mutableStateOf(0.dp) }
    var scale by remember { mutableStateOf(1f) }
    var rotation by remember { mutableStateOf(0f) }
    var shake by remember { mutableStateOf(false) }

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
            Text("チャンク設定")
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
                        onRebuildDeck()
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

                Text(
                    "対象語数: $targetCount"
                )

                Spacer(Modifier.height(16.dp))

                // ======================
                // ✅ レベル設定
                // ======================
                Text("レベル設定")

                val difficulties = listOf(
                    "初級","中級","上級"
                )

                Row {
                    difficulties.forEach { difficulty ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = editingDifficulty.contains(difficulty),

                                onCheckedChange = {
                                    val newDifficulty =
                                        if (
                                            editingDifficulty.contains(difficulty)
                                        )
                                            editingDifficulty - difficulty
                                        else
                                            editingDifficulty + difficulty

                                    editingDifficulty = newDifficulty

                                    val newVisibleCategory =
                                        uiState.chunks
                                            .filter {
                                                newDifficulty.contains(it.difficulty)
                                            }
                                            .map {
                                                it.category
                                            }
                                            .distinct()

                                    editingCategory =
                                        editingCategory.filter {
                                            newVisibleCategory.contains(it)
                                        }.toSet()
                                }
                            )

                            Text(difficulty)
                        }
                    }
                }

                Spacer(Modifier.height(8.dp))

                // ======================
                // ✅ カテゴリ設定
                // ======================
                Text("カテゴリ設定")

                visibleCategories
                    .chunked(2)
                    .forEach { rowItems ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            rowItems.forEach { category ->
                                Box(
                                    Modifier.weight(1f)
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Checkbox(
                                            checked = editingCategory.contains(category),

                                            onCheckedChange = {
                                                editingCategory =
                                                    if (
                                                        editingCategory.contains(category)
                                                    )
                                                        editingCategory - category
                                                    else
                                                        editingCategory + category
                                            }
                                        )

                                        Text(category)
                                    }
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
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onApply(
                        editingCategory,
                        editingDifficulty
                    )
                    onDismiss()
                }
            ) {
                Text("適用")
            }
        }
    )
}

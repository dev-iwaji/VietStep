package com.example.vocabapp.ui.conversation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import com.example.vocabapp.ui.components.SingleSelectDropdown
import com.example.vocabapp.data.source.conversationList

@Composable
fun ComversationSettingDialog(
    uiState: ConversationUiState,
    themeList: List<String>,
//    partList: List<String>,
    onApply: (String, String, String) -> Unit,
    onDismiss: () -> Unit
) {
    var editingTheme by remember(uiState.selectedTheme) {
        mutableStateOf(uiState.selectedTheme)
    }

    var editingPart by remember(uiState.selectedPart) {
        mutableStateOf(uiState.selectedPart)
    }

    var editingStudyMode by remember(uiState.studyMode) {
        mutableStateOf(uiState.studyMode)
    }

    val editingPartList =
        remember(editingTheme) {
            listOf("全部") +
                    conversationList
                        .filter {
                            it.theme == editingTheme
                        }
                        .map {
                            it.part
                        }
                        .distinct()
        }

    AlertDialog(
        onDismissRequest = onDismiss,

        title = {
            Text("会話設定")
        },

        text = {

            Column {

                Text("テーマ")

                Spacer(Modifier.height(8.dp))

                SingleSelectDropdown(
                    title = "テーマ",
                    selected = editingTheme,
                    items = themeList,
                    onSelect = { selectedTheme ->
                        editingTheme = selectedTheme
                        editingPart = "全部"

                    }
                )

                Spacer(Modifier.height(16.dp))

                Text("パート")

                SingleSelectDropdown(
                    title = "パート",
                    selected = editingPart,
                    items = editingPartList,
                    onSelect = { selectedPart ->
                        editingPart = selectedPart
                    }
                )

                Spacer(Modifier.height(16.dp))

                Text("学習モード")

                Spacer(Modifier.height(8.dp))

                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        RadioButton(
                            selected = editingStudyMode == "card",
                            onClick = {
                                editingStudyMode = "card"
                            }
                        )

                        Text("カード")
                    }

                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        RadioButton(
                            selected = editingStudyMode == "quiz",
                            onClick = {
                                editingStudyMode = "quiz"
                            }
                        )

                        Text("クイズ")
                    }
                }
            }
        },

        confirmButton = {

            Button(
                onClick = {
                    onApply(
                        editingTheme,
                        editingPart,
                        editingStudyMode
                    )
                    onDismiss()
                }
            ) {
                Text("適用")
            }
        }
    )
}

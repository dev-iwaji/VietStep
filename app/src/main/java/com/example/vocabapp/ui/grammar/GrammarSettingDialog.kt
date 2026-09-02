package com.example.vocabapp.ui.grammar

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
import android.util.Log

@Composable
fun GrammarSettingDialog(
    uiState: GrammarUiState,
    themeList: List<String>,
    onApply: (String, String) -> Unit,
    onDismiss: () -> Unit
) {
    var editingTheme by remember(uiState.selectedTheme) {
        mutableStateOf(uiState.selectedTheme)
    }

    var editingStudyMode by remember(uiState.studyMode) {
        mutableStateOf(uiState.studyMode)
    }

    AlertDialog(
        onDismissRequest = onDismiss,

        title = {
            Text("文法設定")
        },

        text = {

            Column {

                Text("カテゴリ")

                Spacer(Modifier.height(8.dp))

                SingleSelectDropdown(
                    title = "カテゴリ",
                    selected = editingTheme,//uiState.selectedTheme,
                    items = themeList,
                    onSelect = { selectedTheme ->
                        editingTheme = selectedTheme
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

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
import androidx.compose.ui.Alignment

import com.example.vocabapp.ui.components.SingleSelectDropdown

@Composable
fun GrammarSettingDialog(
    uiState: GrammarUiState,
    themeList: List<String>,
    updateTheme: (String) -> Unit,
    updateStudyMode: (String) -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,

        title = {
            Text("カテゴリ設定")
        },

        text = {

            Column {

                Text("カテゴリ")

                Spacer(Modifier.height(8.dp))

                SingleSelectDropdown(
                    title = "カテゴリ",
                    selected = uiState.selectedTheme,
                    items = themeList,
                    onSelect = { selectedTheme ->
                        updateTheme(selectedTheme)
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
                            selected = uiState.studyMode == "card",
                            onClick = {
                                updateStudyMode("card")
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
                                updateStudyMode("quiz")
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
                    onDismiss()
                }
            ) {
                Text("適用")
            }
        }
    )
}

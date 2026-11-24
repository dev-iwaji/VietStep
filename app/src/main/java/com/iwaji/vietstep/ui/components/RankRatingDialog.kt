package com.iwaji.vietstep.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment

@Composable
fun RankRatingDialog(
    title: String,
    message: String,
    currentRank: Int,
    onConfirm: (Int) -> Unit,
    onDismiss: () -> Unit
) {
    var selectedRank by remember {
        mutableIntStateOf(currentRank)
    }

    AlertDialog(
        onDismissRequest = {
        },
        title = {
            Text("理解度の確認")
        },
        text = {
            Column {

                Text(
                    "「$title」が終了しました。"
                )

                if (message.isNotBlank()) {
                    Spacer(Modifier.height(6.dp))

                    Text("$message")
                }

                Spacer(Modifier.height(12.dp))

                Text("現在の理解度ランク",
                    color = Color.Gray
                )

                Spacer(Modifier.height(4.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    for (rank in 1..5) {

                        Icon(
                            tint = Color.Gray,
                            imageVector =
                                if (rank <= 6 - currentRank)
                                    Icons.Filled.Star
                                else
                                    Icons.Outlined.StarBorder,

                            contentDescription = null
                        )
                    }
                    Text(
                        "  (R$currentRank)",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }

                Spacer(Modifier.height(24.dp))

                Text("新しい理解度ランク",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    for (rank in 1..5) {
                        Icon(
                            tint =
                                if (rank <= 6 - selectedRank)
                                    MaterialTheme.colorScheme.primary
                                else
                                    MaterialTheme.colorScheme.outline,

                            imageVector =
                                if (rank <= 6 - selectedRank)
                                    Icons.Filled.Star
                                else
                                    Icons.Outlined.StarBorder,

                            contentDescription = null,

                            modifier = Modifier
                                .size(40.dp)
                                .clickable {
                                    selectedRank = 6 - rank
                                }
                        )
                    }
                    Text(
                        "  (R$selectedRank)",
                        fontSize = 14.sp
                    )
                }
            }
        },
        confirmButton = {
            FilledTonalButton(
                onClick = {
                    onConfirm(selectedRank)
                }
            ) {
                Text("保存")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismiss()
                }
            ) {
                Text("キャンセル")
            }
        }
    )
}

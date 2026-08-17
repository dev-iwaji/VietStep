package com.example.vocabapp.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeDown
import androidx.compose.material.icons.filled.VolumeOff
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.example.vocabapp.R
import com.example.vocabapp.util.playSound
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import com.example.vocabapp.BuildConfig
import androidx.compose.ui.text.AnnotatedString

@Composable
fun SettingsDialog(
    darkMode: Boolean,
    soundVolume: Float,
    uid: String?,
    isOnline: Boolean,
    isSyncing: Boolean,
    syncMessage: String?,
    onDarkModeChanged: (Boolean) -> Unit,
    onSoundVolumeChanged: (Float) -> Unit,
    onDataInit: () -> Unit,
    onDownloadFromFirebase: () -> Unit,
    onLogin: () -> Unit,
    onLogout: () -> Unit,
    onDismiss: () -> Unit
) {

    val context = LocalContext.current

    var showResetDialog by remember {mutableStateOf(false)}

    var showUid by remember {mutableStateOf(false)}

    val isFirebaseLoggedIn = uid != null

    val canSync =
        isFirebaseLoggedIn &&
                isOnline &&
                !isSyncing
    AlertDialog(

        onDismissRequest = onDismiss,

        title = {
            Text("設定")
        },

        text = {

            Column {

                Text(
                    text = "Firebase同期",
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    Modifier.height(8.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("状態")

                    Spacer(
                        Modifier.width(16.dp)
                    )

                    Text(
                        text = when {
                            !isFirebaseLoggedIn ->
                                "❌ 未ログイン"

                            !isOnline ->
                                "ログイン済み（オフライン）"

                            else ->
                                "✅ 接続中"
                        }
                    )
                }
                Spacer(Modifier.width(16.dp))

                Button(
                    onClick = onDownloadFromFirebase,
                    modifier = Modifier.fillMaxWidth(),
                    enabled = canSync
                ) {
                    if (isSyncing) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            strokeWidth = 2.dp
                        )

                        Spacer(
                            modifier = Modifier.width(8.dp)
                        )

                        Text("同期中")
                    } else {
                        Text("Firebaseでの同期")
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 20.dp)
                ) {
                    when {
                        syncMessage != null -> {
                            Text(syncMessage)
                        }

                        isFirebaseLoggedIn &&
                                !isOnline -> {
                            Text(
                                "ネットワークに接続すると\n同期できます"
                            )
                        }

                        !isFirebaseLoggedIn -> {
                            Text(
                                "Googleログインすると\nFirebaseで同期できます"
                            )
                        }
                    }
                }

                if (isFirebaseLoggedIn) {

                    Spacer(Modifier.height(16.dp))

                    Text("UID")

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = {
                                showUid = !showUid
                            }, modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                if (showUid) {
                                    "非表示"
                                } else {
                                    "表示"
                                }
                            )
                        }

                        Spacer(Modifier.width(8.dp))

                        val clipboard =
                            LocalClipboardManager.current

                        Button(
                            onClick = {
                                clipboard.setText(
                                    AnnotatedString(
                                        uid ?: ""
                                    )
                                )
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("コピー")
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(20.dp)
                    ) {
                        if (showUid) {
                            Text(uid ?: "-")
                        }
                    }
                }

                Spacer(Modifier.height(16.dp))

                Text(
                    text = "Version　${BuildConfig.VERSION_NAME}"
                )

                Spacer(Modifier.height(16.dp))

                Row(
                    verticalAlignment =
                    Alignment.CenterVertically
                ) {

                    Text(
                        "ダークモード",
                        modifier = Modifier.weight(1f)
                    )

                    Switch(
                        checked = darkMode,
                        onCheckedChange =
                        onDarkModeChanged
                    )
                }

                Spacer(Modifier.height(16.dp))

                    Column {

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            // ✅ 音量アイコン
                            Icon(
                                imageVector = when {
                                    soundVolume == 0f -> Icons.Default.VolumeOff
                                    soundVolume < 0.33f -> Icons.Default.VolumeDown
                                    else -> Icons.Default.VolumeUp
                                },
                                contentDescription = null,
                                tint = when {
                                    soundVolume == 0f -> Color.Gray
                                    else -> Color(0xFF4CAF50)
                                }
                            )

                            Spacer(Modifier.width(8.dp))

                            Text("効果音の音量")

                            Spacer(Modifier.weight(1f))

                            Text("${(soundVolume * 100).toInt()}%")
                        }

                        var lastPlayTime by remember { mutableStateOf(0L) }

                        Slider(
                            value = soundVolume,
                            onValueChange = {
                                onSoundVolumeChanged(it)

                                val now = System.currentTimeMillis()

                                // ✅ 400ms以上間隔あける
                                if (now - lastPlayTime > 400) {
                                    playSound(context,
                                        R.raw.correct, soundVolume)
                                    lastPlayTime = now
                                }
                            },
                            valueRange = 0f..1f
                        )
                   }

                Spacer(Modifier.height(16.dp))

                Button(
                    onClick = {
                        showResetDialog = true
                    },
                    colors =
                    ButtonDefaults.buttonColors(
                        containerColor =
                            Color(0xFFD32F2F),
                        contentColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("学習データ初期化")
                }

                Spacer(Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (isFirebaseLoggedIn) {
                            onLogout()
                        } else {
                            onLogin()
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        if (isFirebaseLoggedIn) {
                            "ログアウト"
                        } else {
                            "Googleでログイン"
                        }
                    )
                }
            }

            if (showResetDialog) {

                AlertDialog(

                    onDismissRequest = {
                        showResetDialog = false
                    },

                    title = {
                        Text("学習データ初期化")
                    },

                    text = {
                        Text(
                            """
                                        以下のデータを削除します。

                                        ・単語レベル
                                        ・学習履歴
                                        ・お気に入り
                                        ・CSV設定
                                        ・デッキ情報

                                        本当に初期化しますか？
                                        """.trimIndent()
                        )
                    },

                    dismissButton = {

                        TextButton(
                            onClick = {
                                showResetDialog = false
                            }
                        ) {
                            Text("キャンセル")
                        }
                    },

                    confirmButton = {

                        Button(
                            onClick = {
                                showResetDialog = false
                                onDataInit()
                            }
                        ) {
                            Text("初期化")
                        }
                    }
                )
            }
        },

        confirmButton = {

            TextButton(
                onClick = onDismiss
            ) {
                Text("閉じる")
            }
        }
    )
}
// MainScreen.kt

package com.example.vocabapp.ui.main

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeDown
import androidx.compose.material.icons.filled.VolumeOff
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vocabapp.R
import com.example.vocabapp.ui.chunk.ChunkScreen
import com.example.vocabapp.ui.conversation.ConversationScreen
import com.example.vocabapp.ui.grammar.GrammarScreen
import com.example.vocabapp.ui.search.SearchScreen
import com.example.vocabapp.ui.stats.StatsScreen
import com.example.vocabapp.data.repository.FirebaseRepository
import com.example.vocabapp.manager.ResetManager
import com.example.vocabapp.ui.auth.AuthViewModel
import com.example.vocabapp.ui.settings.SettingsDialog
import com.example.vocabapp.ui.word.WordScreen
import com.example.vocabapp.ui.word.WordViewModel
import com.example.vocabapp.util.playSound
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(authViewModel: AuthViewModel) {

    val context = LocalContext.current
    val prefs = context.getSharedPreferences("app", Context.MODE_PRIVATE)

    val mainViewModel: MainViewModel = viewModel()

    val uiState by mainViewModel.uiState.collectAsState()

    val wordViewModel: WordViewModel = viewModel()

    val firebaseRepository = FirebaseRepository()

    // ✅ 初期化処理
    LaunchedEffect(Unit) {
        val localResetAT = uiState.resetAT
        val firebaseResetAT = firebaseRepository.loadResetAT()
        if (firebaseResetAT > localResetAT) {
            ResetManager.resetAll(prefs, firebaseRepository)
            mainViewModel.setResetAT(firebaseResetAT)
        }

        // ✅ mainViewModelを構築
        mainViewModel.initialize(prefs)
        mainViewModel.load()

        // ✅ wordViewModelを構築
        wordViewModel.initialize(prefs)
        wordViewModel.load(context)
    }

    var tab by remember { mutableStateOf(0) }

    // ✅ 効果音設定
    var soundEnabled by remember { mutableStateOf(true) }

    // ✅ TTS
    var tts: TextToSpeech? by remember { mutableStateOf(null) }

    DisposableEffect(Unit) {
        val speechContext = TextToSpeech(context) {
            tts?.language = Locale("vi", "VN")
        }
        tts = speechContext
        onDispose { speechContext.shutdown() }
    }

    var menuExpanded by remember { mutableStateOf(false) }

    // ✅ 学習データ初期化
    var statsRefreshKey by remember { mutableStateOf(0) }

    // ✅ ユーザー設定ダイアログ
    var showSettings by remember { mutableStateOf(false) }

    MaterialTheme(
        colorScheme = if (uiState.darkMode) {
            darkColorScheme(
                background = Color.Black,
                surface = Color.Black,
                onSurface = Color.White,
                onBackground = Color.White
            )
        } else {
            lightColorScheme()
        }
    ) {

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("VietStep") },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    actions = {
                        var showResetDialog by remember {
                            mutableStateOf(false)
                        }

                        Box {

                            IconButton(onClick = {
                                menuExpanded = true
                            }) {
                                Text("☰", fontSize = 22.sp)
                            }

                            DropdownMenu(
                                expanded = menuExpanded,
                                onDismissRequest = { menuExpanded = false }
                            ) {

                                DropdownMenuItem(
                                    text = {
                                        Text("設定")
                                    },
                                    onClick = {
                                        showSettings = true
                                    }
                                )
                            }
                        }

                        if (showSettings) {
                            SettingsDialog(
                                darkMode = uiState.darkMode,
                                soundVolume = uiState.soundVolume,
                                uid = authViewModel.uiState
                                    .collectAsState()
                                    .value
                                    .uid,
                                onDarkModeChanged = {
                                    mainViewModel.setDarkMode(it)
                                },
                                onSoundVolumeChanged = {
                                    mainViewModel.setSoundVolume(it)
                                },
                                onDataInit = {
                                    menuExpanded = false
                                    showSettings = false

                                    ResetManager.resetAll(prefs, firebaseRepository)
                                    mainViewModel.setResetAT(
                                        System.currentTimeMillis()
                                    )
                                    wordViewModel.load(context)

                                    showResetDialog = false
                                    statsRefreshKey++
                                    tab = 0

                                },
                                onLogout = {
                                    menuExpanded = false
                                    showSettings = false
                                    authViewModel.logout()
                                },
                                onDismiss = {
                                    menuExpanded = false
                                    showSettings = false
                                }
                            )
                        }
                    }
                )
            }

        ) { padding ->

            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {

                Column(Modifier.fillMaxSize()) {

                    // ✅ タブ
                    TabRow(selectedTabIndex = tab) {
                        Tab(tab == 0, onClick = { tab = 0 }) { Text("単語") }
                        Tab(tab == 1, onClick = { tab = 1 }) { Text("文型") }
                        Tab(tab == 2, onClick = { tab = 2 }) { Text("文法") }
                        Tab(tab == 3, onClick = { tab = 3 }) { Text("会話") }
                        Tab(tab == 4, onClick = { tab = 4 }) { Text("検索") }
                        Tab(tab == 5, onClick = { tab = 5 }) { Text("統計") }
                    }

                    // =========================
                    // ✅ 単語
                    // =========================
                    if (tab == 0) {
                        WordScreen(
                            soundEnabled = soundEnabled,
                            soundVolume = uiState.soundVolume,
                            tts = tts,
                            wordViewModel = wordViewModel,
                        )
                    }

                    // =========================
                    // ✅ 文型
                    // =========================
                    if (tab == 1) {
                        ChunkScreen(
                            soundEnabled = soundEnabled,
                            soundVolume = uiState.soundVolume,
                            prefs = prefs,
                            tts = tts
                        )
                    }

                    // =========================
                    // ✅ 文法
                    // =========================
                    if (tab == 2) {
                        GrammarScreen(
                            soundEnabled = soundEnabled,
                            soundVolume = uiState.soundVolume,
                            prefs = prefs,
                            tts = tts
                        )
                    }

                    // =========================
                    // ✅ 会話
                    // =========================
                    if (tab == 3) {
                        ConversationScreen(
                            soundEnabled = soundEnabled,
                            soundVolume = uiState.soundVolume,
                            prefs = prefs,
                            tts = tts
                        )
                    }

                    // =========================
                    // ✅ 検索
                    // =========================
                    if (tab == 4) {

                        SearchScreen(
                            wordViewModel = wordViewModel,
                            onSelect = { word ->
                                wordViewModel.jumpToWord(word)
                                tab = 0
                            }
                        )
                    }

                    // =========================
                    // ✅ 統計
                    // =========================
                    if (tab == 5) {
                        StatsScreen(
                            wordViewModel = wordViewModel,
                            refreshKey = statsRefreshKey
                        )
                    }
                }
            }
        }
    }
}

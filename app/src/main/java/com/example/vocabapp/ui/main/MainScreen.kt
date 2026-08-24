package com.example.vocabapp.ui.main

import java.util.Locale

import kotlinx.coroutines.launch

import android.content.Context
import android.speech.tts.TextToSpeech

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.vocabapp.ui.word.WordScreen
import com.example.vocabapp.ui.word.WordViewModel
import com.example.vocabapp.ui.chunk.ChunkScreen
import com.example.vocabapp.ui.chunk.ChunkViewModel
import com.example.vocabapp.ui.grammar.GrammarScreen
import com.example.vocabapp.ui.grammar.GrammarViewModel
import com.example.vocabapp.ui.conversation.ConversationScreen
import com.example.vocabapp.ui.conversation.ConversationViewModel
import com.example.vocabapp.ui.search.SearchScreen
import com.example.vocabapp.ui.stats.StatsScreen
import com.example.vocabapp.ui.auth.AuthViewModel
import com.example.vocabapp.ui.settings.SettingsDialog
import com.example.vocabapp.ui.sync.SyncViewModel
import com.example.vocabapp.ui.login.LoginScreen
import com.example.vocabapp.manager.ResetManager
import com.example.vocabapp.data.repository.FirebaseRepository
import com.example.vocabapp.data.repository.ChunkRepository
import com.example.vocabapp.data.repository.SyncRepository
import com.example.vocabapp.data.repository.WordRepository
import com.example.vocabapp.data.repository.ConversationRepository
import com.example.vocabapp.data.repository.GrammarRepository
import com.example.vocabapp.util.NetworkMonitor
import android.util.Log

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    authViewModel: AuthViewModel,
    mainViewModel: MainViewModel
) {

    val context = LocalContext.current
    val prefs = context.getSharedPreferences("app", Context.MODE_PRIVATE)

    val mainUiState by mainViewModel.uiState.collectAsState()

    val wordViewModel: WordViewModel = viewModel()

    val chunkViewModel: ChunkViewModel = viewModel()

    val grammarViewModel: GrammarViewModel = viewModel()

    val conversationViewModel: ConversationViewModel = viewModel()

    val syncViewModel: SyncViewModel = viewModel()

    val syncUiState by syncViewModel.uiState.collectAsState()

    val firebaseRepository = FirebaseRepository()

    val networkMonitor = remember { NetworkMonitor(context) }

    val syncRepository = remember(
        prefs,
        firebaseRepository
    ) {
        val syncWordRepository =
            WordRepository(
                prefs = prefs,
                firebaseRepository = firebaseRepository
            )

        val syncChunkRepository =
            ChunkRepository(
                prefs = prefs,
                firebaseRepository = firebaseRepository
            )

        val syncGrammarRepository =
            GrammarRepository(
                prefs = prefs,
                firebaseRepository = firebaseRepository
            )

        val syncConversationRepository =
            ConversationRepository(
                prefs = prefs,
                firebaseRepository = firebaseRepository
            )

        SyncRepository(
            firebaseRepository = firebaseRepository,
            wordRepository = syncWordRepository,
            chunkRepository = syncChunkRepository,
            grammarRepository = syncGrammarRepository,
            conversationRepository = syncConversationRepository,
        )
    }

    var showLogin by remember {
        mutableStateOf(false)
    }

    // ✅ 初期化処理
    LaunchedEffect(Unit) {
        // ✅ 最初に全ViewModelを初期化
        wordViewModel.initialize(prefs)
        chunkViewModel.initialize(prefs)
        grammarViewModel.initialize(prefs)
        conversationViewModel.initialize(prefs)

        syncViewModel.initialize(
            repository = syncRepository
        )

        // ✅ SharedPreferencesから直接取得
        val localResetAT = mainViewModel.getLocalResetAT()

        val firebaseResetAT =
            try {
                firebaseRepository.loadResetAT()
            } catch (e: Exception) {
                Log.w(
                    "RESET",
                    "resetATの取得に失敗しました。ローカル状態を使用します",
                    e
                )
                0L
            }

        // ✅ 他端末で新しい初期化が行われた場合だけローカル削除
        val shouldReset =
            firebaseResetAT != null &&
                    firebaseResetAT > localResetAT

        if (shouldReset) {
            try {
                ResetManager.resetLocalOnly(
                    prefs = prefs
                )

                syncRepository.restoreLearningConditionsAfterReset()

                mainViewModel.setLocalResetAT(
                    firebaseResetAT!!
                )
            } catch (e: Exception) {
                Log.e(
                    "RESET",
                    "Firebaseの初期化状態の反映に失敗しました",
                    e
                )
            }
        }

        // ✅ リセット判定後にローカル学習データを読み込む
        wordViewModel.load(context)
        chunkViewModel.load()
        grammarViewModel.load()
        conversationViewModel.load()
    }

    var tab by remember { mutableStateOf(0) }

    val lifecycleOwner =
        LocalLifecycleOwner.current

    DisposableEffect(
        lifecycleOwner,
        wordViewModel,
        chunkViewModel
    ) {
        val observer =
            LifecycleEventObserver { _, event ->

                if (event == Lifecycle.Event.ON_STOP) {
                    wordViewModel.uploadLearningStateIfDirty()
                    chunkViewModel.uploadLearningStateIfDirty()
                }
            }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    val scope = rememberCoroutineScope()

    DisposableEffect(
        networkMonitor
    ) {

        val callback =
            networkMonitor.registerCallback { online ->

                scope.launch {
                    syncViewModel.updateOnlineState(
                        online
                    )
                }
            }

        onDispose {
            networkMonitor.unregisterCallback(
                callback
            )
        }
    }

    fun selectTab(newTab: Int) {
        if (newTab == tab) {
            return
        }

        when (tab) {
            0 -> {
                wordViewModel.uploadLearningStateIfDirty()
            }

            1 -> {
                chunkViewModel.uploadLearningStateIfDirty()
            }
        }

        tab = newTab
    }

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

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("VietStep") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                actions = {

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
                                    syncViewModel.updateOnlineState(
                                        networkMonitor.isOnline()
                                    )

                                    syncViewModel.clearMessage()

                                    showSettings = true
                                    menuExpanded = false
                                }
                            )
                        }
                    }

                    if (showSettings) {
                        SettingsDialog(
                            darkMode = mainUiState.darkMode,
                            soundVolume = mainUiState.soundVolume,
                            uid = firebaseRepository.getUid(),
                            isOnline = syncUiState.isOnline,
                            isSyncing = syncUiState.isSyncing,
                            syncMessage = syncUiState.message,
                            onDarkModeChanged = {
                                mainViewModel.setDarkMode(it)
                            },
                            onSoundVolumeChanged = {
                                mainViewModel.setSoundVolume(it)
                            },
                            onDataInit = {
                                menuExpanded = false
                                showSettings = false

                                scope.launch {
                                    try {
                                        ResetManager.resetAll(
                                            prefs,
                                            firebaseRepository
                                        )

                                        val resetTime =
                                            System.currentTimeMillis()

                                        mainViewModel.setResetAT(
                                            resetTime
                                        )

                                        wordViewModel.load(context)
                                        chunkViewModel.load()
                                        grammarViewModel.load()
                                        conversationViewModel.load()

                                        statsRefreshKey++
                                        tab = 0

                                    } catch (e: Exception) {
                                        Log.e(
                                            "RESET",
                                            "学習データの初期化に失敗しました",
                                            e
                                        )
                                    }
                                }
                            },
                            onLogin = {
                                syncViewModel.clearMessage()

                                showSettings = false
                                showLogin = true
                                menuExpanded = false
                            },
                            onLogout = {
                                menuExpanded = false
                                showSettings = false
                                authViewModel.logout()
                                showSettings = true
                                },
                            onDownloadFromFirebase = {
                                val online = networkMonitor.isOnline()
                                syncViewModel.updateOnlineState(online)
                                if (online) {
                                    syncViewModel.requestDownload()
                                }
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
                    Tab(tab == 0, onClick = { selectTab(0) }) { Text("単語") }
                    Tab(tab == 1, onClick = { selectTab(1) }) { Text("文型") }
                    Tab(tab == 2, onClick = { selectTab(2) }) { Text("文法") }
                    Tab(tab == 3, onClick = { selectTab(3) }) { Text("会話") }
                    Tab(tab == 4, onClick = { selectTab(4) }) { Text("検索") }
                    Tab(tab == 5, onClick = { selectTab(5) }) { Text("統計") }
                }

                // =========================
                // ✅ 単語
                // =========================
                if (tab == 0) {
                    WordScreen(
                        soundEnabled = soundEnabled,
                        soundVolume = mainUiState.soundVolume,
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
                        soundVolume = mainUiState.soundVolume,
                        tts = tts,
                        viewModel = chunkViewModel
                    )
                }

                // =========================
                // ✅ 文法
                // =========================
                if (tab == 2) {
                    GrammarScreen(
                        soundEnabled = soundEnabled,
                        soundVolume = mainUiState.soundVolume,
                        tts = tts,
                        viewModel = grammarViewModel
                    )
                }

                // =========================
                // ✅ 会話
                // =========================
                if (tab == 3) {
                    ConversationScreen(
                        soundEnabled = soundEnabled,
                        soundVolume = mainUiState.soundVolume,
                        tts = tts,
                        viewModel = conversationViewModel
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

    if (showLogin) {
        Dialog(
            onDismissRequest = {
                showLogin = false
                showSettings = true
            }
        ) {
            LoginScreen(
                authViewModel = authViewModel,
                isOnline = syncUiState.isOnline,
                onDismiss = {
                    showLogin = false
                    showSettings = true
                }
            )
        }
    }

    if (syncUiState.showOverwriteConfirmation) {
        AlertDialog(
            onDismissRequest = {
                syncViewModel.cancelDownload()
            },
            title = {
                Text("Firebaseでの同期")
            },
            text = {
                Text(
                    "この端末の現在の単語・チャンクの学習状態を、" +
                            "Firebaseに保存されている状態で上書きします。"
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        syncViewModel.confirmDownload(
                            context = context,
                            onCompleted = {
                                wordViewModel.load(context)
                                chunkViewModel.load()
                                grammarViewModel.load()
                                conversationViewModel.load()

                                statsRefreshKey++
                            }
                        )
                    }
                ) {
                    Text("同期する")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        syncViewModel.cancelDownload()
                    }
                ) {
                    Text("キャンセル")
                }
            }
        )
    }
}

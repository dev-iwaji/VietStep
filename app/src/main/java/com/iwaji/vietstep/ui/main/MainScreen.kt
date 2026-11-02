package com.iwaji.vietstep.ui.main

import java.util.Locale

import kotlinx.coroutines.launch

import android.content.Context
import android.speech.tts.TextToSpeech
import android.widget.Toast

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem

import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException

import com.iwaji.vietstep.ui.word.WordScreen
import com.iwaji.vietstep.ui.word.WordViewModel
import com.iwaji.vietstep.ui.chunk.ChunkScreen
import com.iwaji.vietstep.ui.chunk.ChunkViewModel
import com.iwaji.vietstep.ui.grammar.GrammarScreen
import com.iwaji.vietstep.ui.grammar.GrammarViewModel
import com.iwaji.vietstep.ui.conversation.ConversationScreen
import com.iwaji.vietstep.ui.conversation.ConversationViewModel
import com.iwaji.vietstep.ui.search.SearchScreen
import com.iwaji.vietstep.ui.stats.StatsScreen
import com.iwaji.vietstep.ui.auth.AuthViewModel
import com.iwaji.vietstep.ui.settings.SettingsDialog
import com.iwaji.vietstep.ui.sync.SyncViewModel
import com.iwaji.vietstep.ui.login.LoginScreen
import com.iwaji.vietstep.manager.ResetManager
import com.iwaji.vietstep.data.repository.FirebaseRepository
import com.iwaji.vietstep.data.repository.ChunkRepository
import com.iwaji.vietstep.data.repository.SyncRepository
import com.iwaji.vietstep.data.repository.WordRepository
import com.iwaji.vietstep.data.repository.ConversationRepository
import com.iwaji.vietstep.data.repository.GrammarRepository
import com.iwaji.vietstep.util.NetworkMonitor
import com.iwaji.vietstep.util.GoogleLoginHelper
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

    var isDeleting by remember {
        mutableStateOf(false)
    }

    fun resetAll() {
        // ✅ リセット判定後にローカル学習データを読み込む
        wordViewModel.load(context)
        chunkViewModel.load()
        grammarViewModel.load()
        conversationViewModel.load()

        // ✅ リセット判定後にクイズモードを初期化する
        wordViewModel.resetQuizStats()
        chunkViewModel.resetQuizStats()
        grammarViewModel.resetQuizStats()
        conversationViewModel.resetQuizStats()
    }

    suspend fun syncResetState(): Boolean {

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

        return shouldReset
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

        syncResetState()

        resetAll()
    }

    val lifecycleOwner =
        LocalLifecycleOwner.current

    DisposableEffect(
        lifecycleOwner,
        wordViewModel,
        chunkViewModel,
        grammarViewModel,
        conversationViewModel
    ) {
        val observer =
            LifecycleEventObserver { _, event ->

                if (event == Lifecycle.Event.ON_STOP) {
                    wordViewModel.uploadLearningStateIfDirty()
                    chunkViewModel.uploadLearningStateIfDirty()
                    grammarViewModel.uploadDeckStateIfDirty()
                    conversationViewModel.uploadDeckStateIfDirty()
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
                            isDeleting = isDeleting,
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

                                        wordViewModel.resetQuizStats()
                                        chunkViewModel.resetQuizStats()
                                        grammarViewModel.resetQuizStats()
                                        conversationViewModel.resetQuizStats()

                                        statsRefreshKey++

                                        mainViewModel.setLearningTab(
                                            LearningTab.WORD
                                        )

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
                            onDeleteAccount = {
                                isDeleting = true

                                authViewModel.deleteAccount(

                                    onSuccess = {

                                        GoogleLoginHelper
                                            .getClient(context)
                                            .signOut()
                                            .addOnCompleteListener {

                                                authViewModel.refreshLoginState()

                                                Toast.makeText(
                                                    context,
                                                    "アカウントを削除しました",
                                                    Toast.LENGTH_LONG
                                                ).show()

                                                isDeleting = false
                                                showSettings = true
                                                showLogin = false
                                            }
                                    },

                                    onError = { e ->
                                        isDeleting = false

                                        if (e is FirebaseAuthRecentLoginRequiredException) {

                                            Toast.makeText(
                                                context,
                                                "アカウント削除には再ログインが必要です",
                                                Toast.LENGTH_LONG
                                            ).show()

                                            showSettings = false
                                            showLogin = true

                                        } else {

                                            Toast.makeText(
                                                context,
                                                e.message ?: "削除に失敗しました",
                                                Toast.LENGTH_LONG
                                            ).show()

                                            showSettings = true
                                            showLogin = false
                                        }
                                    }
                                )
                            },
                            onDismiss = {
                                    menuExpanded = false
                                    showSettings = false
                            }
                        )
                    }
                }
            )
        },

        bottomBar = {

            NavigationBar {

                NavigationBarItem(
                    selected = mainUiState.bottomPage == BottomPage.LEARNING,
                    onClick = {
                        mainViewModel.setBottomPage(
                            BottomPage.LEARNING
                        )
                    },
                    icon = {
                        Icon(Icons.Default.School, null)
                    },
                    label = {
                        Text("学習")
                    }
                )

                NavigationBarItem(
                    selected = mainUiState.bottomPage == BottomPage.SEARCH,
                    onClick = {
                        mainViewModel.setBottomPage(
                            BottomPage.SEARCH
                        )
                    },
                    icon = {
                        Icon(Icons.Default.Search, null)
                    },
                    label = {
                        Text("検索")
                    }
                )

                NavigationBarItem(
                    selected = mainUiState.bottomPage == BottomPage.STATS,
                    onClick = {
                        mainViewModel.setBottomPage(
                            BottomPage.STATS
                        )
                    },
                    icon = {
                        Icon(Icons.Default.BarChart, null)
                    },
                    label = {
                        Text("統計")
                    }
                )

                NavigationBarItem(
                    selected = mainUiState.bottomPage == BottomPage.SETTINGS,
                    onClick = {
                        mainViewModel.setBottomPage(
                            BottomPage.SETTINGS
                        )
                    },
                    icon = {
                        Icon(Icons.Default.Settings, null)
                    },
                    label = {
                        Text("設定")
                    }
                )
            }
        }
    ) { padding ->

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            when (mainUiState.bottomPage) {

                BottomPage.LEARNING -> {

                    Column {

                        TabRow(
                            selectedTabIndex =
                                mainUiState.learningTab.ordinal
                        ) {

                            Tab(
                                selected =
                                    mainUiState.learningTab == LearningTab.WORD,
                                onClick = {
                                    mainViewModel.setLearningTab(
                                        LearningTab.WORD
                                    )
                                }
                            ) {
                                Text("単語")
                            }

                            Tab(
                                selected =
                                    mainUiState.learningTab == LearningTab.CHUNK,
                                onClick = {
                                    mainViewModel.setLearningTab(
                                        LearningTab.CHUNK
                                    )
                                }
                            ) {
                                Text("文型")
                            }

                            Tab(
                                selected =
                                    mainUiState.learningTab == LearningTab.GRAMMAR,
                                onClick = {
                                    mainViewModel.setLearningTab(
                                        LearningTab.GRAMMAR
                                    )
                                }
                            ) {
                                Text("文法")
                            }

                            Tab(
                                selected =
                                    mainUiState.learningTab == LearningTab.CONVERSATION,
                                onClick = {
                                    mainViewModel.setLearningTab(
                                        LearningTab.CONVERSATION
                                    )
                                }
                            ) {
                                Text("会話")
                            }
                        }

                        when (mainUiState.learningTab) {

                            LearningTab.WORD ->
                                WordScreen(
                                    soundEnabled = soundEnabled,
                                    soundVolume = mainUiState.soundVolume,
                                    tts = tts,
                                    wordViewModel = wordViewModel,
                                )

                            LearningTab.CHUNK ->
                                ChunkScreen(
                                    soundEnabled = soundEnabled,
                                    soundVolume = mainUiState.soundVolume,
                                    tts = tts,
                                    viewModel = chunkViewModel
                                )

                            LearningTab.GRAMMAR ->
                                GrammarScreen(
                                    soundEnabled = soundEnabled,
                                    soundVolume = mainUiState.soundVolume,
                                    tts = tts,
                                    viewModel = grammarViewModel
                                )

                            LearningTab.CONVERSATION ->
                                ConversationScreen(
                                    soundEnabled = soundEnabled,
                                    soundVolume = mainUiState.soundVolume,
                                    tts = tts,
                                    viewModel = conversationViewModel
                                )
                        }
                    }
                }

                BottomPage.SEARCH -> {

                    SearchScreen(
                        wordViewModel = wordViewModel,
                        onSelect = { word ->

                            wordViewModel.jumpToWord(word)

                            mainViewModel.setBottomPage(
                                BottomPage.LEARNING
                            )

                            mainViewModel.setLearningTab(
                                LearningTab.WORD
                            )
                        }
                    )
                }

                BottomPage.STATS -> {

                    StatsScreen(
                        wordViewModel = wordViewModel,
                        chunkViewModel = chunkViewModel,
                        grammarViewModel = grammarViewModel,
                        conversationViewModel = conversationViewModel,
                        refreshKey = statsRefreshKey
                    )
                }

                BottomPage.SETTINGS -> {

                    // 後でSettingsScreenにする
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
                onLoginSuccess = {
                    scope.launch {
                        if (syncResetState())
                            resetAll()
                    }
                },
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

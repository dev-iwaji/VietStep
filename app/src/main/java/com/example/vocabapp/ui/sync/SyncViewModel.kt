package com.example.vocabapp.ui.sync

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vocabapp.data.repository.SyncRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SyncViewModel : ViewModel() {

    private lateinit var repository: SyncRepository

    private val _uiState =
        MutableStateFlow(SyncUiState())

    val uiState: StateFlow<SyncUiState> =
        _uiState.asStateFlow()

    fun initialize(
        repository: SyncRepository
    ) {
        this.repository = repository
    }

    fun updateOnlineState(
        isOnline: Boolean
    ) {
        _uiState.update {
            it.copy(
                isOnline = isOnline
            )
        }
    }

    fun requestDownload() {
        if (!_uiState.value.isOnline) {
            _uiState.update {
                it.copy(
                    message = "ネットワークに接続してください"
                )
            }
            return
        }

        _uiState.update {
            it.copy(
                showOverwriteConfirmation = true,
                message = null
            )
        }
    }

    fun confirmDownload(
        context: Context,
        onCompleted: () -> Unit
    ) {
        if (!::repository.isInitialized) {
            _uiState.update {
                it.copy(
                    showOverwriteConfirmation = false,
                    message = "同期処理が初期化されていません"
                )
            }
            return
        }

        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isSyncing = true,
                    showOverwriteConfirmation = false,
                    message = null
                )
            }

            val result =
                repository.downloadAllLearningState(context)

            result.onSuccess {
                _uiState.update {
                    it.copy(
                        isSyncing = false,
                        message = "Firebaseの学習状態を取得しました"
                    )
                }

                onCompleted()
            }

            result.onFailure { error ->
                Log.e(
                    "SYNC",
                    "ダウンロードに失敗しました",
                    error
                )

                _uiState.update {
                    it.copy(
                        isSyncing = false,
                        message = when {
                            !uiState.value.isOnline -> {
                                "ネットワークに接続してください"
                            }

                            error is IllegalStateException -> {
                                error.message
                                    ?: "同期データを取得できませんでした"
                            }

                            else -> {
                                "同期に失敗しました。通信状態を確認してください"
                            }
                        }
                    )
                }
            }        }
    }

    fun cancelDownload() {
        _uiState.update {
            it.copy(
                showOverwriteConfirmation = false
            )
        }
    }

    fun clearMessage() {
        _uiState.update {
            it.copy(
                message = null
            )
        }
    }
}
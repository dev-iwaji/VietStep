package com.example.vocabapp.ui.sync

data class SyncUiState(
    val isOnline: Boolean = false,

    val isSyncing: Boolean = false,

    val lastSyncedAt: Long? = null,

    val message: String? = null,

    val showOverwriteConfirmation: Boolean = false,
)

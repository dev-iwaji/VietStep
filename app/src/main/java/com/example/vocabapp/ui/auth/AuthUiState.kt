package com.example.vocabapp.ui.auth

data class AuthUiState(
    val isLoading: Boolean = true,

    val isLoggedIn: Boolean = false,

    val uid: String? = null,

    val isOffline: Boolean = false,

    val isFirebaseConnected: Boolean = false,
)

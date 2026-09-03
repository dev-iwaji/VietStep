package com.iwaji.vietstep.ui.auth

data class AuthUiState(
    val isLoading: Boolean = true,

    val isLoggedIn: Boolean = false,

    val uid: String? = null,
)

package com.example.vocabapp.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vocabapp.data.repository.FirebaseRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val firebaseRepository =
        FirebaseRepository()

    private val _uiState =
        MutableStateFlow(AuthUiState())

    val uiState = _uiState.asStateFlow()

    fun init() {
        load()
        observeAuthState()
    }

    private fun observeAuthState() {
        viewModelScope.launch {
            firebaseRepository.authState.collect { user ->
                _uiState.update { old ->
                    old.copy(
                        isLoggedIn = user != null || old.isOffline,
                        uid = user?.uid
                    )
                }/*                _uiState.value = AuthUiState(
                    isLoading = false,
                    isLoggedIn = user != null,
                    uid = user?.uid
                )
*/
            }
        }
    }

    fun load() {

        _uiState.value =
            _uiState.value.copy(
//            AuthUiState(
                isLoading = false,
                isLoggedIn = firebaseRepository.getUid() != null,
                uid = firebaseRepository.getUid()
            )
    }

    fun logout() {
        firebaseRepository.logout()
    }

    fun refreshLoginState() {
        load()
    }

    fun enterOfflineMode() {
        _uiState.value = AuthUiState(
            isLoading = false,
            isLoggedIn = false,
            uid = null,
            isOffline = true
        )
    }

    fun continueOffline() {
        _uiState.update {
            it.copy(
                isLoggedIn = true,
                isOffline = true,
                uid = null
            )
        }
    }
}


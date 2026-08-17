package com.example.vocabapp.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vocabapp.data.repository.FirebaseRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.Job

class AuthViewModel : ViewModel() {

    private val firebaseRepository =
        FirebaseRepository()

    private val _uiState =
        MutableStateFlow(AuthUiState())

    private var observeJob: Job? = null

    val uiState = _uiState.asStateFlow()

    fun init() {
        observeAuthState()
    }

    private fun observeAuthState() {
        if (observeJob?.isActive == true) {
            return
        }

        observeJob =
            viewModelScope.launch {
                firebaseRepository.authState.collect { user ->

                    if (user != null) {
                        _uiState.value =
                            AuthUiState(
                                isLoading = false,
                                isLoggedIn = true,
                                uid = user.uid
                            )
                    }
                }
            }
    }

    fun logout() {
        firebaseRepository.logout()
    }

    fun refreshLoginState() {

        val uid =
            firebaseRepository.getUid()

        _uiState.value =
            AuthUiState(
                isLoading = false,
                isLoggedIn =
                uid != null,
                uid = uid
            )
    }
}

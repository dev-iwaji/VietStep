package com.iwaji.vietstep.ui.auth

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.Job

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.iwaji.vietstep.data.repository.FirebaseRepository
import android.util.Log

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

    fun deleteAccount(
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {

        viewModelScope.launch {

            try {
                firebaseRepository.deleteAccount()
                onSuccess()
            } catch (e: Exception) {
                onError(e)
            }
        }
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

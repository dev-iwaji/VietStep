package com.example.vocabapp.ui.main

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel

import com.example.vocabapp.data.repository.FirebaseRepository
import com.example.vocabapp.data.repository.MainRepository
import android.util.Log

class MainViewModel : ViewModel() {

    private lateinit var repository: MainRepository

    fun initialize(
        prefs: SharedPreferences,
    ) {
        if (!::repository.isInitialized) {
            repository = MainRepository(
                prefs,
                FirebaseRepository()
            )
        }
    }

    private val _uiState =
        MutableStateFlow(
            MainUiState()
        )

    val uiState =
        _uiState.asStateFlow()

    fun load() {
        _uiState.update {
            it.copy(
                resetAT =
                repository.loadResetAT(),

                soundVolume =
                repository.loadSoundVolume(),

                darkMode =
                repository.loadDarkMode()
            )
        }
    }

    fun setLocalResetAT(
        time: Long
    ) {
        repository.saveLocalResetAT(time)

        _uiState.update {
            it.copy(
                resetAT = time
            )
        }
    }

    fun setResetAT(
        time: Long
    ) {
        repository.saveResetAT(time)
    }

    fun getLocalResetAT(): Long {
        check(::repository.isInitialized) {
            "MainViewModel is not initialized"
        }

        return repository.loadResetAT()
    }

    fun setSoundVolume(
        volume: Float
    ) {
        repository.saveSoundVolume(volume)

        _uiState.update {

            it.copy(
                soundVolume = volume
            )
        }
    }

    fun setDarkMode(
        enabled: Boolean
    ) {
        repository.saveDarkMode(enabled)

        _uiState.update {

            it.copy(
                darkMode = enabled
            )
        }
    }
}

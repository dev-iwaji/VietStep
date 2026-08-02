package com.example.vocabapp.ui.main

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vocabapp.data.model.Word
import com.example.vocabapp.data.repository.ChunkRepository
import com.example.vocabapp.data.repository.ConversationRepository
import com.example.vocabapp.data.repository.FirebaseRepository
import com.example.vocabapp.data.repository.GrammarRepository
import com.example.vocabapp.data.repository.MainRepository
import com.example.vocabapp.data.repository.WordRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

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

        viewModelScope.launch {
            repository.restoreFromFirebase()

            val resetAT = repository.loadResetAT()
            val soundVolume = repository.loadSoundVolume()
            val darkMode = repository.loadDarkMode()

            _uiState.update {
                it.copy(
                    resetAT = resetAT,
                    soundVolume = soundVolume,
                    darkMode = darkMode
                )
            }
        }

        _uiState.update {

            it.copy(
                soundVolume = repository.loadSoundVolume(),
                darkMode = repository.loadDarkMode(),
            )
        }
    }

    fun setResetAT(time: Long) {
        repository.saveResetAT(time)
    }

    fun setSoundVolume(volume: Float) {
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
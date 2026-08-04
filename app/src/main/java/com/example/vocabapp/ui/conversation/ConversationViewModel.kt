package com.example.vocabapp.ui.conversation

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vocabapp.data.repository.FirebaseRepository
import com.example.vocabapp.data.repository.ConversationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ConversationViewModel : ViewModel() {

    private lateinit var repository: ConversationRepository

    fun initialize(
        prefs: SharedPreferences,
    ) {
        if (!::repository.isInitialized) {
            repository = ConversationRepository(
                prefs,
                FirebaseRepository()
            )
        }
    }

    private val _uiState =
        MutableStateFlow(
            ConversationUiState()
        )

    val uiState =
        _uiState.asStateFlow()

    fun load() {
        loadLocalData()
    }

    private fun loadLocalData() {

        _uiState.update {

            it.copy(
                selectedTheme = repository.loadSelectedTheme(),
                selectedPart = repository.loadSelectedPart(),
                speechRate = repository.loadSpeechRate(),
            )
        }
    }

    fun setTheme(theme: String) {
        repository.saveSelectedTheme(theme)

        _uiState.update {

            it.copy(
                selectedTheme = theme
            )
        }
    }

    fun setPart(part: String) {
        repository.saveSelectedPart(part)

        _uiState.update {

            it.copy(
                selectedPart = part
            )
        }
    }

    fun setStudyMode(
        mode: String
    ) {

        _uiState.update {

            it.copy(
                studyMode = mode
            )
        }
    }

    fun setSpeechRate(speed: Float) {
        repository.saveSpeechRate(speed)

        _uiState.update {

            it.copy(
                speechRate = speed
            )
        }
    }
}
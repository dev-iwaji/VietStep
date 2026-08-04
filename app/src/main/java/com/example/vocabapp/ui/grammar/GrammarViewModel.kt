package com.example.vocabapp.ui.grammar

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vocabapp.data.repository.FirebaseRepository
import com.example.vocabapp.data.repository.GrammarRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GrammarViewModel : ViewModel() {

    private lateinit var repository: GrammarRepository

    fun initialize(
        prefs: SharedPreferences,
    ) {
        if (!::repository.isInitialized) {
            repository = GrammarRepository(
                prefs,
                FirebaseRepository()
            )
        }
    }

    private val _uiState =
        MutableStateFlow(
            GrammarUiState()
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
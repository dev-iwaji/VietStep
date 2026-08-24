package com.example.vocabapp.ui.grammar

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.example.vocabapp.data.repository.FirebaseRepository
import com.example.vocabapp.data.repository.GrammarRepository
import com.example.vocabapp.data.model.updated
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


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

    fun applySettings(
        theme: String,
        studyMode: String
    ) {
        _uiState.update {
            it.copy(
                studyMode = studyMode
            )
        }

        val current = _uiState.value

        if (
            theme == current.selectedTheme
        ) {
            return
        }

        repository.saveSelectedTheme(theme)

        _uiState.update {
            it.copy(
                selectedTheme = theme
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

    fun updateQuizStats(
        correct: Boolean
    ) {
        _uiState.update {
            it.copy(
                quizStats =
                it.quizStats.updated(correct)
            )
        }
    }
}
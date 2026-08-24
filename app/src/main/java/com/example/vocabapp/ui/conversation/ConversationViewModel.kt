package com.example.vocabapp.ui.conversation

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.example.vocabapp.data.repository.FirebaseRepository
import com.example.vocabapp.data.repository.ConversationRepository
import com.example.vocabapp.data.model.updated
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

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

    fun applySettings(
        theme: String,
        part: String,
        studyMode: String
    ) {
        _uiState.update {
            it.copy(
                studyMode = studyMode
            )
        }

        val current = _uiState.value

        if (
            theme == current.selectedTheme &&
            part == current.selectedPart
        ) {
            return
        }

        repository.saveSelectedTheme(theme)
        repository.saveSelectedPart(part)

        _uiState.update {
            it.copy(
                selectedTheme = theme,
                selectedPart = part
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
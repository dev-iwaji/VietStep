package com.example.vocabapp.ui.grammar

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.Job

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vocabapp.data.model.Grammar

import com.example.vocabapp.data.repository.FirebaseRepository
import com.example.vocabapp.data.repository.GrammarRepository
import com.example.vocabapp.data.model.updated
import com.example.vocabapp.data.source.grammarList
import com.example.vocabapp.domain.generateGrammarDeck
import android.util.Log

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

    private var deckDirty = false

    private var deckRevision = 0L

    private var uploadJob: Job? = null

    fun load() {

        loadLocalData()

        val restoredDeck =
            repository.loadDeck()

        if (restoredDeck.isNotEmpty()) {

            val savedIndex =
                repository.loadDeckIndex()
                    .coerceIn(
                        0,
                        restoredDeck.lastIndex
                    )

            _uiState.update {
                it.copy(
                    deck = restoredDeck,
                    deckIndex = savedIndex
                )
            }

            return
        }

        rebuildDeck(markDirty = false)
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

        rebuildDeck()

        // ✅ カテゴリ変更なので即時同期
        uploadDeckState()
    }

    fun uploadDeckStateIfDirty() {

        if (!deckDirty) return

        uploadDeckState()
    }

    fun rebuildDeck(
        markDirty: Boolean = true
    ) {

        val items = getFilteredGrammars()

        val previousDeck =
            _uiState.value.deck

        val newDeck =
            generateGrammarDeck(
                items = items,
                previousDeck = previousDeck
            )

        _uiState.update {
            it.copy(
                deck = newDeck,
                deckIndex = 0
            )
        }

        deckDirty = markDirty

        repository.saveDeckOrder(newDeck)
        repository.saveDeckIndex(0)
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

    fun nextCard() {

        val state = _uiState.value

        if (state.deck.isEmpty()) return

        if (state.deckIndex < state.deck.lastIndex) {

            setDeckIndex(
                state.deckIndex + 1
            )

        } else {

            // ✅ 1周終了
            rebuildDeck()

            uploadDeckStateIfDirty()
        }
    }

    private fun uploadDeckState() {

        if (uploadJob?.isActive == true) return

        val revisionAtUpload = deckRevision

        uploadJob = viewModelScope.launch {

            val result =
                repository.uploadDeckState()

            if (
                result.isSuccess &&
                deckRevision == revisionAtUpload
                ) {
                   deckDirty = false
                }
        }
    }

    private fun getFilteredGrammars(): List<Grammar> {

        return grammarList.filter {
            uiState.value.selectedTheme.isEmpty() ||
                    it.theme == uiState.value.selectedTheme
        }
    }

    private fun setDeckIndex(index: Int) {

        val deck = _uiState.value.deck
        if (deck.isEmpty()) return

        val safeIndex =
            index.coerceIn(0, deck.lastIndex)

        _uiState.update {
            it.copy(
                deckIndex = safeIndex
            )
        }

        repository.saveDeckIndex(safeIndex)

        deckDirty = true
        deckRevision++
    }
}

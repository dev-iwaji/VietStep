package com.example.vocabapp.ui.conversation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.Job

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.vocabapp.data.repository.FirebaseRepository
import com.example.vocabapp.data.repository.ConversationRepository
import com.example.vocabapp.data.model.updated
import com.example.vocabapp.data.model.Conversation
import com.example.vocabapp.data.source.conversationList
import android.util.Log

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

    private var deckDirty = false

    private var deckRevision = 0L

    private var uploadJob: Job? = null

    fun load() {

        loadLocalData()

        val deck =
            getFilteredConversations()

        if (deck.isEmpty()) {

            _uiState.update {
                it.copy(
                    deck = emptyList(),
                    deckIndex = 0
                )
            }

            return
        }

        val savedIndex =
            repository.loadDeckIndex()
                .coerceIn(
                    0,
                    deck.lastIndex
                )

        _uiState.update {
            it.copy(
                deck = deck,
                deckIndex = savedIndex
            )
        }
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

        val newDeck =
            getFilteredConversations()
                .toMutableList()

        _uiState.update {
            it.copy(
                deck = newDeck,
                deckIndex = 0
            )
        }

        deckDirty = markDirty

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

    private fun getFilteredConversations(): List<Conversation> {

        return conversationList.filter { item ->

            val themeMatches =
                uiState.value.selectedTheme.isEmpty() ||
                        item.theme == uiState.value.selectedTheme

            val partMatches =
                uiState.value.selectedPart == "全部" ||
                        item.part == uiState.value.selectedPart

            themeMatches && partMatches
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

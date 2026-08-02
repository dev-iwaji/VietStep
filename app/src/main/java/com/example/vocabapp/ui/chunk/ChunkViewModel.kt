package com.example.vocabapp.ui.chunk

import com.google.gson.Gson

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

import android.content.SharedPreferences

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.vocabapp.data.model.deckKey
import com.example.vocabapp.data.model.Chunk
import com.example.vocabapp.data.repository.FirebaseRepository
import com.example.vocabapp.data.repository.ChunkRepository
import com.example.vocabapp.data.source.baseChunks
import com.example.vocabapp.domain.generateChunkDeck
import com.example.vocabapp.domain.updateChunk
import com.example.vocabapp.util.loadChunkProgress
import com.example.vocabapp.util.saveChunkProgress
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import android.util.Log

class ChunkViewModel : ViewModel() {
init {
    Log.d("CHUNK", "ViewModel created")
}
    private lateinit var repository: ChunkRepository

    fun initialize(
        prefs: SharedPreferences,
    ) {
        Log.d("CHUNK", "Initialize")
        if (!::repository.isInitialized) {
            repository = ChunkRepository(
                prefs,
                FirebaseRepository()
            )
        }
    }

    private val _uiState =
        MutableStateFlow(
            ChunkUiState()
        )

    val uiState =
        _uiState.asStateFlow()

    fun load() {

        viewModelScope.launch {
            repository.restoreFromFirebase()

            val progressMap = loadChunkProgress(repository)

            val loadedChuncks =
                baseChunks.map { chunk ->
                    progressMap[chunk.deckKey()]
                        ?.let { progress ->
                            chunk.copy(
                                level = progress.level,
                                streak = progress.streak,
                                recentResults = progress.recentResults
                            )
                        }
                        ?: chunk
                }

            _uiState.update {

                it.copy(
                    selectedDifficulty = repository.loadFilterDifficulty(),
                    selectedCategory = repository.loadFilterCategory(),
                    weakMode = repository.loadWeakMode(),
                    deckIndex = repository.loadDeckIndex(),
                    chunks = loadedChuncks
                )
            }

            val savedDeckJson = repository.loadDeckOrder()

            if (savedDeckJson != null) {
                val savedIds: List<String> =
                    Gson().fromJson(savedDeckJson, object : TypeToken<List<String>>() {}.type)

                val restored =
                    savedIds.mapNotNull { id ->
                        loadedChuncks.find { it.deckKey() == id }
                    }

                if (restored.isNotEmpty() &&
                    restored.size == getFilteredChunks().size) {
                        _uiState.update {
                            it.copy(
                                deck = restored,
                                deckIndex = repository.loadDeckIndex()
                            )
                        }
                        setDeck(restored)
                        return@launch
                    }
                }
                rebuildDeck()
        }
}

    fun rebuildDeck() {

        val chunks = getFilteredChunks()

        _uiState.update {

            it.copy(
                deck = generateChunkDeck(chunks),
            )
        }

        saveDeckOrder()
        setDeckIndex(0)
    }

    fun setDifficulty(
        difficulty: Set<String>
    ) {
        repository.saveFilterDifficulty(difficulty)

        _uiState.update {

            it.copy(
                selectedDifficulty = difficulty
            )
        }
    }

    fun setCategory(
        category: Set<String>
    ) {
        repository.saveFilterCategory(category)

        _uiState.update {
            it.copy(
                selectedCategory = category
            )
        }

        rebuildDeck()

        repository.syncDeckToFirebase()
    }

    fun setWeakMode(
        enabled: Boolean
    ) {
        repository.saveWeakMode(enabled)

        _uiState.update {

            it.copy(
                weakMode = enabled
            )
        }

        rebuildDeck()

        repository.syncDeckToFirebase()
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

    fun getFilteredChunks(): List<Chunk> {

        return uiState.value.chunks

            // レベル
            .filter {

                uiState.value.selectedDifficulty.isEmpty() ||

                        uiState.value.selectedDifficulty.contains(
                            it.difficulty
                        )
            }

            // カテゴリ
            .filter {

                uiState.value.selectedCategory.isEmpty() ||

                        uiState.value.selectedCategory.contains(
                            it.category
                        )
            }

            // 苦手語
            .let { list ->

                if (uiState.value.weakMode) {

                    list.filter { chunk ->

                        if (chunk.recentResults.isEmpty())
                            return@filter true

                        val correct =
                            chunk.recentResults.count { it }

                        val accuracy =
                            correct.toFloat() /
                                    chunk.recentResults.size

                        accuracy < 0.6f
                    }

                } else {

                    list
                }
            }
    }

    fun answerChunk(
        chunk: Chunk,
        correct: Boolean
    ) {
        val updated = updateChunk(chunk, correct)

        saveChunkProgress(repository, updated)

        _uiState.update {
            val updatedChunks =
                it.chunks.map {
                    if (
                        it.deckKey() == chunk.deckKey()
                    )
                        updated
                    else
                        it
                }

            it.copy(
                chunks = updatedChunks,
                dirty = true
            )
        }

        nextCard()

        saveDeckOrder()
    }

    private fun nextCard() {
        val next =

            if (
                uiState.value.deckIndex <
                uiState.value.deck.lastIndex
            )
                uiState.value.deckIndex + 1
            else
                0

        setDeckIndex(next)

        val completeDeck =
            uiState.value.deckIndex == uiState.value.deck.lastIndex

        if (completeDeck && uiState.value.dirty) {
            val result = repository.syncToFirebase()

            if (!result) {
                Log.d(
                    "SYNC",
                    "retry later"
                )
            }

            _uiState.update {
                it.copy(
                    dirty = false
                )
            }
        }
    }

    private fun saveDeckOrder() {
        val deckIds = uiState.value.deck.map { it.deckKey() }

        repository.saveDeckOrder(deckIds)
    }

    private fun setDeck(
        chunk: List<Chunk>
    ) {

        _uiState.update {

            it.copy(
                deck = chunk
            )
        }
    }

    private fun setDeckIndex(
        index: Int
    ) {

        _uiState.update {

            it.copy(
                deckIndex = index
            )
        }

        repository.saveDeckIndex(index)
    }
}

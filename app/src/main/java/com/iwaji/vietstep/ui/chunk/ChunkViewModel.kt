package com.iwaji.vietstep.ui.chunk

import com.google.gson.Gson

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.Job

import android.content.SharedPreferences

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.google.gson.reflect.TypeToken

import com.iwaji.vietstep.data.model.deckKey
import com.iwaji.vietstep.data.model.Chunk
import com.iwaji.vietstep.data.model.QuizStats
import com.iwaji.vietstep.data.model.updated
import com.iwaji.vietstep.data.repository.FirebaseRepository
import com.iwaji.vietstep.data.repository.ChunkRepository
import com.iwaji.vietstep.data.source.baseChunks
import com.iwaji.vietstep.domain.generateChunkDeck
import com.iwaji.vietstep.domain.updateChunk
import com.iwaji.vietstep.util.loadChunkProgress
import com.iwaji.vietstep.util.saveChunkProgress
import android.util.Log

class ChunkViewModel : ViewModel() {
    private lateinit var repository: ChunkRepository

    fun initialize(
        prefs: SharedPreferences,
    ) {

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

    private var deckDirty = false

    private var uploadJob: Job? = null

    private var learningRevision: Long = 0L

    fun load() {
        loadLocalData()
    }

    private fun loadLocalData() {

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
                try {
                    Gson().fromJson(savedDeckJson, object : TypeToken<List<String>>() {}.type)
                } catch (e: Exception) {
                    Log.e(
                        "CHUNK_RELOAD",
                        "deckOrderの解析に失敗",
                        e
                    )
                    emptyList()
                }

            val restored =
                savedIds.mapNotNull { id ->
                    loadedChuncks.find { it.deckKey() == id }
                }

            if (restored.isNotEmpty() &&
                restored.size == savedIds.size) {
                val savedIndex =
                    repository.loadDeckIndex()
                        .coerceIn(0, restored.lastIndex)

                _uiState.update {
                    it.copy(
                        deck = restored,
                        deckIndex = savedIndex,
                    )
                }

                deckDirty = false

                return
            }
        }

        rebuildDeck(markDirty = false)
    }

    fun uploadLearningStateIfDirty() {

        if (!::repository.isInitialized) {
            return
        }

        if (!deckDirty) {
            return
        }

        if (uploadJob?.isActive == true) {
            return
        }

        val revisionAtUpload =
            learningRevision

        uploadJob =
            viewModelScope.launch {

                val result =
                    repository.uploadLearningState()

                result.onSuccess {
                    /*
                     * アップロード中に新しい回答がなければ、
                     * dirtyを解除する。
                     */
                    if (
                        learningRevision ==
                        revisionAtUpload
                    ) {
                        deckDirty = false
                    }
                }

                result.onFailure { error ->
                    /*
                     * 失敗した場合はdirtyを残す。
                     * 次にタブを離れるときなどに再試行できる。
                     */
                    Log.w(
                        "WORD_SYNC",
                        "学習状態をアップロードできませんでした",
                        error
                    )
                }
            }
    }

    fun resetQuizStats() {
        _uiState.update {
            it.copy(
                quizStats = QuizStats()
            )
        }
    }

    fun rebuildDeck(
        markDirty: Boolean = true
    ) {

        val chunks = getFilteredChunks()

        _uiState.update {

            it.copy(
                deck = generateChunkDeck(chunks),
            )
        }
        deckDirty = markDirty

        saveDeckOrder()
        setDeckIndex(0)
    }

    fun getFilteredChunks(): List<Chunk> {

        return uiState.value.chunks

            // ✅ レベル
            .filter {

                uiState.value.selectedDifficulty.isEmpty() ||

                        uiState.value.selectedDifficulty.contains(
                            it.difficulty
                        )
            }

            // ✅ カテゴリ
            .filter {

                uiState.value.selectedCategory.isEmpty() ||

                        uiState.value.selectedCategory.contains(
                            it.category
                        )
            }

            // ✅ 苦手語
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

        learningRevision++

        _uiState.update { state ->
            val updatedChunks =
                state.chunks.map { currentChunk ->
                    if (
                        currentChunk.deckKey() == chunk.deckKey()
                    )
                        updated
                    else
                        currentChunk
                }

            val updatedDeck =
                state.deck.map { deckChunk ->
                    if (deckChunk.deckKey() == chunk.deckKey()) {
                        updated
                    } else {
                        deckChunk
                    }
                }

            state.copy(
                chunks = updatedChunks,
                deck = updatedDeck,
            )
        }
        deckDirty = true

        nextCard()

        saveDeckOrder()
    }

    fun applySettings(
        category: Set<String>,
        difficulty: Set<String>,
        weakMode: Boolean,
        studyMode: String
    ) {
        _uiState.update {
            it.copy(
                studyMode = studyMode
            )
        }

        val current = _uiState.value

        if (
            category == current.selectedCategory &&
            difficulty == current.selectedDifficulty &&
            weakMode == current.weakMode
        ) {
            return
        }

        repository.saveFilterCategory(category)
        repository.saveFilterDifficulty(difficulty)
        repository.saveWeakMode(weakMode)

        _uiState.update {
            it.copy(
                selectedCategory = category,
                selectedDifficulty = difficulty,
                weakMode = weakMode
            )
        }

        // ✅ 新しい条件でデッキ再構築
        rebuildDeck()

        // ✅ 再構築後のdeckOrder / deckIndexを即時同期
        viewModelScope.launch {
            repository.uploadDeckState()
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

    private fun nextCard() {

        val state = _uiState.value

        if (state.deck.isEmpty()) return

        if (state.deckIndex < state.deck.lastIndex) {

            setDeckIndex(state.deckIndex + 1)

        } else {

            // ✅ 1周終了
            rebuildDeck()

            uploadLearningStateIfDirty()
        }
    }

    private fun saveDeckOrder() {
        val deckIds = uiState.value.deck.map { it.deckKey() }

        repository.saveDeckOrder(deckIds)
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

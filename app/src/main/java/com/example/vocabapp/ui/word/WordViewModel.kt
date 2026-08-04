package com.example.vocabapp.ui.word

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vocabapp.data.model.CsvFile
import com.example.vocabapp.data.model.Word
import com.example.vocabapp.data.model.deckKey
import com.example.vocabapp.data.source.baseWords
import com.example.vocabapp.domain.generateWordDeck
import com.example.vocabapp.domain.updateWord
import com.example.vocabapp.data.model.DailyStat
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

import com.example.vocabapp.data.repository.FirebaseRepository
import com.example.vocabapp.data.repository.WordRepository
import com.example.vocabapp.util.saveWordProgress
import com.example.vocabapp.util.loadWordProgress
import kotlinx.coroutines.launch
import android.util.Log
import kotlinx.coroutines.Job

class WordViewModel : ViewModel() {

    private lateinit var repository: WordRepository

    fun initialize(
        prefs: SharedPreferences,
    ) {
        if (!::repository.isInitialized) {
            repository = WordRepository(
                prefs,
                FirebaseRepository()
            )
        }
    }

    private val _uiState =
        MutableStateFlow(

            WordUiState()
        )

    val uiState =
        _uiState.asStateFlow()

    private var uploadJob: Job? = null

    private var learningRevision: Long = 0L

    fun load(context: Context) {
        loadLocalData(context)

        _uiState.update {
            it.copy(
                isInitialized = true
            )
        }
    }

    private fun loadLocalData(context: Context) {

        _uiState.update {
            it.copy(
                selectedPos = repository.loadFilterPos(),
                weakMode = repository.loadWeakMode(),
                deckIndex = repository.loadDeckIndex(),
                favoriteOnly = repository.loadFavoriteOnly(),
                favorites = repository.loadFavorites(),
            )
        }

        val mergedWords = rebuildWords(context)
        val savedDeckJson = repository.loadDeckOrder()

        if (!savedDeckJson.isNullOrBlank()) {
            val savedIds: List<String> =
                try {
                    Gson().fromJson(
                        savedDeckJson,
                        object : TypeToken<List<String>>() {}.type
                    )
                } catch (e: Exception) {
                    emptyList()
                }

            val restored = savedIds.mapNotNull { id ->
                mergedWords.find { it.deckKey() == id }
            }

            if (restored.isNotEmpty()) {
                setDeck(restored)

                val savedIndex = repository.loadDeckIndex()
                    .coerceIn(0, restored.lastIndex)

                setDeckIndex(savedIndex)
                return
            }
        }

        rebuildDeck()
    }

    fun uploadLearningStateIfDirty() {

        if (!::repository.isInitialized) {
            return
        }

        if (!uiState.value.dirty) {
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
                        _uiState.update {
                            it.copy(
                                dirty = false
                            )
                        }
                    }

                    Log.d(
                        "WORD_SYNC",
                        "学習状態をアップロードしました"
                    )
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

    fun rebuildDeck() {

        val words = getFilteredWords()

        _uiState.update {

            it.copy(
                deck = generateWordDeck(words),
            )
        }

        saveDeckOrder()
        setDeckIndex(0)
    }

    fun toggleFavorites(
        word: Word
    ) {

        val newFavorites =
            if (uiState.value.favorites.contains(word.deckKey())) {
                uiState.value.favorites - word.deckKey()
            } else {
                uiState.value.favorites + word.deckKey()
            }

        setFavorites(newFavorites)

        if (uiState.value.favoriteOnly) {
            rebuildDeck()
        }
    }

    fun toggleCsvFile(
        file: CsvFile,
        context: Context
    ) {
        val updated = uiState.value.csvList.map {
            if (it.name == file.name)
                it.copy(enabled = !it.enabled)
            else it
        }

        viewModelScope.launch {
            repository.updateCsvFileEnabled(
                file.name,
                !file.enabled
            )
        }

        setCsvFileList(updated, context)

        rebuildDeck()
    }

    fun jumpToWord(
            word: Word
    ) {
        val newDeck =
            listOf(word) +
            uiState.value.deck.filter {
                it != word
            }
        setDeck(newDeck)
        setDeckIndex(0)
    }

    fun addCsvFile(
        uri: String,
        fileName: String,
        context: Context
    ) {
        val newFile = CsvFile(
            name = fileName,
            uri = uri
        )

        val updated = uiState.value.csvList + newFile

        setCsvFileList(updated, context)

        rebuildDeck()

        val content =
            context.contentResolver
                .openInputStream(
                    Uri.parse(uri)
                )
                ?.bufferedReader()
                ?.readText()
                ?: return

        viewModelScope.launch {
            repository.addCsvFile(
                fileName,
                content,
                true
            )
        }
    }

    fun removeCsvFile(
        file: CsvFile,
        context: Context
    ) {
        val updated = uiState.value.csvList - file

        viewModelScope.launch {
            repository.removeCsvFile(
                file.name
            )
        }

        setCsvFileList(updated, context)

        rebuildDeck()
    }

    fun setSelectedPos(
        pos: Set<String>
    ) {

        repository.saveFilterPos(pos)

        _uiState.update {
            it.copy(
                selectedPos = pos
            )
        }

        rebuildDeck()
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

    fun getFilteredWords(): List<Word> {

        val filteredByPos = if (uiState.value.selectedPos.isEmpty()) {
            uiState.value.words
        } else {
            uiState.value.words.filter {
                uiState.value.selectedPos.contains(it.partOfSpeech)
            }
        }

        return filteredByPos

       .let { list ->

           // ✅ 苦手モード
            val weakFiltered = if (uiState.value.weakMode) {
                list.filter { w ->
                    if (w.recentResults.isEmpty()) return@filter true
                    val correct = w.recentResults.count { it }
                    val accuracy = correct.toFloat() / w.recentResults.size
                    accuracy < 0.6f
                }
            } else list

           // ✅ お気に入りモード
            if (uiState.value.favoriteOnly) {
                weakFiltered.filter {
                    uiState.value.favorites.contains(it.deckKey())
                }
            } else {
                weakFiltered
            }
        }
    }

    fun answerWord(
        word: Word,
        correct: Boolean
    ) {
        val updated = updateWord(word, correct)

        saveWordProgress(repository, updated)

        repository.addStudyResult(correct)

        learningRevision++

        _uiState.update {
            val updatedWords =
                it.words.map { currentWord ->
                    if (
                        currentWord.deckKey() == word.deckKey()
                    )
                        updated
                    else
                        currentWord
                }

            it.copy(
                words = updatedWords,
                dirty = true
            )
        }

        nextCard()

        saveDeckOrder()
    }

    fun setFavoriteOnly(
        enabled: Boolean
    ) {
        repository.saveFavoriteOnly(enabled)

        _uiState.update {

            it.copy(
                favoriteOnly = enabled
            )
        }

        rebuildDeck()
    }

    fun getTodayStat(): DailyStat? {
        return repository.getTodayStat()
    }

    fun getLast7DaysData(): List<Pair<String, Int>> {
        return repository.getLast7DaysData()
    }

    private fun rebuildWords(
        context: Context
    ): List<Word> {

        val progressMap = loadWordProgress(repository)

        val csvList = Gson().fromJson(
            repository.loadCsvFileList(),
            Array<CsvFile>::class.java
        ).toList()

        val loadedWords =
            baseWords.map { word ->
                progressMap[word.deckKey()]
                    ?.let { progress ->
                        word.copy(
                            level = progress.level,
                            streak = progress.streak,
                            recentResults = progress.recentResults
                        )
                    }
                    ?: word
            }

        val csvWords = csvList
            .filter { it.enabled }
            .flatMap { file ->
                try {
                    parseCsv(
                        context,
                        Uri.parse(file.uri)
                    )
                } catch (e: Exception ) {
                    emptyList()
                }
            }

        val mergedWords = loadedWords + csvWords

        _uiState.update {

            it.copy(

                words = mergedWords,

                csvList = csvList
            )
        }

        return mergedWords
    }

    private fun setCsvFileList(
        list: List<CsvFile>,
        context: Context
    ) {

        _uiState.update {
            it.copy(
                csvList = list
            )
        }

        repository.saveCsvFileList(list)

        rebuildWords(context)
    }

    private fun nextCard() {

        val currentIndex = uiState.value.deckIndex

        val lastIndex = uiState.value.deck.lastIndex

        if (lastIndex < 0) {
            return
        }

        /*
         * 回答したカードが最後のカードなら、
         * これでデッキを1周したことになる。
         */
        val completedRound =
            currentIndex == lastIndex

        val nextIndex =
            if (completedRound) {
                0
            } else {
                currentIndex + 1
            }

        setDeckIndex(nextIndex)

        if (completedRound) {
            uploadLearningStateIfDirty()
        }
    }
/*
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
*/
    private fun saveDeckOrder() {
        val deckIds = uiState.value.deck.map { it.deckKey() }

        repository.saveDeckOrder(deckIds)
    }

    private fun setDeck(
        word: List<Word>
    ) {

        _uiState.update {

            it.copy(
                deck = word
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

    private fun setFavorites(
        newFavorites: Set<String>
    ) {

        repository.saveFavorites(newFavorites)

        _uiState.update {
            it.copy(
                favorites = newFavorites
            )
        }
    }
}

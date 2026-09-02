package com.example.vocabapp.data.repository

import java.time.LocalDate
import java.io.File

import android.content.Context
import android.content.SharedPreferences

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import com.example.vocabapp.data.model.CsvFile
import com.example.vocabapp.data.model.DailyStat
import com.example.vocabapp.data.model.WordProgress
import com.example.vocabapp.utils.PrefKeys
import com.example.vocabapp.data.model.WordLearningState
import android.util.Log

class WordRepository(
    private val prefs: SharedPreferences,
    private val firebaseRepository: FirebaseRepository
) {

    suspend fun downloadCsvFilesFromFirebase(
        context: Context
    ) {
        val firebaseFiles = firebaseRepository.loadCsvFiles()

        val csvFiles = mutableListOf<CsvFile>()

        firebaseFiles.forEach { csv ->
            val localFile =
                File(
                    context.filesDir,
                    csv.fileName
                )

            localFile.writeText(
                csv.content
            )

            csvFiles.add(
                CsvFile(
                    name = csv.fileName,
                    uri = localFile.toURI().toString(),
                    enabled = csv.enabled
                )
            )
        }

        saveCsvFileList(csvFiles)
    }

    suspend fun uploadLearningState(): Result<Unit> {
        return runCatching {

            firebaseRepository.saveWordLearningState(
                progress = loadProgress() ?: "[]",

                deckOrder = loadDeckOrder() ?: "[]",

                deckIndex = loadDeckIndex(),

                studyHistory = loadStudyHistory() ?: "[]",
            )
        }.onFailure { error ->

            Log.w(
                "WordRepository",
                "学習状態のアップロードに失敗しました",
                error
            )
        }
    }

    suspend fun uploadDeckState(): Result<Unit> {
        return runCatching {
            firebaseRepository.saveWordDeckState(
                deckOrder = loadDeckOrder() ?: "[]",
                deckIndex = loadDeckIndex()
            )
        }
    }

    fun applyDownloadedLearningState(
        state: WordLearningState
    ) {
        prefs.edit()
            .putString(
                PrefKeys.WORD_PROGRESS,
                state.progress
            )
            .putString(
                PrefKeys.WORD_DECK_ORDER,
                state.deckOrder
            )
            .putInt(
                PrefKeys.WORD_DECK_INDEX,
                state.deckIndex
            )
            .putString(
                PrefKeys.WORD_STUDY_HISTORY,
                state.studyHistory
            )
            .putStringSet(
                PrefKeys.WORD_FILTER_POS,
                state.filterPos
            )
            .putStringSet(
                PrefKeys.WORD_FAVORITES,
                state.favorites
            )
            .putBoolean(
                PrefKeys.WORD_FAVORITE_ONLY,
                state.favoriteOnly
            )
            .putBoolean(
                PrefKeys.WORD_WEAK_MODE,
                state.weakMode
            )
            .apply()
    }

    fun applyDownloadedLearningConditions(
        state: WordLearningState
    ) {
        prefs.edit()
            .putStringSet(
                PrefKeys.WORD_FILTER_POS,
                state.filterPos
            )
            .putStringSet(
                PrefKeys.WORD_FAVORITES,
                state.favorites
            )
            .putBoolean(
                PrefKeys.WORD_FAVORITE_ONLY,
                state.favoriteOnly
            )
            .putBoolean(
                PrefKeys.WORD_WEAK_MODE,
                state.weakMode
            )
            .apply()
    }

    fun addCsvFile(
        fileName: String,
        content: String,
        enabled: Boolean
    ) {
        firebaseRepository.saveCsvFiles(fileName, content, enabled)
    }

    suspend fun removeCsvFile(
        fileName: String
    ) {
        firebaseRepository.removeCsvFile(fileName)
    }

    suspend fun updateCsvFileEnabled(
        fileName: String,
        enabled: Boolean
    ) {
        firebaseRepository.updateCsvFileEnabled(
            fileName,
            enabled
        )
    }

    fun loadProgress(): String? {
        return prefs.getString(
            PrefKeys.WORD_PROGRESS,
            "[]"
        )
    }

    fun saveProgress(
        gson: Gson, progressList: List<WordProgress>
    ) {
        prefs.edit()
            .putString(
                PrefKeys.WORD_PROGRESS,
                gson.toJson(progressList)
            )
            .apply()
    }

    fun loadDeckIndex(): Int {
        return prefs.getInt(
            PrefKeys.WORD_DECK_INDEX,
            0
        )
    }

    fun saveDeckIndex(
        index: Int
    ) {
        prefs.edit()
            .putInt(PrefKeys.WORD_DECK_INDEX, index)
            .apply()
    }

    fun loadDeckOrder(): String? {
        return prefs.getString(
            PrefKeys.WORD_DECK_ORDER,
            "[]"
        )
    }

    fun saveDeckOrder(
        deckIds: List<String>
    ) {
        prefs.edit()
            .putString(
                PrefKeys.WORD_DECK_ORDER,
                Gson().toJson(deckIds)
            )
            .apply()
    }

    fun loadFilterPos(): Set<String> {
        return prefs.getStringSet(
            PrefKeys.WORD_FILTER_POS,
            emptySet()
        )?.toSet() ?: emptySet()
    }

    fun saveFilterPos(
        pos: Set<String>
    ) {
        prefs.edit()
            .putStringSet(PrefKeys.WORD_FILTER_POS, pos)
            .apply()

        firebaseRepository.saveWordFilterPos(pos)
    }

    fun loadCsvFileList(): String? {
        return prefs.getString(
            PrefKeys.WORD_CSV_FILE_LIST,
            "[]"
        )
    }

    fun saveCsvFileList(
        list: List<CsvFile>
    ) {
        prefs.edit()
            .putString(PrefKeys.WORD_CSV_FILE_LIST, Gson().toJson(list))
            .apply()

        firebaseRepository.saveWordCsvFileList(Gson().toJson(list))
    }

    fun loadFavorites(): Set<String> {
        return prefs.getStringSet(
            PrefKeys.WORD_FAVORITES,
            emptySet()
        ) ?: emptySet()
    }

    fun saveFavorites(
        favorites: Set<String>
    ) {
        prefs.edit()
            .putStringSet(PrefKeys.WORD_FAVORITES, favorites)
            .apply()

        firebaseRepository.saveWordFavorites(favorites)
    }

    fun loadFavoriteOnly(): Boolean {
        return prefs.getBoolean(
            PrefKeys.WORD_FAVORITE_ONLY,
            false
        )
    }

    fun saveFavoriteOnly(
        enabled: Boolean
    ) {
        prefs.edit()
            .putBoolean(
                PrefKeys.WORD_FAVORITE_ONLY,
                enabled
            )
            .apply()

        firebaseRepository.saveWordFavoriteOnly(enabled)
    }

    fun loadWeakMode(): Boolean {
        return prefs.getBoolean(
            PrefKeys.WORD_WEAK_MODE,
            false
        )
    }

    fun saveWeakMode(
        enabled: Boolean
    ) {
        prefs.edit()
            .putBoolean(
                PrefKeys.WORD_WEAK_MODE,
                enabled
            )
            .apply()

        firebaseRepository.saveWordWeakMode(enabled)
    }

    fun addStudyResult(
        correct: Boolean
    ) {

        val gson = Gson()

        val json = loadStudyHistory()

        val list: MutableList<DailyStat> =
            gson.fromJson(
                json,
                object : TypeToken<MutableList<DailyStat>>() {}.type
            )

        val today =
            LocalDate.now().toString()

        val current =
            list.find {
                it.date == today
            }

        if (current == null) {

            list.add(
                DailyStat(
                    date = today,
                    correct = if (correct) 1 else 0,
                    incorrect = if (correct) 0 else 1
                )
            )

        } else {

            val index = list.indexOf(current)

            list[index] =
                current.copy(
                    correct =
                        current.correct +
                                if (correct) 1 else 0,

                    incorrect =
                        current.incorrect +
                                if (correct) 0 else 1
                )
        }

        saveStudyHistory(list)
    }

    fun getLast7DaysData(): List<Pair<String, Int>> {

        val json = loadStudyHistory()

        val history = try {

            Gson().fromJson<List<DailyStat>>(
                json,
                object : TypeToken<List<DailyStat>>() {}.type
            ) ?: emptyList()

        } catch (e: Exception) {

            emptyList()
        }

        return (6 downTo 0).map { i ->
            val date = LocalDate.now()
                .minusDays(i.toLong())

            val stat =
                history.find {
                    it.date == date.toString()
                }

            val total =
                (stat?.correct ?: 0) +
                        (stat?.incorrect ?: 0)

            date.toString().substring(5) to total
        }
    }

    fun getTodayStat(): DailyStat? {

        val json = loadStudyHistory()

        val list: List<DailyStat> =
            Gson().fromJson(
                json,
                object : TypeToken<List<DailyStat>>() {}.type
            )

        val today =
            LocalDate.now().toString()

        return list.find {
            it.date == today
        }
    }

    private fun loadStudyHistory(): String? {
        return prefs.getString(
            PrefKeys.WORD_STUDY_HISTORY,
            "[]"
        )
    }

    private fun saveStudyHistory(
        list: MutableList<DailyStat>
    ) {
        val gson = Gson()
        prefs.edit()
            .putString(
                PrefKeys.WORD_STUDY_HISTORY,
                gson.toJson(list)
            )
            .apply()
        val savedJson = prefs.getString(
            PrefKeys.WORD_STUDY_HISTORY,
            "[]"
        )
        Log.d(
            "WORD_HISTORY",
            "SharedPreferences保存確認: $savedJson"
        )
    }
}

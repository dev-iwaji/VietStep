package com.iwaji.vietstep.data.repository

import android.content.SharedPreferences

import com.google.gson.Gson

import com.iwaji.vietstep.ui.chunk.ChunkDefaults
import com.iwaji.vietstep.data.model.ChunkLearningState
import com.iwaji.vietstep.data.model.ChunkProgress
import com.iwaji.vietstep.utils.PrefKeys
import android.util.Log
import com.google.gson.reflect.TypeToken
import com.iwaji.vietstep.data.model.DailyStat
import java.time.LocalDate

class ChunkRepository(
    private val prefs: SharedPreferences,
    private val firebaseRepository: FirebaseRepository

) {

    suspend fun uploadLearningState(): Result<Unit> {
        return runCatching {

            firebaseRepository.saveChunkLearningState(
                progress = loadProgress() ?: "[]",

                deckOrder = loadDeckOrder() ?: "[]",

                deckIndex = loadDeckIndex(),

                studyHistory = loadStudyHistory() ?: "[]",
            )
        }.onFailure { error ->

            Log.w(
                "ChunkRepository",
                "学習状態のアップロードに失敗しました",
                error
            )
        }

    }

    suspend fun uploadDeckState(): Result<Unit> {
        return runCatching {
            firebaseRepository.saveChunkDeckState(
                deckOrder = loadDeckOrder() ?: "[]",
                deckIndex = loadDeckIndex()
            )
        }
    }

    fun applyDownloadedLearningState(
        state: ChunkLearningState
    ) {
        prefs.edit()
            .putString(
                PrefKeys.CHUNK_PROGRESS,
                state.progress
            )
            .putString(
                PrefKeys.CHUNK_DECK_ORDER,
                state.deckOrder
            )
            .putInt(
                PrefKeys.CHUNK_DECK_INDEX,
                state.deckIndex
            )
            .putString(
                PrefKeys.CHUNK_STUDY_HISTORY,
                state.studyHistory
            )
            .putStringSet(
                PrefKeys.CHUNK_FILTER_CATEGORY,
                state.filterCategory
            )
            .putStringSet(
                PrefKeys.CHUNK_FILTER_DIFFICULTY,
                state.filterDifficulty
            )
            .putBoolean(
                PrefKeys.CHUNK_WEAK_MODE,
                state.weakMode
            )
            .apply()
    }

    fun applyDownloadedLearningConditions(
        state: ChunkLearningState
    ) {
        prefs.edit()
            .putStringSet(
                PrefKeys.CHUNK_FILTER_CATEGORY,
                state.filterCategory
            )
            .putStringSet(
                PrefKeys.CHUNK_FILTER_DIFFICULTY,
                state.filterDifficulty
            )
            .putBoolean(
                PrefKeys.CHUNK_WEAK_MODE,
                state.weakMode
            )
            .apply()
    }

    fun loadProgress(): String? {
        return prefs.getString(
            PrefKeys.CHUNK_PROGRESS,
            "[]"
        )
    }

    fun saveProgress(
        gson: Gson, progressList: List<ChunkProgress>
    ) {
        prefs.edit()
            .putString(
                PrefKeys.CHUNK_PROGRESS,
                gson.toJson(progressList)
            )
            .apply()
    }

    fun loadDeckIndex(): Int {
        return prefs.getInt(
            PrefKeys.CHUNK_DECK_INDEX,
            0
        )
    }

    fun saveDeckIndex(
        index: Int
    ) {
        prefs.edit()
            .putInt(PrefKeys.CHUNK_DECK_INDEX, index)
            .apply()
    }

    fun loadDeckOrder(): String? {
        return prefs.getString(
            PrefKeys.CHUNK_DECK_ORDER,
            null
        )
    }

    fun saveDeckOrder(
        deckIds: List<String>
    ) {
        prefs.edit()
            .putString(
                PrefKeys.CHUNK_DECK_ORDER,
                Gson().toJson(deckIds)
            )
            .apply()
    }

    fun loadFilterCategory(): Set<String> {
        return prefs.getStringSet(
            PrefKeys.CHUNK_FILTER_CATEGORY,
            emptySet()
        )?.toSet() ?: emptySet()
    }

    fun saveFilterCategory(
        category: Set<String>
    ) {
        prefs.edit()
            .putStringSet(PrefKeys.CHUNK_FILTER_CATEGORY, category)
            .apply()

        firebaseRepository.saveChunkFilterCategory(category)
    }

    fun loadFilterDifficulty(): Set<String> {
        return prefs
            .getStringSet(PrefKeys.CHUNK_FILTER_DIFFICULTY, null)
            ?.toSet()
            ?: ChunkDefaults.DIFFICULTIES
    }

    fun saveFilterDifficulty(
        difficulty: Set<String>
    ) {
        prefs.edit()
            .putStringSet(PrefKeys.CHUNK_FILTER_DIFFICULTY, difficulty)
            .apply()

        firebaseRepository.saveChunkFilterDifficulty(difficulty)
    }

    fun loadWeakMode(): Boolean {
        return prefs.getBoolean(
            PrefKeys.CHUNK_WEAK_MODE,
            false
        )
    }

    fun saveWeakMode(
        enabled: Boolean
    ) {
        prefs.edit()
            .putBoolean(
                PrefKeys.CHUNK_WEAK_MODE,
                enabled
            )
            .apply()

        firebaseRepository.saveChunkWeakMode(enabled)
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
            PrefKeys.CHUNK_STUDY_HISTORY,
            "[]"
        )
    }

    private fun saveStudyHistory(
        list: MutableList<DailyStat>
    ) {
        val gson = Gson()
        prefs.edit()
            .putString(
                PrefKeys.CHUNK_STUDY_HISTORY,
                gson.toJson(list)
            )
            .apply()
        val savedJson = prefs.getString(
            PrefKeys.CHUNK_STUDY_HISTORY,
            "[]"
        )
    }
}

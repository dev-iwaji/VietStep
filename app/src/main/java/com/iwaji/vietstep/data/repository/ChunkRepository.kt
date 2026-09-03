package com.iwaji.vietstep.data.repository

import android.content.SharedPreferences

import com.google.gson.Gson

import com.iwaji.vietstep.ui.chunk.ChunkDefaults
import com.iwaji.vietstep.data.model.ChunkLearningState
import com.iwaji.vietstep.data.model.ChunkProgress
import com.iwaji.vietstep.utils.PrefKeys
import android.util.Log

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
//        return prefs.getStringSet(
//            PrefKeys.CHUNK_FILTER_DIFFICULTY,
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
}

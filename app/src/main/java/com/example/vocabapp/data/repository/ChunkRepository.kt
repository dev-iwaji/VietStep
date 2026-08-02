package com.example.vocabapp.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.example.vocabapp.data.model.ChunkProgress
import com.example.vocabapp.data.model.DailyStat
import com.example.vocabapp.utils.PrefKeys
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ChunkRepository(
    private val prefs: SharedPreferences,
    private val firebaseRepository: FirebaseRepository

) {

    suspend fun restoreFromFirebase() {

        val progressList = firebaseRepository.loadChunkProgress()
        prefs.edit()
            .putString(PrefKeys.CHUNK_PROGRESS, progressList)
            .apply()

        val dckIndex = firebaseRepository.loadChunkDeckIndex()
        if (dckIndex != 0) {
            prefs.edit()
                .putInt(PrefKeys.CHUNK_DECK_INDEX, dckIndex)
                .apply()
        }

        val deckOrder = firebaseRepository.loadChunkDeckOrder()
        if (deckOrder.isNotEmpty()) {
            prefs.edit()
                .putString(PrefKeys.CHUNK_DECK_ORDER, deckOrder)
                .apply()
        }

        val filterCategory = firebaseRepository.loadChunkFilterCategory()
        if (filterCategory.isNotEmpty()) {
            prefs.edit()
                .putStringSet(PrefKeys.CHUNK_FILTER_CATEGORY, filterCategory)
                .apply()
        }

        val filterDifficulty = firebaseRepository.loadChunkFilterDifficulty()
        if (filterDifficulty.isNotEmpty()) {
            prefs.edit()
                .putStringSet(PrefKeys.CHUNK_FILTER_DIFFICULTY, filterDifficulty)
                .apply()
        }

        val weakMode = firebaseRepository.loadChunkWeakMode()
        prefs.edit()
            .putBoolean(PrefKeys.CHUNK_WEAK_MODE, weakMode)
            .apply()
    }

    fun syncToFirebase(): Boolean {
        try {
            firebaseRepository.saveChunkProgress(loadProgress() ?: "[]")
            firebaseRepository.saveChunkDeckOrder(loadDeckOrder() ?: "[]")

            return true
        } catch (e: Exception) {
            Log.e(
                "ChunkRepository",
                "Firebase sync failed",
                e
            )

            return false
        }
    }

    fun syncDeckToFirebase(): Boolean {
        try {
            firebaseRepository.saveChunkDeckOrder(loadDeckOrder() ?: "[]")
            firebaseRepository.saveChunkDeckIndex(loadDeckIndex())

            return true
        } catch (e: Exception) {
            Log.e(
                "ChunkRepository",
                "Firebase sync failed",
                e
            )

            return false
        }
    }

    fun loadProgress(): String? {
        return prefs.getString(
            PrefKeys.CHUNK_PROGRESS,
            "[]"
        )
    }

    fun saveProgress(gson: Gson, progressList: List<ChunkProgress>) {
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

    fun saveDeckIndex(index: Int) {
        prefs.edit()
            .putInt(PrefKeys.CHUNK_DECK_INDEX, index)
            .apply()

        firebaseRepository.saveChunkDeckIndex(index)
    }

    fun loadDeckOrder(): String? {
        return prefs.getString(
            PrefKeys.CHUNK_DECK_ORDER,
            null
        )
    }

    fun saveDeckOrder(deckIds: List<String>) {
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

    fun saveFilterCategory(category: Set<String>) {
        prefs.edit()
            .putStringSet(PrefKeys.CHUNK_FILTER_CATEGORY, category)
            .apply()

        firebaseRepository.saveChunkFilterCategory(category)
    }

    fun loadFilterDifficulty(): Set<String> {
        return prefs.getStringSet(
            PrefKeys.CHUNK_FILTER_DIFFICULTY,
            setOf("初級", "中級", "上級")
        )?.toSet() ?: emptySet()
    }

    fun saveFilterDifficulty(difficulty: Set<String>) {
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

    fun saveWeakMode(enabled: Boolean) {
        prefs.edit()
            .putBoolean(
                PrefKeys.CHUNK_WEAK_MODE,
                enabled
            )
            .apply()

        firebaseRepository.saveChunkWeakMode(enabled)
    }
}
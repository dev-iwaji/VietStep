package com.iwaji.vietstep.data.repository

import android.content.SharedPreferences

import com.iwaji.vietstep.data.model.GrammarLearningState
import com.iwaji.vietstep.utils.PrefKeys
import com.iwaji.vietstep.data.model.Grammar
import com.google.gson.Gson

class GrammarRepository(
    private val prefs: SharedPreferences,
    private val firebaseRepository: FirebaseRepository
) {

    suspend fun uploadDeckState(): Result<Unit> {
        return runCatching {
            firebaseRepository.saveGrammarDeckState(
                deckOrder = loadDeckOrder() ?: "[]",
                deckIndex = loadDeckIndex()
            )
        }
    }

    fun applyDownloadedLearningState(
        state: GrammarLearningState
    ) {
        prefs.edit()
            .putString(
                PrefKeys.GRAMMAR_THEME,
                state.theme
            )
            .putString(
                PrefKeys.GRAMMAR_DECK_ORDER,
                state.deckOrder
            )
            .putInt(
                PrefKeys.GRAMMAR_DECK_INDEX,
                state.deckIndex
            )
            .apply()
    }

    fun loadDeckIndex(): Int {
        return prefs.getInt(
            PrefKeys.GRAMMAR_DECK_INDEX,
            0
        )
    }

    fun saveDeckIndex(
        index: Int
    ) {
        prefs.edit()
            .putInt(PrefKeys.GRAMMAR_DECK_INDEX, index)
            .apply()
    }

    fun loadDeckOrder(): String? {
        return prefs.getString(
            PrefKeys.GRAMMAR_DECK_ORDER,
            null
        )
    }

    fun loadDeck(): List<Grammar> {

        val json =
            loadDeckOrder()
                ?: return emptyList()

        return try {
            Gson().fromJson(
                json,
                Array<Grammar>::class.java
            ).toList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun saveDeckOrder(
        deck: List<Grammar>
    ) {
        prefs.edit()
            .putString(
                PrefKeys.GRAMMAR_DECK_ORDER,
                Gson().toJson(deck)
            )
            .apply()
    }

    fun loadSelectedTheme(): String {
        return prefs.getString(
            PrefKeys.GRAMMAR_THEME,
            "基本形"
        ) ?: "基本形"
    }

    fun saveSelectedTheme(
        theme: String
    ) {
        prefs.edit()
            .putString(
                PrefKeys.GRAMMAR_THEME,
                theme
            )
            .apply()

        firebaseRepository.saveGrammarTheme(theme)
    }

    fun loadSpeechRate(): Float {
        return prefs.getFloat(
            PrefKeys.GRAMMAR_SPEECH_RATE,
            0.8f
        )
    }

    fun saveSpeechRate(speed: Float) {
        prefs.edit()
            .putFloat(
                PrefKeys.GRAMMAR_SPEECH_RATE,
                speed
            )
            .apply()
    }
}

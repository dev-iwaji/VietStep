package com.example.vocabapp.data.repository

import android.content.SharedPreferences
import com.example.vocabapp.utils.PrefKeys
import com.google.gson.Gson

class GrammarRepository(
    private val prefs: SharedPreferences,
    private val firebaseRepository: FirebaseRepository
) {

    suspend fun restoreFromFirebase() {

        val theme = firebaseRepository.loadGrammarTheme()
        if (theme.isNotEmpty()) {
            prefs.edit()
                .putString(PrefKeys.GRAMMAR_THEME, theme)
                .apply()
        }

        val speechRate = firebaseRepository.loadGrammarSpeechRate()
        prefs.edit()
            .putFloat(PrefKeys.GRAMMAR_SPEECH_RATE, speechRate)
            .apply()
    }

    fun loadSelectedTheme(): String {
        return prefs.getString(
            PrefKeys.GRAMMAR_THEME,
            "基本形"
        ) ?: "基本形"
    }

    fun saveSelectedTheme(theme: String) {
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

        firebaseRepository.saveGrammarSpeechRate(speed)
    }
}
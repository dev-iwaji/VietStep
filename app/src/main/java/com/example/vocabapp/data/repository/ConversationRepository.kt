package com.example.vocabapp.data.repository

import android.content.SharedPreferences

import com.example.vocabapp.utils.PrefKeys
import com.example.vocabapp.data.model.ConversationLearningState
import android.util.Log

class ConversationRepository(
    private val prefs: SharedPreferences,
    private val firebaseRepository: FirebaseRepository
) {

    suspend fun uploadDeckState(): Result<Unit> {
        return runCatching {
            firebaseRepository.saveConversationDeckState(
                deckIndex = loadDeckIndex()
            )
        }
    }

    fun applyDownloadedLearningState(
        state: ConversationLearningState
    ) {
        prefs.edit()
            .putString(
                PrefKeys.CONVERSATION_THEME,
                state.theme
            )
            .putString(
                PrefKeys.CONVERSATION_PART,
                state.part
            )
            .putInt(
                PrefKeys.CONVERSATION_DECK_INDEX,
                state.deckIndex
            )
            .apply()
    }

    fun loadDeckIndex(): Int {
        return prefs.getInt(
            PrefKeys.CONVERSATION_DECK_INDEX,
            0
        )
    }

    fun saveDeckIndex(
        index: Int
    ) {
        prefs.edit()
            .putInt(PrefKeys.CONVERSATION_DECK_INDEX, index)
            .apply()
    }

    fun loadSelectedTheme(): String {
        return prefs.getString(
            PrefKeys.CONVERSATION_THEME,
            "一般"
        ) ?: "一般"
    }

    fun saveSelectedTheme(
        theme: String
    ) {
        prefs.edit()
            .putString(
                PrefKeys.CONVERSATION_THEME,
                theme
            )
            .apply()

        firebaseRepository.saveConversationTheme(theme)
    }

    fun loadSelectedPart(): String {
        return prefs.getString(
            PrefKeys.CONVERSATION_PART,
            "全部"
        ) ?: "全部"
    }

    fun saveSelectedPart(
        part: String
    ) {
        prefs.edit()
            .putString(
                PrefKeys.CONVERSATION_PART,
                part
            )
            .apply()

        firebaseRepository.saveConversationPart(part)
    }

    fun loadSpeechRate(): Float {
        return prefs.getFloat(
            PrefKeys.CONVERSATION_SPEECH_RATE,
            0.8f
        )
    }

    fun saveSpeechRate(
        speed: Float
    ) {
        prefs.edit()
            .putFloat(
                PrefKeys.CONVERSATION_SPEECH_RATE,
                speed
            )
            .apply()
    }
}

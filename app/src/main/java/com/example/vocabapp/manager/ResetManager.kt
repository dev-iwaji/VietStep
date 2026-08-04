package com.example.vocabapp.manager

import android.content.SharedPreferences
import com.example.vocabapp.data.repository.FirebaseRepository
import com.example.vocabapp.utils.PrefKeys

object ResetManager {

    suspend fun resetAll(
        prefs: SharedPreferences,
        firebaseRepository: FirebaseRepository
    ) {
        firebaseRepository.resetWord()
        firebaseRepository.resetChunk()
        firebaseRepository.resetGrammar()
        firebaseRepository.resetConversation()

        clearLocalData(prefs)
    }

    fun resetLocalOnly(
        prefs: SharedPreferences
    ) {
        clearLocalData(prefs)
    }

    private fun clearLocalData(
        prefs: SharedPreferences
    ) {
        prefs.edit()
            .remove(PrefKeys.WORD_PROGRESS)
            .remove(PrefKeys.WORD_DECK_INDEX)
            .remove(PrefKeys.WORD_DECK_ORDER)
            .remove(PrefKeys.WORD_FILTER_POS)
            .remove(PrefKeys.WORD_FAVORITES)
            .remove(PrefKeys.WORD_FAVORITE_ONLY)
            .remove(PrefKeys.WORD_WEAK_MODE)
            .remove(PrefKeys.WORD_STUDY_HISTORY)

            .remove(PrefKeys.CHUNK_PROGRESS)
            .remove(PrefKeys.CHUNK_DECK_INDEX)
            .remove(PrefKeys.CHUNK_DECK_ORDER)
            .remove(PrefKeys.CHUNK_FILTER_CATEGORY)
            .remove(PrefKeys.CHUNK_FILTER_DIFFICULTY)
            .remove(PrefKeys.CHUNK_WEAK_MODE)

            // 必要な文法・会話設定
            .commit()
    }
}
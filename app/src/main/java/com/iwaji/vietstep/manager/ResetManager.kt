package com.iwaji.vietstep.manager

import android.content.SharedPreferences
import com.iwaji.vietstep.data.repository.FirebaseRepository
import com.iwaji.vietstep.utils.PrefKeys

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
            .remove(PrefKeys.WORD_STUDY_HISTORY)

            .remove(PrefKeys.CHUNK_PROGRESS)
            .remove(PrefKeys.CHUNK_DECK_INDEX)
            .remove(PrefKeys.CHUNK_DECK_ORDER)

            .remove(PrefKeys.GRAMMAR_DECK_ORDER)
            .remove(PrefKeys.GRAMMAR_DECK_ORDER)

            .remove(PrefKeys.CONVERSATION_DECK_INDEX)

            .commit()
    }
}
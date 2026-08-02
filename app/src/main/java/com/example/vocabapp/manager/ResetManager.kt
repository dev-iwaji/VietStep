package com.example.vocabapp.manager

import android.content.SharedPreferences
import com.example.vocabapp.data.repository.FirebaseRepository

object ResetManager {
    fun resetAll(
        prefs: SharedPreferences,
        firebaseRepository: FirebaseRepository
    ) {
        prefs.edit()
            .clear()
            .apply()

        firebaseRepository.resetWord()
        firebaseRepository.resetChunk()
        firebaseRepository.resetGrammar()
        firebaseRepository.resetConversation()
    }
}
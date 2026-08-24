package com.example.vocabapp.data.repository

import android.content.Context
import android.util.Log

class SyncRepository (
    private val firebaseRepository: FirebaseRepository,
    private val wordRepository: WordRepository,
    private val chunkRepository: ChunkRepository,
    private val grammarRepository: GrammarRepository,
    private val conversationRepository: ConversationRepository
) {
    suspend fun downloadAllLearningState(context: Context): Result<Unit> {
        return runCatching {
            val wordState = firebaseRepository.loadWordLearningState()

            val chunkState = firebaseRepository.loadChunkLearningState()

            val grammarState = firebaseRepository.loadGrammarLearningState()

            val conversationState = firebaseRepository.loadConversationLearningState()

            wordRepository.applyDownloadedLearningState(wordState)

            wordRepository.downloadCsvFilesFromFirebase(context)

            chunkRepository.applyDownloadedLearningState(chunkState)

            grammarRepository.applyDownloadedLearningState(grammarState)

            conversationRepository.applyDownloadedLearningState(conversationState)
        }
    }

    suspend fun restoreLearningConditionsAfterReset() {
        wordRepository.applyDownloadedLearningConditions(
            firebaseRepository.loadWordLearningState()
        )

        chunkRepository.applyDownloadedLearningConditions(
            firebaseRepository.loadChunkLearningState()
        )
    }
}
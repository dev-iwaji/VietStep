package com.iwaji.vietstep.data.repository

import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException

import com.iwaji.vietstep.data.model.FirebaseCsv
import com.iwaji.vietstep.ui.chunk.ChunkDefaults
import com.iwaji.vietstep.data.model.ChunkLearningState
import com.iwaji.vietstep.data.model.WordLearningState
import com.iwaji.vietstep.data.model.ConversationLearningState
import com.iwaji.vietstep.data.model.GrammarLearningState
import android.util.Log

class FirebaseRepository {
    private val db =
        FirebaseFirestore.getInstance()

    private val auth =
        FirebaseAuth.getInstance()

    private val _authState = MutableStateFlow(auth.currentUser)

    val authState = _authState.asStateFlow()

    private val listener = FirebaseAuth.AuthStateListener { firebaseAuth ->
        _authState.value = firebaseAuth.currentUser
    }

    private companion object {

        val USER_COLLECTIONS =
            listOf(
                "word",
                "chunk",
                "grammar",
                "conversation",
                "csv",
                "sync"
            )
    }

    init {
        auth.addAuthStateListener(listener)
    }

    fun getUid(): String? {
        return auth.currentUser?.uid
    }

    fun logout() {
        FirebaseAuth
            .getInstance()
            .signOut()
    }

    suspend fun deleteAccount() {

        val user = auth.currentUser
            ?: throw IllegalStateException("ログインしていません")

        val uid = user.uid

        val userDoc =
            db.collection("users")
                .document(uid)

        USER_COLLECTIONS.forEach {
            // ✅ サブコレクション内の全ドキュメントを削除
            deleteCollection(uid, it)
        }

        // ✅ users/{uid} を削除
        userDoc.delete().await()

        try {
            // ✅ Firebase Authentication のユーザー削除
            user.delete().await()
        }
         catch (e: FirebaseAuthRecentLoginRequiredException) {
            // ✅ UI側へ通知する
            throw e
        }
    }

    private suspend fun deleteCollection(
        uid: String,
        name: String
    ) {

        val snapshot =
            db.collection("users")
                .document(uid)
                .collection(name)
                .get()
                .await()

        val batch = db.batch()

        snapshot.documents.forEach {

            batch.delete(it.reference)

        }

        batch.commit().await()
    }

    suspend fun saveWordLearningState(
        progress: String,
        deckOrder: String,
        deckIndex: Int,
        studyHistory: String
    ) {
        val uid = getUid()
            ?: throw IllegalStateException(
                "Firebaseにログインしていません"
            )

        val wordCollection =
            db.collection("users")
                .document(uid)
                .collection("word")

            db.runBatch { batch ->

                batch.set(
                    wordCollection.document("progress"),
                    mapOf(
                        "progress" to progress
                    )
                )

            batch.set(
                wordCollection.document("deckOrder"),
                mapOf(
                    "deckOrder" to deckOrder
                )
            )

            batch.set(
                wordCollection.document("deckIndex"),
                mapOf(
                    "deckIndex" to deckIndex
                )
            )

            batch.set(
                wordCollection.document("studyHistory"),
                mapOf(
                    "studyHistory" to studyHistory
                )
            )

        }.await()
    }

    suspend fun saveWordDeckState(
        deckOrder: String,
        deckIndex: Int
    ) {
        val uid = getUid()
            ?: throw IllegalStateException(
                "Firebaseにログインしていません"
            )

        val wordCollection =
            db.collection("users")
                .document(uid)
                .collection("word")

        db.runBatch { batch ->

            batch.set(
                wordCollection.document("deckOrder"),
                mapOf(
                    "deckOrder" to deckOrder
                )
            )

            batch.set(
                wordCollection.document("deckIndex"),
                mapOf(
                    "deckIndex" to deckIndex
                )
            )

        }.await()
    }

    suspend fun loadWordLearningState(): WordLearningState {
        val uid = getUid()
            ?: throw IllegalStateException(
                "Firebaseにログインしていません"
            )

        val wordCollection =
            db.collection("users")
                .document(uid)
                .collection("word")

        val progressSnapshot =
            wordCollection
                .document("progress")
                .get()
                .await()

        val deckOrderSnapshot =
            wordCollection
                .document("deckOrder")
                .get()
                .await()

        val deckIndexSnapshot =
            wordCollection
                .document("deckIndex")
                .get()
                .await()

        val studyHistorySnapshot =
            wordCollection
                .document("studyHistory")
                .get()
                .await()

        val filterPosSnapshot =
            wordCollection
                .document("filterPos")
                .get()
                .await()

        val favoritesSnapshot =
            wordCollection
                .document("favorites")
                .get()
                .await()

        val favoriteOnlySnapshot =
            wordCollection
                .document("favoriteOnly")
                .get()
                .await()

        val weakModeSnapshot =
            wordCollection
                .document("weakMode")
                .get()
                .await()

        return WordLearningState(
            progress =
                if (progressSnapshot.exists()) {
                    progressSnapshot
                        .getString("progress")
                        ?: "[]"
                } else {
                    "[]"
                },

            deckOrder =
                if (deckOrderSnapshot.exists()) {
                    deckOrderSnapshot
                        .getString("deckOrder")
                        ?: "[]"
                } else {
                    "[]"
                },

            deckIndex =
                if (deckIndexSnapshot.exists()) {
                    deckIndexSnapshot
                        .getLong("deckIndex")
                        ?.toInt()
                        ?: 0
                } else {
                    0
                },

            studyHistory =
                if (studyHistorySnapshot.exists()) {
                    studyHistorySnapshot
                        .getString("studyHistory")
                        ?: "[]"
                } else {
                    "[]"
                },

            filterPos =
                if (filterPosSnapshot.exists()) {
                    filterPosSnapshot
                        .get("filterPos")
                            as? List<String> ?: emptyList()
                } else {
                    emptyList()
                }.toSet(),

            favorites =
                if (favoritesSnapshot.exists()) {
                    favoritesSnapshot
                        .get("favorites")
                            as? List<String>  ?: emptySet()
                } else {
                    emptyList()
                }.toSet(),

            favoriteOnly =
                if (favoriteOnlySnapshot.exists()) {
                    favoriteOnlySnapshot
                        .getBoolean("favoriteOnly") ?: false
                } else {
                    false
                },

            weakMode =
                if (weakModeSnapshot.exists()) {
                    weakModeSnapshot
                        .getBoolean("weakMode") ?: false
                } else {
                    false
                },
        )
    }

    fun saveCsvFiles(
        fileName: String,
        content: String,
        enabled: Boolean
    ) {
        val uid = getUid() ?: return

        db.collection("users")
            .document(uid)
            .collection("csv")
            .document(fileName)
            .set(
                mapOf(
                    "fileName" to fileName,
                    "content" to content,
                    "enabled" to enabled
                )
            )
    }

    suspend fun loadCsvFiles(): List<FirebaseCsv> {
        val uid = getUid() ?: return emptyList()

        val snapshot =
            db.collection("users")
                .document(uid)
                .collection("csv")
                .get()
                .await()

        return snapshot.documents.mapNotNull {
            val fileName =
                it.getString("fileName")
            val content =
                it.getString("content")
            val enabled =
                it.getBoolean("enabled") ?: true
            if (
                fileName != null &&
                content != null
            ) {
                FirebaseCsv(
                    fileName = fileName,
                    content = content,
                    enabled = enabled
                )
            } else {
                null
            }
        }
    }

    suspend fun removeCsvFile(
        fileName: String
    ) {
        val uid = getUid() ?: return

        db.collection("users")
            .document(uid)
            .collection("csv")
            .document(fileName)
            .delete()
            .await()
    }

    suspend fun updateCsvFileEnabled(
        fileName: String,
        enabled: Boolean
    ) {
        val uid = getUid() ?: return

        db.collection("users")
            .document(uid)
            .collection("csv")
            .document(fileName)
            .update(
                "enabled", enabled
            )
            .await()
    }

    fun saveWordFilterPos(
        filterPos: Set<String>
    ) {
        val uid = getUid() ?: return

        db.collection("users")
            .document(uid)
            .collection("word")
            .document("filterPos")
            .set(
                mapOf(
                    "filterPos" to filterPos.toList()
                )
            )
    }

    fun saveWordCsvFileList(
        csvFileList: String
    ) {
        val uid = getUid() ?: return

        db.collection("users")
            .document(uid)
            .collection("word")
            .document("csvFileList")
            .set(
                mapOf(
                    "csvFileList" to csvFileList.toString()
                )
            )
    }

    fun saveWordFavorites(
        favorites: Set<String>
    ) {
        val uid = getUid() ?: return

        db.collection("users")
            .document(uid)
            .collection("word")
            .document("favorites")
            .set(
                mapOf(
                    "favorites" to favorites.toList()
                )
            )
    }

    fun saveWordFavoriteOnly(
        enabled: Boolean
    ) {
        val uid = getUid() ?: return

        db.collection("users")
            .document(uid)
            .collection("word")
            .document("favoriteOnly")
            .set(
                mapOf(
                    "favoriteOnly" to enabled
                )
            )
    }

    fun saveWordWeakMode(
        enabled: Boolean
    ) {
        val uid = getUid() ?: return

        db.collection("users")
            .document(uid)
            .collection("word")
            .document("weakMode")
            .set(
                mapOf(
                    "weakMode" to enabled
                )
            )
    }

    suspend fun saveChunkLearningState(
        progress: String,
        deckOrder: String,
        deckIndex: Int
    ) {
        val uid = getUid()
            ?: throw IllegalStateException(
                "Firebaseにログインしていません"
            )

        val chunkCollection =
            db.collection("users")
                .document(uid)
                .collection("chunk")

        db.runBatch { batch ->

            batch.set(
                chunkCollection.document("progress"),
                mapOf(
                    "progress" to progress
                )
            )

            batch.set(
                chunkCollection.document("deckOrder"),
                mapOf(
                    "deckOrder" to deckOrder
                )
            )

            batch.set(
                chunkCollection.document("deckIndex"),
                mapOf(
                    "deckIndex" to deckIndex
                )
            )

        }.await()
    }

    suspend fun saveChunkDeckState(
        deckOrder: String,
        deckIndex: Int
    ) {
        val uid = getUid()
            ?: throw IllegalStateException(
                "Firebaseにログインしていません"
            )

        val wordCollection =
            db.collection("users")
                .document(uid)
                .collection("chunk")

        db.runBatch { batch ->

            batch.set(
                wordCollection.document("deckOrder"),
                mapOf(
                    "deckOrder" to deckOrder
                )
            )

            batch.set(
                wordCollection.document("deckIndex"),
                mapOf(
                    "deckIndex" to deckIndex
                )
            )

        }.await()
    }

    suspend fun loadChunkLearningState(): ChunkLearningState {
        val uid = getUid()
            ?: throw IllegalStateException(
                "Firebaseにログインしていません"
            )

        val chunkCollection =
            db.collection("users")
                .document(uid)
                .collection("chunk")

        val progressSnapshot =
            chunkCollection
                .document("progress")
                .get()
                .await()

        val deckOrderSnapshot =
            chunkCollection
                .document("deckOrder")
                .get()
                .await()

        val deckIndexSnapshot =
            chunkCollection
                .document("deckIndex")
                .get()
                .await()

        val filterDifficultySnapshot =
            chunkCollection
                .document("filterDifficulty")
                .get()
                .await()

        val filterCategorySnapshot =
            chunkCollection
                .document("filterCategory")
                .get()
                .await()

        val weakModeSnapshot =
            chunkCollection
                .document("weakMode")
                .get()
                .await()

        return ChunkLearningState(
            progress =
                if (progressSnapshot.exists()) {
                    progressSnapshot
                        .getString("progress")
                        ?: "[]"
                } else {
                    "[]"
                },

            deckOrder =
                if (deckOrderSnapshot.exists()) {
                    deckOrderSnapshot
                        .getString("deckOrder")
                        ?: "[]"
                } else {
                    "[]"
                },

            deckIndex =
                if (deckIndexSnapshot.exists()) {
                    deckIndexSnapshot
                        .getLong("deckIndex")
                        ?.toInt()
                        ?: 0
                } else {
                    0
                },

            filterDifficulty =
                if (filterDifficultySnapshot.exists()) {
                    (filterDifficultySnapshot
                        .get("filterDifficulty") as? List<*>)
                    ?.filterIsInstance<String>()
                        ?.toSet()
                        ?: ChunkDefaults.DIFFICULTIES
                } else {
                    ChunkDefaults.DIFFICULTIES
                },

            filterCategory =
                if (filterCategorySnapshot.exists()) {
                    filterCategorySnapshot
                        .get("filterCategory")
                            as? List<String>  ?: emptySet()
                } else {
                    emptyList()
                }.toSet(),

            weakMode =
                if (weakModeSnapshot.exists()) {
                    weakModeSnapshot
                        .getBoolean("weakMode") ?: false
                } else {
                    false
                },
        )
    }

    fun saveChunkFilterCategory(
        filterCategory: Set<String>
    ) {
        val uid = getUid() ?: return

        db.collection("users")
            .document(uid)
            .collection("chunk")
            .document("filterCategory")
            .set(
                mapOf(
                    "filterCategory" to filterCategory.toList()
                )
            )
    }

    fun saveChunkFilterDifficulty(
        filterDifficulty: Set<String>
    ) {
        val uid = getUid() ?: return

        db.collection("users")
            .document(uid)
            .collection("chunk")
            .document("filterDifficulty")
            .set(
                mapOf(
                    "filterDifficulty" to filterDifficulty.toList()
                )
            )
    }

    fun saveChunkWeakMode(
        enabled: Boolean
    ) {
        val uid = getUid() ?: return

        db.collection("users")
            .document(uid)
            .collection("chunk")
            .document("weakMode")
            .set(
                mapOf(
                    "weakMode" to enabled
                )
            )
    }

    suspend fun saveGrammarDeckState(
        deckOrder: String,
        deckIndex: Int
    ) {
        val uid = getUid()
            ?: throw IllegalStateException(
                "Firebaseにログインしていません"
            )

        val wordCollection =
            db.collection("users")
                .document(uid)
                .collection("grammar")

        db.runBatch { batch ->

            batch.set(
                wordCollection.document("deckOrder"),
                mapOf(
                    "deckOrder" to deckOrder
                )
            )

            batch.set(
                wordCollection.document("deckIndex"),
                mapOf(
                    "deckIndex" to deckIndex
                )
            )

        }.await()
    }

    suspend fun loadGrammarLearningState(): GrammarLearningState {
        val uid = getUid()
            ?: throw IllegalStateException(
                "Firebaseにログインしていません"
            )

        val grammarCollection =
            db.collection("users")
                .document(uid)
                .collection("grammar")

        val themeSnapshot =
            grammarCollection
                .document("theme")
                .get()
                .await()

        val deckOrderSnapshot =
            grammarCollection
                .document("deckOrder")
                .get()
                .await()

        val deckIndexSnapshot =
            grammarCollection
                .document("deckIndex")
                .get()
                .await()

        return GrammarLearningState(
            theme =
                if (themeSnapshot.exists()) {
                    themeSnapshot
                        .getString("theme")
                        ?: "基本形"
                } else {
                    "基本形"
                },

            deckOrder =
            if (deckOrderSnapshot.exists()) {
                deckOrderSnapshot
                    .getString("deckOrder")
                    ?: "[]"
            } else {
                "[]"
            },

            deckIndex =
            if (deckIndexSnapshot.exists()) {
                deckIndexSnapshot
                    .getLong("deckIndex")
                    ?.toInt()
                    ?: 0
            } else {
                0
            },
        )
    }

    fun saveGrammarTheme(
        theme: String
    ) {
        val uid = getUid() ?: return

        db.collection("users")
            .document(uid)
            .collection("grammar")
            .document("theme")
            .set(
                mapOf(
                    "theme" to theme
                )
            )
    }

    suspend fun saveConversationDeckState(
        deckIndex: Int
    ) {
        val uid = getUid()
            ?: throw IllegalStateException(
                "Firebaseにログインしていません"
            )

        val wordCollection =
            db.collection("users")
                .document(uid)
                .collection("conversation")

        db.runBatch { batch ->

            batch.set(
                wordCollection.document("deckIndex"),
                mapOf(
                    "deckIndex" to deckIndex
                )
            )

        }.await()
    }

    suspend fun loadConversationLearningState(): ConversationLearningState {
        val uid = getUid()
            ?: throw IllegalStateException(
                "Firebaseにログインしていません"
            )

        val conversationCollection =
            db.collection("users")
                .document(uid)
                .collection("conversation")

        val themeSnapshot =
            conversationCollection
                .document("theme")
                .get()
                .await()

        val partSnapshot =
            conversationCollection
                .document("part")
                .get()
                .await()

        val deckIndexSnapshot =
            conversationCollection
                .document("deckIndex")
                .get()
                .await()

        return ConversationLearningState(
            theme =
            if (themeSnapshot.exists()) {
                themeSnapshot
                    .getString("theme")
                    ?: "一般"
            } else {
                "一般"
            },

            part =
            if (partSnapshot.exists()) {
                partSnapshot
                    .getString("part")
                    ?: "全部"
            } else {
                "全部"
            },

            deckIndex =
            if (deckIndexSnapshot.exists()) {
                deckIndexSnapshot
                    .getLong("deckIndex")
                    ?.toInt()
                    ?: 0
            } else {
                0
            },
        )
    }

    fun saveConversationTheme(
        theme: String
    ) {
        val uid = getUid() ?: return

        db.collection("users")
            .document(uid)
            .collection("conversation")
            .document("theme")
            .set(
                mapOf(
                    "theme" to theme
                )
            )
    }

    fun saveConversationPart(
        part: String
    ) {
        val uid = getUid() ?: return

        db.collection("users")
            .document(uid)
            .collection("conversation")
            .document("part")
            .set(
                mapOf(
                    "part" to part
                )
            )
    }

    fun saveResetAT(
        time: Long
    ) {
        val uid =getUid() ?: return
        db.collection("users")
            .document(uid)
            .collection("sync")
            .document("reset")
            .set(
                mapOf(
                    "resetAT" to time
                )
            )
    }

    suspend fun loadResetAT(): Long? {
        val uid = getUid() ?: return null

        return try {
            val snapshot =
                db.collection("users")
                    .document(uid)
                    .collection("sync")
                    .document("reset")
                    .get()
                    .await()

            if (!snapshot.exists()) {
                null
            } else {
                snapshot.getLong("resetAT")
            }
        } catch (e: Exception) {
            Log.w(
                "RESET_CHECK",
                "FirebaseのresetATを取得できませんでした",
                e
            )
            null
        }
    }

    suspend fun resetWord() {
        val uid = getUid() ?: return

        val wordCollection =
            db.collection("users")
                .document(uid)
                .collection("word")

        db.runBatch { batch ->
            batch.delete(
                wordCollection.document("progress")
            )

            batch.delete(
                wordCollection.document("deckIndex")
            )

            batch.delete(
                wordCollection.document("deckOrder")
            )

            batch.delete(
                wordCollection.document("studyHistory")
            )
        }.await()
    }

    suspend fun resetChunk() {
        val uid = getUid() ?: return

        val chunkCollection =
            db.collection("users")
                .document(uid)
                .collection("chunk")

        db.runBatch { batch ->
            batch.delete(
                chunkCollection.document("progress")
            )

            batch.delete(
                chunkCollection.document("deckIndex")
            )

            batch.delete(
                chunkCollection.document("deckOrder")
            )
        }.await()
    }

    suspend fun resetGrammar() {
        val uid = getUid() ?: return

        val chunkCollection =
            db.collection("users")
                .document(uid)
                .collection("grammar")

        db.runBatch { batch ->
            batch.delete(
                chunkCollection.document("deckIndex")
            )

            batch.delete(
                chunkCollection.document("deckOrder")
            )
        }.await()
    }

    suspend fun resetConversation() {
        val uid = getUid() ?: return

        val chunkCollection =
            db.collection("users")
                .document(uid)
                .collection("conversation")

        db.runBatch { batch ->
            batch.delete(
                chunkCollection.document("deckIndex")
            )
        }.await()
    }
}

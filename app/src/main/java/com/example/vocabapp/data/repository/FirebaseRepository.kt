package com.example.vocabapp.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import com.example.vocabapp.data.model.FirebaseCsv
import com.example.vocabapp.data.model.ChunkLearningState
import com.example.vocabapp.data.model.WordLearningState
import com.example.vocabapp.data.model.ConversationLearningState
import com.example.vocabapp.data.model.GrammarLearningState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    fun saveMainSoundVolume(
        soundVolume: Float
    ) {
        val uid = getUid() ?: return

        db.collection("users")
            .document(uid)
            .collection("main")
            .document("soundVolume")
            .set(
                mapOf(
                    "soundVolume" to soundVolume
                )
            )
    }

    suspend fun loadMainSoundVolume(): Float {
        val uid = getUid() ?: return 0.5f

        val snapshot =
            db.collection("users")
                .document(uid)
                .collection("main")
                .document("soundVolume")
                .get()
                .await()

        val volume = snapshot.getDouble("soundVolume") ?: 0.5f
        return volume.toFloat()
    }

    fun saveMainDarkMode(
        enabled: Boolean
    ) {
        val uid = getUid() ?: return

        db.collection("users")
            .document(uid)
            .collection("main")
            .document("darkMode")
            .set(
                mapOf(
                    "darkMode" to enabled
                )
            )
    }

    suspend fun loadMainDarkMode(): Boolean {
        val uid = getUid() ?: return false

        val snapshot =
            db.collection("users")
                .document(uid)
                .collection("main")
                .document("darkMode")
                .get()
                .await()

        return snapshot.getBoolean("darkMode") ?: false
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
                    filterDifficultySnapshot
                        .get("filterDifficulty")
                            as? List<String>  ?: emptySet()
                } else {
                    emptyList()
                }.toSet(),

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

        return GrammarLearningState(
            theme =
                if (themeSnapshot.exists()) {
                    themeSnapshot
                        .getString("theme")
                        ?: "基本形"
                } else {
                    "基本形"
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

    fun saveResetAT(time: Long) {
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

            batch.delete(
                wordCollection.document("favorites")
            )

            batch.delete(
                wordCollection.document("favoriteOnly")
            )

            batch.delete(
                wordCollection.document("weakMode")
            )

            batch.delete(
                wordCollection.document("filterPos")
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

            batch.delete(
                chunkCollection.document("weakMode")
            )

            batch.delete(
                chunkCollection.document("filterCategory")
            )

            batch.delete(
                chunkCollection.document("filterDifficulty")
            )
        }.await()
    }

    suspend fun resetGrammar() {
        val uid = getUid() ?: return

        val grammarCollection =
            db.collection("users")
                .document(uid)
                .collection("grammar")

        db.runBatch { batch ->
            batch.delete(
                grammarCollection.document("theme")
            )
        }.await()
    }

    suspend fun resetConversation() {
        val uid = getUid() ?: return

        val conversationCollection =
            db.collection("users")
                .document(uid)
                .collection("conversation")

        db.runBatch { batch ->
            batch.delete(
                conversationCollection.document("theme")
            )

            batch.delete(
                conversationCollection.document("part")
            )
        }.await()
    }
}

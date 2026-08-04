package com.example.vocabapp.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import com.example.vocabapp.data.model.FirebaseCsv
import com.example.vocabapp.data.model.ChunkLeaningState
import com.example.vocabapp.data.model.WordLearningState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import android.util.Log
import com.example.vocabapp.data.model.ConversationLeaningState
import com.example.vocabapp.data.model.GrammarLeaningState

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

            filterPos = loadWordFilterPos(),

            favorites = loadWordFavorites(),

            favoriteOnly = loadWordFavoriteOnly(),

            weakMode = loadWordWeakMode()
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

    fun saveWordProgress(
        progress: String
    ) {
        val uid = getUid() ?: return

        db.collection("users")
            .document(uid)
            .collection("word")
            .document("progress")
            .set(
                mapOf(
                    "progress" to progress
                )
            )
    }

    suspend fun loadWordProgress(): String {
        val uid = getUid() ?: return "[]"

        val snapshot =
            db.collection("users")
                .document(uid)
                .collection("word")
                .document("progress")
                .get()
                .await()

        return snapshot.getString("progress") ?: "[]"
    }

    fun saveWordDeckIndex(
        deckIndex: Int
    ) {
        val uid = getUid() ?: return

        db.collection("users")
            .document(uid)
            .collection("word")
            .document("deckIndex")
            .set(
                mapOf(
                    "deckIndex" to deckIndex
                )
            )
    }

    suspend fun loadWordDeckIndex(): Int {
        val uid = getUid() ?: return 0

        val snapshot =
            db.collection("users")
                .document(uid)
                .collection("word")
                .document("deckIndex")
                .get()
                .await()

        val rate = snapshot.getDouble("deckIndex") ?: 0
        return rate.toInt()
    }

    fun saveWordDeckOrder(
        deckOrder: String
    ) {
        val uid = getUid() ?: return

        db.collection("users")
            .document(uid)
            .collection("word")
            .document("deckOrder")
            .set(
                mapOf(
                    "deckOrder" to deckOrder
                )
            )
    }

    suspend fun loadWordDeckOrder(): String {
        val uid = getUid() ?: return ""

        val snapshot =
            db.collection("users")
                .document(uid)
                .collection("word")
                .document("deckOrder")
                .get()
                .await()

        return snapshot.getString("deckOrder") ?: ""
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

    suspend fun loadWordFilterPos(): Set<String> {
        val uid = getUid() ?: return emptySet()

        val snapshot =
            db.collection("users")
                .document(uid)
                .collection("word")
                .document("filterPos")
                .get()
                .await()

        val filterPos =
            snapshot.get("filterPos") as? List<String> ?: emptyList()

        return filterPos.toSet()
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

    suspend fun loadWordCsvFileList(): String {
        val uid = getUid() ?: return ""

        val snapshot =
            db.collection("users")
                .document(uid)
                .collection("word")
                .document("csvFileList")
                .get()
                .await()

        return snapshot.getString("csvFileList") ?: ""
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

    suspend fun loadWordFavorites(): Set<String> {
        val uid = getUid() ?: return emptySet()

        val snapshot =
            db.collection("users")
                .document(uid)
                .collection("word")
                .document("favorites")
                .get()
                .await()

        if (!snapshot.exists()) {
            return emptySet()
        }

        return (snapshot.get("favorites") as? List<String>)
            ?.toSet()
            ?: emptySet()
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

    suspend fun loadWordFavoriteOnly(): Boolean {
        val uid = getUid() ?: return false

        val snapshot =
            db.collection("users")
                .document(uid)
                .collection("word")
                .document("favoriteOnly")
                .get()
                .await()

        return snapshot.getBoolean("favoriteOnly") ?: false
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

    suspend fun loadWordWeakMode(): Boolean {
        val uid = getUid() ?: return false

        val snapshot =
            db.collection("users")
                .document(uid)
                .collection("word")
                .document("weakMode")
                .get()
                .await()

        return snapshot.getBoolean("weakMode") ?: false
    }

    fun saveWordStudyHistory(
        json: String
    ) {
        val uid = getUid() ?: return

        db.collection("users")
            .document(uid)
            .collection("word")
            .document("studyHistory")
            .set(
                mapOf(
                    "history" to json
                )
            )
    }

    suspend fun loadWordStudyHistory(): String? {
        val uid = getUid() ?: return null

        val snapshot =
            db.collection("users")
                .document(uid)
                .collection("word")
                .document("studyHistory")
                .get()
                .await()

        val history =
            snapshot.get("history") as? String

        return history.toString()
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

    suspend fun loadChunkLearningState(): ChunkLeaningState {
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

        return ChunkLeaningState(
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

            filterCategory = loadChunkFilterCategory(),

            filterDifficulty = loadChunkFilterDifficulty(),

            weakMode = loadChunkWeakMode()
        )
    }

    fun saveChunkDeckIndex(
        deckIndex: Int
    ) {
        val uid = getUid() ?: return

        db.collection("users")
            .document(uid)
            .collection("chunk")
            .document("deckIndex")
            .set(
                mapOf(
                    "deckIndex" to deckIndex
                )
            )
    }

    fun saveChunkDeckOrder(
        deckOrder: String
    ) {
        val uid = getUid() ?: return

        db.collection("users")
            .document(uid)
            .collection("chunk")
            .document("deckOrder")
            .set(
                mapOf(
                    "deckOrder" to deckOrder
                )
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

    suspend fun loadChunkFilterCategory(): Set<String> {
        val uid = getUid() ?: return emptySet()

        val snapshot =
            db.collection("users")
                .document(uid)
                .collection("chunk")
                .document("filterCategory")
                .get()
                .await()

        val filterCategory =
            snapshot.get("filterCategory") as? List<String> ?: emptyList()

        return filterCategory.toSet()
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

    suspend fun loadChunkFilterDifficulty(): Set<String> {
        val uid = getUid() ?: return emptySet()

        val snapshot =
            db.collection("users")
                .document(uid)
                .collection("chunk")
                .document("filterDifficulty")
                .get()
                .await()

        val filterDifficulty =
            snapshot.get("filterDifficulty") as? List<String> ?: emptyList()

        return filterDifficulty.toSet()
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

    suspend fun loadChunkWeakMode(): Boolean {
        val uid = getUid() ?: return false

        val snapshot =
            db.collection("users")
                .document(uid)
                .collection("chunk")
                .document("weakMode")
                .get()
                .await()

        return snapshot.getBoolean("weakMode") ?: false
    }

    suspend fun loadGrammarLearningState(): GrammarLeaningState {
        return GrammarLeaningState(
            theme = loadGrammarTheme(),
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

    suspend fun loadGrammarTheme(): String {
        val uid = getUid() ?: return "基本形"

        val snapshot =
            db.collection("users")
                .document(uid)
                .collection("grammar")
                .document("theme")
                .get()
                .await()

        return snapshot.getString("theme") ?: "基本形"
    }

    fun saveGrammarSpeechRate(
        speechRate: Float
    ) {
        val uid = getUid() ?: return

        db.collection("users")
            .document(uid)
            .collection("grammar")
            .document("speechRate")
            .set(
                mapOf(
                    "speechRate" to speechRate
                )
            )
    }

    suspend fun loadGrammarSpeechRate(): Float {
        val uid = getUid() ?: return 0.8f

        val snapshot =
            db.collection("users")
                .document(uid)
                .collection("grammar")
                .document("speechRate")
                .get()
                .await()

        val rate = snapshot.getDouble("speechRate") ?: 0.8f
        return rate.toFloat()
    }

    suspend fun loadConversationLearningState(): ConversationLeaningState {
        return ConversationLeaningState(
            theme = loadConversationTheme(),

            part = loadConversationPart(),
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

    suspend fun loadConversationTheme(): String {
        val uid = getUid() ?: return "一般"

        val snapshot =
            db.collection("users")
                .document(uid)
                .collection("conversation")
                .document("theme")
                .get()
                .await()

        return snapshot.getString("theme") ?: "一般"
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

    suspend fun loadConversationPart(): String {
        val uid = getUid() ?: return "全部"

        val snapshot =
            db.collection("users")
                .document(uid)
                .collection("conversation")
                .document("part")
                .get()
                .await()

        return snapshot.getString("part") ?: "全部"
    }

    fun saveConversationSpeechRate(
        speechRate: Float
    ) {
        val uid = getUid() ?: return

        db.collection("users")
            .document(uid)
            .collection("conversation")
            .document("speechRate")
            .set(
                mapOf(
                    "speechRate" to speechRate
                )
            )
    }

    suspend fun loadConversationSpeechRate(): Float {
        val uid = getUid() ?: return 0.8f

        val snapshot =
            db.collection("users")
                .document(uid)
                .collection("conversation")
                .document("speechRate")
                .get()
                .await()

        val rate = snapshot.getDouble("speechRate") ?: 0.8f
        return rate.toFloat()
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

    suspend fun loadResetAT(): Long {
        val uid = getUid() ?: return 0L

        val snapshot =
            db.collection("users")
                .document(uid)
                .collection("sync")
                .document("reset")
                .get()
                .await()

        return snapshot.getLong("resetAT")
            ?: 0L
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

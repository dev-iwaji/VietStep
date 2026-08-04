package com.example.vocabapp.ui.word

import com.example.vocabapp.data.model.Word
import com.example.vocabapp.data.source.baseWords
import com.example.vocabapp.data.model.CsvFile

data class WordUiState(
    val dirty: Boolean = false,

    val words: List<Word> = baseWords,

    val deck: List<Word> = emptyList(),

    val deckIndex: Int = 0,

    val selectedPos: Set<String> = emptySet(),

    val searchWord: Boolean = false,

    val weakMode: Boolean = false,

    val favoriteOnly: Boolean = false,

    val favorites: Set<String> = emptySet(),

    val studyMode: String = "card",

    val csvList: List<CsvFile> = emptyList(),

    val isSyncing: Boolean = true,

    val syncMessage: String = "",

    val isInitialized: Boolean = false,
)


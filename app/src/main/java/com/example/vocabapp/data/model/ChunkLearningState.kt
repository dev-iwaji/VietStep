package com.example.vocabapp.data.model

data class ChunkLeaningState(
    val progress: String = "[]",
    val deckOrder: String = "[]",
    val deckIndex: Int = 0,
    val filterCategory: Set<String> = emptySet(),
    val filterDifficulty: Set<String> = emptySet(),
    val weakMode: Boolean = false
)

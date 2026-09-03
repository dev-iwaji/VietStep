package com.iwaji.vietstep.data.model

import com.iwaji.vietstep.ui.chunk.ChunkDefaults

data class ChunkLearningState(
    val progress: String = "[]",
    val deckOrder: String = "[]",
    val deckIndex: Int = 0,
    val filterCategory: Set<String> = emptySet(),
    val filterDifficulty: Set<String> = ChunkDefaults.DIFFICULTIES,
    val weakMode: Boolean = false
)

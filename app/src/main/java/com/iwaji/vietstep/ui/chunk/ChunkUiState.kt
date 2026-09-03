package com.iwaji.vietstep.ui.chunk

import com.iwaji.vietstep.data.model.Chunk
import com.iwaji.vietstep.data.source.baseChunks
import com.iwaji.vietstep.data.model.QuizStats

data class ChunkUiState(
    val chunks: List<Chunk> = baseChunks,

    val deck: List<Chunk> = emptyList(),

    val deckIndex: Int = 0,

    val selectedCategory: Set<String> = emptySet(),

    val selectedDifficulty: Set<String> = ChunkDefaults.DIFFICULTIES,

    val weakMode: Boolean = false,

    val studyMode: String = "card",

    val quizStats: QuizStats = QuizStats(),
)

object ChunkDefaults {
    val DIFFICULTIES = setOf(
        "初級",
        "中級",
        "上級"
    )
}

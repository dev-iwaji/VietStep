package com.example.vocabapp.data.model

import androidx.compose.ui.graphics.Color

data class Chunk(
    val category: String,
    val difficulty: String,
    val pattern: String,
    val memo: String,

    val vietnamese: String,
    val japanese: String,

    val level: Int = 5,
    val streak: Int = 0,
    val recentResults: List<Boolean> = emptyList()
)

fun Chunk.deckKey(): String {
    return "${vietnamese}_${japanese}"
}

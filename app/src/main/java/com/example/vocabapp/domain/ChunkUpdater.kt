package com.example.vocabapp.domain

import android.util.Log

import com.example.vocabapp.data.model.Chunk

fun updateChunk(chunk: Chunk, correct: Boolean): Chunk {
    val history = (chunk.recentResults + correct).takeLast(5)

    val newStreak = if (correct) chunk.streak + 1 else 0

    // ✅ レベル更新
    val newLevel = if (correct) {
        (chunk.level - 1).coerceAtLeast(1)
    } else {
        (chunk.level + 1).coerceAtMost(5)
    }

    return chunk.copy(
        recentResults = history,
        streak = newStreak,
        level = newLevel
    )
}

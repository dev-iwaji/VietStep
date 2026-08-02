package com.example.vocabapp.domain

import android.util.Log

import com.example.vocabapp.data.model.Word

fun updateWord(word: Word, correct: Boolean): Word {
    val history = (word.recentResults + correct).takeLast(5)

    val newStreak = if (correct) word.streak + 1 else 0

    // ✅ レベル更新
    val newLevel = if (correct) {
        (word.level - 1).coerceAtLeast(1)
    } else {
        (word.level + 1).coerceAtMost(5)
    }

    return word.copy(
        recentResults = history,
        streak = newStreak,
        level = newLevel
    )
}

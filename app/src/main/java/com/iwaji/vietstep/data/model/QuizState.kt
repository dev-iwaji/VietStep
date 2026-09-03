package com.iwaji.vietstep.data.model

data class QuizStats(
    val streak: Int = 0,
    val bestStreak: Int = 0,
    val total: Int = 0,
    val correctCount: Int = 0,
)

fun QuizStats.updated(
    correct: Boolean
): QuizStats {

    val newStreak =
        if (correct) {
            streak + 1
        } else {
            0
        }

    return copy(
        streak = newStreak,
        bestStreak = maxOf(
            bestStreak,
            newStreak
        ),
        total = total + 1,
        correctCount = correctCount + if (correct) 1 else 0
    )
}
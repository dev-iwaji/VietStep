package com.example.vocabapp.ui.grammar

import com.example.vocabapp.data.model.QuizStats

data class GrammarUiState(
    val selectedTheme: String = "",

    val studyMode: String = "card",

    val speechRate: Float = 0.8f,

    val quizStats: QuizStats = QuizStats(),)

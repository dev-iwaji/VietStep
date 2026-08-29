package com.example.vocabapp.ui.grammar

import com.example.vocabapp.data.model.Grammar
import com.example.vocabapp.data.model.QuizStats

data class GrammarUiState(
    val selectedTheme: String = "",

    val studyMode: String = "card",

    val deck: List<Grammar> = emptyList(),

    val deckIndex: Int = 0,

    val speechRate: Float = 0.8f,

    val quizStats: QuizStats = QuizStats(),)

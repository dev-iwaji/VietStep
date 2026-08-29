package com.example.vocabapp.ui.conversation

import com.example.vocabapp.data.model.Conversation
import com.example.vocabapp.data.model.QuizStats

data class ConversationUiState(
    val selectedTheme: String = "",

    val selectedPart: String = "",

    val studyMode: String = "card",

    val deck: List<Conversation> = emptyList(),

    val deckIndex: Int = 0,

    val speechRate: Float = 0.8f,

    val quizStats: QuizStats = QuizStats(),)

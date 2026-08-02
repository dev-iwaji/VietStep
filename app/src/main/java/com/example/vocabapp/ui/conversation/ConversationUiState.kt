package com.example.vocabapp.ui.conversation

data class ConversationUiState(
    val selectedTheme: String = "",

    val selectedPart: String = "",

    val studyMode: String = "card",

    val speechRate: Float = 0.8f,
)

package com.iwaji.vietstep.data.model

data class WordLearningState(
    val progress: String = "[]",
    val deckOrder: String = "[]",
    val deckIndex: Int = 0,
    val studyHistory: String = "[]",
    val filterPos: Set<String> = emptySet(),
    val favorites: Set<String> = emptySet(),
    val favoriteOnly: Boolean = false,
    val weakMode: Boolean = false
)

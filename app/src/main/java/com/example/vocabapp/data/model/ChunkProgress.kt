package com.example.vocabapp.data.model

data class ChunkProgress(
    val key: String,
    val level: Int,
    val streak: Int,
    val recentResults: List<Boolean>
)
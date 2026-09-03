package com.iwaji.vietstep.data.model

data class WordProgress(
    val key: String,
    val level: Int,
    val streak: Int,
    val recentResults: List<Boolean>
)
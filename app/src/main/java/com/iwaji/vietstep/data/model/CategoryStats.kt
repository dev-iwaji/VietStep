package com.iwaji.vietstep.data.model

data class CategoryStats(
    val averageLevel: Float,
    val learned: Int,
    val total: Int,
    val maxLevel: Int,
    val levelCounts: List<Int>,
)

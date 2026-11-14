package com.iwaji.vietstep.domain.stats

import com.iwaji.vietstep.data.model.CategoryStats

object CategoryStatsCalculator {
    fun <T> calc(
        items: List<T>,
        levelOf: (T) -> Int,
        isLearned: (T) -> Boolean
    ): CategoryStats {

        val total = items.size

        val learned =
            items.count { isLearned(it) }

        val average =
            if (total == 0) 0f
            else items.sumOf { levelOf(it) }.toFloat() / total

        val maxLevel =
            items.maxOfOrNull { levelOf(it) } ?: 0

        val levelCounts = IntArray(5)
        items.forEach {
            val level = levelOf(it).coerceIn(1, 5)
            levelCounts[level - 1]++
        }

        return CategoryStats(
            averageLevel = average,
            learned = learned,
            total = total,
            maxLevel = maxLevel,
            levelCounts = levelCounts.toList()
        )
    }
}

package com.example.vocabapp.domain

import com.example.vocabapp.data.model.Word
import com.example.vocabapp.data.model.Chunk
import com.example.vocabapp.data.model.Grammar

fun generateWordDeck(words: List<Word>): MutableList<Word> {

    val lv1 = words.filter { it.level == 1 }
    val lv2 = words.filter { it.level == 2 }
    val lv3 = words.filter { it.level == 3 }
    val lv4 = words.filter { it.level == 4 }
    val lv5 = words.filter { it.level == 5 }

    fun pick(list: List<Word>, rate: Float): List<Word> {
        if (list.isEmpty()) return emptyList()
        val count = (list.size * rate).toInt().coerceAtLeast(1)
        return list.shuffled().take(count)
    }

    val selected = mutableListOf<Word>()

    selected += pick(lv5, 1.0f)
    selected += pick(lv4, 1.0f)
    selected += pick(lv3, 0.8f)
    selected += pick(lv2, 0.5f)
    selected += pick(lv1, 0.2f)

    return selected.shuffled().toMutableList()
}

fun generateChunkDeck(chunks: List<Chunk>): MutableList<Chunk> {

    val lv1 = chunks.filter { it.level == 1 }
    val lv2 = chunks.filter { it.level == 2 }
    val lv3 = chunks.filter { it.level == 3 }
    val lv4 = chunks.filter { it.level == 4 }
    val lv5 = chunks.filter { it.level == 5 }

    fun pick(list: List<Chunk>, rate: Float): List<Chunk> {
        if (list.isEmpty()) return emptyList()
        val count = (list.size * rate).toInt().coerceAtLeast(1)
        return list.shuffled().take(count)
    }

    val selected = mutableListOf<Chunk>()

    selected += pick(lv5, 1.0f)
    selected += pick(lv4, 1.0f)
    selected += pick(lv3, 0.8f)
    selected += pick(lv2, 0.5f)
    selected += pick(lv1, 0.2f)

    return selected.shuffled().toMutableList()
}

fun generateGrammarDeck(
    items: List<Grammar>,
    previousDeck: List<Grammar> = emptyList()
): MutableList<Grammar> {

    return items
        .groupBy { it.pattern }
        .map { (pattern, examples) ->

            val previous =
                previousDeck.firstOrNull {
                    it.pattern == pattern
                }

            val candidates =
                if (examples.size > 1 && previous != null) {
                    examples.filter { it != previous }
                } else {
                    examples
                }

            candidates.random()
        }
        .toMutableList()
}

package com.example.vocabapp.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import com.example.vocabapp.data.model.Chunk
import com.example.vocabapp.data.model.ChunkProgress

import com.example.vocabapp.data.model.deckKey
import com.example.vocabapp.data.repository.ChunkRepository

fun saveChunkProgress(
    repository: ChunkRepository,
    chunk: Chunk
) {

    val gson = Gson()

    val json = repository.loadProgress()

    val progressList: MutableList<ChunkProgress> =
        gson.fromJson(
            json,
            object : TypeToken<MutableList<ChunkProgress>>() {}.type
        )

    val progress = ChunkProgress(
        key = chunk.deckKey(),
        level = chunk.level,
        streak = chunk.streak,
        recentResults = chunk.recentResults
    )

    val index =
        progressList.indexOfFirst {
            it.key == progress.key
        }

    if (index >= 0) {

        progressList[index] = progress

    } else {

        progressList.add(progress)

    }

    repository.saveProgress(gson, progressList)
}

fun loadChunkProgress(
    repository: ChunkRepository
): Map<String, ChunkProgress> {

    val json = repository.loadProgress()

    val list: List<ChunkProgress> =
        Gson().fromJson(
            json,
            object : TypeToken<List<ChunkProgress>>() {}.type
        )

    return list.associateBy {
        it.key
    }
}

package com.iwaji.vietstep.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import com.iwaji.vietstep.data.model.Word
import com.iwaji.vietstep.data.model.WordProgress
import com.iwaji.vietstep.data.repository.WordRepository
import com.iwaji.vietstep.data.model.deckKey
import android.util.Log

fun saveWordProgress(
    repository: WordRepository,
    word: Word
) {

    val gson = Gson()

    val json = repository.loadProgress()

    if (json.isNullOrBlank() || !json.trim().startsWith("[")) {
        return
    }
    val progressList: MutableList<WordProgress> =
        gson.fromJson(
            json,
            object : TypeToken<MutableList<WordProgress>>() {}.type
        )

    val progress = WordProgress(
        key = word.deckKey(),
        level = word.level,
        streak = word.streak,
        recentResults = word.recentResults
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

fun loadWordProgress(
    repository: WordRepository
): Map<String, WordProgress> {

    val json = repository.loadProgress()

    if (json.isNullOrBlank() || !json.trim().startsWith("[")) {
        Log.e("WordProgress", "Invalid JSON, fallback to empty list: $json")
        return emptyMap()
    }
    val list: List<WordProgress> =
        Gson().fromJson(
            json,
            object : TypeToken<List<WordProgress>>() {}.type
        )

    return list.associateBy {
        it.key
    }
}

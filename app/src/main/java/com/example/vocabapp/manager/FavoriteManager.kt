package com.example.vocabapp.manager

object FavoriteManager {

    fun toggle(
        favorites: Set<String>,
        key: String
    ): Set<String> {
        return if (favorites.contains(key)) {
            favorites - key
        } else {
            favorites + key
        }
    }
}

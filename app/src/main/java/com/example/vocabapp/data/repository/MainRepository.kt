package com.example.vocabapp.data.repository

import android.content.SharedPreferences

import com.example.vocabapp.utils.PrefKeys
import android.util.Log

class MainRepository (
    private val prefs: SharedPreferences,
    private val firebaseRepository: FirebaseRepository
) {

    fun loadResetAT(): Long {
        return prefs.getLong(
            PrefKeys.RESET_AT,
            0L
        )
    }

    fun saveLocalResetAT(
        time: Long
    ) {
        prefs.edit()
            .putLong(
                PrefKeys.RESET_AT,
                time
            )
            .apply()
    }

    fun saveResetAT(
        time: Long
    ) {
        saveLocalResetAT(time)
        firebaseRepository.saveResetAT(time)
    }

    fun loadSoundVolume(): Float {
        return prefs.getFloat(
            PrefKeys.MAIN_SOUND_VOLUME,
            0.5f
        )
    }

    fun saveSoundVolume(
        volume: Float
    ) {
        prefs.edit()
            .putFloat(
                PrefKeys.MAIN_SOUND_VOLUME,
                volume
            )
            .apply()
    }

    fun loadDarkMode(): Boolean {
        return prefs.getBoolean(
            PrefKeys.MAIN_DARK_MODE,
            false
        )
    }

    fun saveDarkMode(
        enabled: Boolean
    ) {
        prefs.edit()
            .putBoolean(
                PrefKeys.MAIN_DARK_MODE,
                enabled
            )
            .apply()
    }
}

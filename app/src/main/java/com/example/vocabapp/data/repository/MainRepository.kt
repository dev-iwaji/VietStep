package com.example.vocabapp.data.repository

import android.content.SharedPreferences
import com.example.vocabapp.utils.PrefKeys

class MainRepository (
    private val prefs: SharedPreferences,
    private val firebaseRepository: FirebaseRepository
) {

    suspend fun restoreFromFirebase() {

        val volume = firebaseRepository.loadMainSoundVolume()
        prefs.edit()
            .putFloat(PrefKeys.MAIN_SOUND_VOLUME, volume)
            .apply()

        val enabled = firebaseRepository.loadMainDarkMode()
        prefs.edit()
            .putBoolean(PrefKeys.MAIN_DARK_MODE, enabled)
            .apply()

        val resetAT = firebaseRepository.loadResetAT()
        prefs.edit()
            .putLong(PrefKeys.RESET_AT, resetAT)
            .apply()
    }

    fun loadResetAT(): Long {
        return prefs.getLong(
            PrefKeys.RESET_AT,
            0L
        )
    }

    fun saveResetAT(time: Long) {
        prefs.edit()
            .putLong(
                PrefKeys.RESET_AT,
                time
            )
            .apply()

        firebaseRepository.saveResetAT(time)
    }

    fun loadSoundVolume(): Float {
        return prefs.getFloat(
            PrefKeys.MAIN_SOUND_VOLUME,
            0.5f
        )
    }

    fun saveSoundVolume(volume: Float) {
        prefs.edit()
            .putFloat(
                PrefKeys.MAIN_SOUND_VOLUME,
                volume
            )
            .apply()

        firebaseRepository.saveMainSoundVolume(volume)
    }

    fun loadDarkMode(): Boolean {
        return prefs.getBoolean(
            PrefKeys.MAIN_DARK_MODE,
            false
        )
    }

    fun saveDarkMode(enabled: Boolean) {
        prefs.edit()
            .putBoolean(
                PrefKeys.MAIN_DARK_MODE,
                enabled
            )
            .apply()

        firebaseRepository.saveMainDarkMode(enabled)
    }
}
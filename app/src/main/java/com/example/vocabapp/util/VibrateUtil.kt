package com.example.vocabapp.util

import android.content.Context
import android.os.Vibrator
import android.os.VibrationEffect
import android.os.Build

fun vibration(context: Context) {

    val vibrator = context.getSystemService(Vibrator::class.java)

    vibrator?.let {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            it.vibrate(
                VibrationEffect.createOneShot(
                    50,
                    VibrationEffect.DEFAULT_AMPLITUDE
                )
            )
        }
    }
}

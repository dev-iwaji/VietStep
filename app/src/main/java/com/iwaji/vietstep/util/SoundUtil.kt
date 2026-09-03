package com.iwaji.vietstep.util

import android.content.Context

import android.media.MediaPlayer

fun playSound(
    context: Context, res: Int, volume: Float
) {
    MediaPlayer.create(context, res)?.apply {
        setVolume(volume, volume)
        setOnCompletionListener { it.release() }
        start()
    }
}

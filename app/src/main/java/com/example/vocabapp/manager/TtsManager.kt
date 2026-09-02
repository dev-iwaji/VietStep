package com.example.vocabapp.manager

import android.speech.tts.TextToSpeech

object TtsManager {

    fun speak(
        tts: TextToSpeech?, text: String
    ) {
        tts?.speak(
            text,
            TextToSpeech.QUEUE_FLUSH,
            null,
            null
        )
    }
}

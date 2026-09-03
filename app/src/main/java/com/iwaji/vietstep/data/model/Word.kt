package com.iwaji.vietstep.data.model

import androidx.compose.ui.graphics.Color

data class Word(
    val categoryId: String,
    val partOfSpeech: String,
    val vietnamese: String,
    val japanese: String,
    val recentResults: List<Boolean> = emptyList(),
    val streak: Int = 0,
    val level: Int = 5
)

fun getPosColor(pos: String): Color {
    return when (pos) {
        "動" -> Color(0xFF4CAF50) // 動詞
        "名" -> Color(0xFF2196F3) // 名詞
        "形" -> Color(0xFFFF9800) // 形容詞
        "副" -> Color(0xFF9C27B0) // 副詞
        "前" -> Color(0xFF009688) // 前置詞
        "代" -> Color(0xFF3F51B5) // 代名詞
        "疑" -> Color(0xFFFF5722) // 疑問詞
        "助" -> Color(0xFF795548) // 助辞
        "接" -> Color(0xFF607D8B) // 接続詞
        "感" -> Color(0xFFE91E63) // 感動詞
        "類" -> Color(0xFF8BC34A) // 類別詞

        else -> Color.Gray
    }
}

fun Word.deckKey(): String {
    return "${vietnamese}_${japanese}"
}

package com.iwaji.vietstep.ui.stats.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.max

@Composable
fun TodayResultBar(
    correct: Int,
    incorrect: Int
) {
    val total = correct + incorrect
    val correctRatio = correct.toFloat() / total

    val animatedCorrect by animateFloatAsState(
        targetValue = correctRatio,
        animationSpec = tween(durationMillis = 1200)
    )
    val animatedIncorrect = 1f - animatedCorrect

    val safeCorrect = max(animatedCorrect, 0.0001f)
    val safeIncorrect = max(animatedIncorrect, 0.0001f)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(Color.LightGray)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(safeCorrect)
                    .background(Color(0xFF4CAF50))
            )
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(safeIncorrect)
                    .background(Color(0xFFF44336))
            )
        }

        Spacer(Modifier.height(12.dp))

        Text(
            "正解 $correct (${ "%.1f".format(correctRatio * 100) }%) : ミス $incorrect (${ "%.1f".format((1 - correctRatio) * 100) }%)",
            fontSize = 16.sp
        )
    }
}

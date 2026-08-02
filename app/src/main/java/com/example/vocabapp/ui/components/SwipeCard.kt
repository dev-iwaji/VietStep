package com.example.vocabapp.ui.components

import android.content.Context
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.border
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.material3.Text

import androidx.compose.ui.input.pointer.consumePositionChange

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.draw.shadow

import com.example.vocabapp.util.vibration

@Composable
fun SwipeCard(
    onRight: () -> Unit,
    onLeft: () -> Unit,
    content: @Composable () -> Unit
) {

    val context = LocalContext.current

    var offsetX by remember { mutableStateOf(0f) }
    var alpha by remember { mutableStateOf(1f) }
    var scale by remember { mutableStateOf(1f) }

    val animatedOffset by animateFloatAsState(offsetX)
    val animatedAlpha by animateFloatAsState(alpha)
    val animatedScale by animateFloatAsState(scale)

    val defaultCardColor = MaterialTheme.colorScheme.surface

    val backgroundColor = when {
        offsetX > 50 -> Color(0xFFB2FFB2)
        offsetX < -50 -> Color(0xFFFFB2B2)
        else -> defaultCardColor
    }

    val latestOnRight by rememberUpdatedState(onRight)
    val latestOnLeft by rememberUpdatedState(onLeft)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .shadow(
                elevation = 12.dp,                     // ★ 影の強さ（お好みで 8〜20dp）
                shape = RoundedCornerShape(20.dp),     // ★ 枠線と同じ角丸
                clip = false                           // ★ 影を外側に描画
            ).graphicsLayer {
                translationX = animatedOffset
                this.alpha = animatedAlpha
                this.scaleX = animatedScale
                this.scaleY = animatedScale
            }
            .background(backgroundColor, RoundedCornerShape(20.dp))
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.outlineVariant,
                shape = RoundedCornerShape(20.dp)
            )
            .pointerInput(Unit) {
                detectDragGestures(
                    onDrag = { change, dragAmount ->
                        change.consumePositionChange()
                        offsetX += dragAmount.x
                        scale = 1f + (kotlin.math.abs(offsetX) / 1000f)
                        alpha = 1f - (kotlin.math.abs(offsetX) / 1000f)
                    },
                    onDragEnd = {
                        when {
                            offsetX > 200 -> {
                                vibration(context)
                                latestOnRight()
                            }
                            offsetX < -200 -> {
                                vibration(context)
                                latestOnLeft()
                            }
                        }
                        offsetX = 0f
                        scale = 1f
                        alpha = 1f
                    }
                )
            },
        contentAlignment = Alignment.Center
    ) {

        // ★★★ ここに自由な UI を描画できる ★★★
        content()

        if (offsetX > 80) {
            Text(
                "✔",
                fontSize = 60.sp,
                color = Color.Green,
                modifier = Modifier.align(Alignment.TopEnd).padding(16.dp)
            )
        }

        if (offsetX < -80) {
            Text(
                "✖",
                fontSize = 60.sp,
                color = Color.Red,
                modifier = Modifier.align(Alignment.TopStart).padding(16.dp)
            )
        }
    }
}

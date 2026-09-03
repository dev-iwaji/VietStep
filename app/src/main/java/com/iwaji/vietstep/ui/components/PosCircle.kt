package com.iwaji.vietstep.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PosCircle(pos: String){
    val color = when(pos){
        "名"->Color.Blue
        "動"->Color.Red
        "形"->Color.Green
        "副"->Color.Magenta
        else->Color.Gray
    }

    Box(
        modifier = Modifier.size(28.dp).background(color, CircleShape),
        contentAlignment = Alignment.Center
    ){
        Text(pos, color = Color.White, fontSize = 12.sp)
    }
}

package com.iwaji.vietstep.ui.stats.components

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text

@Composable
fun StatsSection(
    title: String,
    content: @Composable () -> Unit
) {
    Spacer(Modifier.height(24.dp))

    Text(
        title,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            Modifier.padding(16.dp)
        ) {
            content()
        }
    }
}

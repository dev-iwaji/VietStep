package com.example.vocabapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ThemeSelectScreen(
    title: String,
    themes: List<String>,
    onSelected: (String) -> Unit
) {

    Column(
        modifier = Modifier.padding(16.dp)
    ) {

        Text(title)

        Spacer(Modifier.height(12.dp))

        themes.forEach { theme ->

            Button(
                onClick = {
                    onSelected(theme)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(theme)
            }

            Spacer(Modifier.height(8.dp))
        }
    }
}

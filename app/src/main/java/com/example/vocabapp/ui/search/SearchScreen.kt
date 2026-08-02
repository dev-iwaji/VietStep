package com.example.vocabapp.ui.search

import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.compose.runtime.*
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.platform.LocalContext

import com.example.vocabapp.ui.word.WordViewModel
import com.example.vocabapp.data.model.Word
import com.example.vocabapp.data.model.deckKey
import com.example.vocabapp.data.model.getPosColor

@Composable
fun SearchScreen(
    wordViewModel: WordViewModel,
    onSelect: (Word) -> Unit
) {

    val uiState by
    wordViewModel.uiState.collectAsState()

    var query by remember { mutableStateOf("") }
    var favoriteOnly by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        TextField(
            value = query,
            onValueChange = { query = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("検索") }
        )

        Spacer(Modifier.height(8.dp))

        // ✅ お気に入りフィルター（検索専用）
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = favoriteOnly,
                onCheckedChange = { favoriteOnly = it }
            )
            Text("お気に入りのみ")
        }

        Spacer(Modifier.height(8.dp))

        val filtered = uiState.words
            .filter {
                query.isEmpty() ||
                        it.vietnamese.contains(query, true) ||
                        it.japanese.contains(query, true)
            }
            .filter {
                if (uiState.favoriteOnly) uiState.favorites.contains(it.deckKey())
                else true
            }

        LazyColumn {
            items(filtered) { word ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSelect(word) }
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = if (uiState.favorites.contains(word.deckKey())) "★" else "☆",
                        color = if (uiState.favorites.contains(word.deckKey())) Color(0xFFFFC107) else Color.Gray,

                        modifier = Modifier.clickable {

                            wordViewModel.toggleFavorites(word)
                        },
                        fontSize = 20.sp
                    )

                    // ✅ 品詞アイコン
                    Box(
                        modifier = Modifier
                            .size(22.dp)
                            .background(
                                getPosColor(word.partOfSpeech),
                                CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            word.partOfSpeech,
                            color = Color.White,
                            fontSize = 10.sp
                        )
                    }

                    Spacer(Modifier.width(6.dp))

                    // ✅ ベトナム語
                    Text(
                        word.vietnamese,
                        fontSize = 18.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                // ✅ 日本語（右側で揃う）
                Text(
                    word.japanese,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                }
            }
        }
    }
}

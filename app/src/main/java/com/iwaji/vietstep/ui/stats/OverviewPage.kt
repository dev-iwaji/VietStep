package com.iwaji.vietstep.ui.stats

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues

import com.iwaji.vietstep.ui.word.WordViewModel
import com.iwaji.vietstep.ui.chunk.ChunkViewModel
import com.iwaji.vietstep.ui.grammar.GrammarViewModel
import com.iwaji.vietstep.ui.conversation.ConversationViewModel
import com.iwaji.vietstep.ui.stats.components.CategoryLevelCard

@Composable
fun OverviewPage(
    wordViewModel: WordViewModel,
    chunkViewModel: ChunkViewModel,
    grammarViewModel: GrammarViewModel,
    conversationViewModel: ConversationViewModel
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 12.dp
        ),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        item {
            CategoryLevelCard(
                title = "単語",
                stats = wordViewModel.getCategoryStats()
            )
        }

        item {
            CategoryLevelCard(
                title = "文型",
                stats = chunkViewModel.getCategoryStats()
            )
        }
/*
        item {
            CategoryLevelCard(
                title = "文法",
                stats = grammarStats
            )
        }

        item {
            CategoryLevelCard(
                title = "会話",
                stats = conversationStats
            )
        }
*/
    }
}

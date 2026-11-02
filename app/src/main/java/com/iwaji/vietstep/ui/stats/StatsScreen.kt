package com.iwaji.vietstep.ui.stats

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import com.iwaji.vietstep.ui.word.WordViewModel
import com.iwaji.vietstep.ui.chunk.ChunkViewModel
import com.iwaji.vietstep.ui.grammar.GrammarViewModel
import com.iwaji.vietstep.ui.conversation.ConversationViewModel

@Composable
fun StatsScreen(
    wordViewModel: WordViewModel,
    chunkViewModel: ChunkViewModel,
    grammarViewModel: GrammarViewModel,
    conversationViewModel: ConversationViewModel,
    refreshKey: Int
) {
    val wordWeeklyData =
        remember(refreshKey) {
            wordViewModel.getLast7DaysData()
        }

    val wordTodayStat =
        remember(refreshKey) {
            wordViewModel.getTodayStat()
        }

    var statsTab by rememberSaveable {
        mutableStateOf(StatsTab.OVERVIEW)
    }

    Column {

        TabRow(
            selectedTabIndex =
                statsTab.ordinal
        ) {

            Tab(
                selected =
                    statsTab == StatsTab.OVERVIEW,
                onClick = {
                    statsTab = StatsTab.OVERVIEW
                }
            ) {
                Text("概要")
            }

            Tab(
                selected =
                    statsTab == StatsTab.WORD,
                onClick = {
                    statsTab = StatsTab.WORD
                }
            ) {
                Text("単語")
            }

            Tab(
                selected =
                    statsTab == StatsTab.CHUNK,
                onClick = {
                    statsTab = StatsTab.CHUNK
                }
            ) {
                Text("文型")
            }

            Tab(
                selected =
                    statsTab == StatsTab.GRAMMAR,
                onClick = {
                    statsTab = StatsTab.GRAMMAR
                }
            ) {
                Text("文法")
            }

            Tab(
                selected =
                    statsTab == StatsTab.CONVERSATION,
                onClick = {
                    statsTab = StatsTab.CONVERSATION
                }
            ) {
                Text("会話")
            }
        }

        when (statsTab) {

            StatsTab.OVERVIEW -> {
                /*
                OverviewPage(
                    uiState = wordViewModel.uiState.collectAsState().value,
                    refreshKey = refreshKey
                )
*/
            }

            StatsTab.WORD ->
                WordStatsPage(
                    uiState = wordViewModel.uiState.collectAsState().value,
                    todayStat = wordTodayStat,
                    weeklyData = wordWeeklyData
                )

            StatsTab.CHUNK -> {
/*
                ChunkStatsPage(
                    uiState = chunkViewModel.uiState.collectAsState().value,
                    refreshKey = refreshKey
                )
*/
            }

            StatsTab.GRAMMAR -> {
/*
                GrammarStatsPage(
                    uiState = grammarViewModel.uiState.collectAsState().value,
                    refreshKey = refreshKey
                )
*/
            }

            StatsTab.CONVERSATION -> {
/*
                ConversationStatsPage(
                    uiState = conversationViewModel.uiState.collectAsState().value,
                    refreshKey = refreshKey
                )
*/
            }
        }
    }
}

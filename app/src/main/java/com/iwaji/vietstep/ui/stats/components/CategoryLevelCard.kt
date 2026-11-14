package com.iwaji.vietstep.ui.stats.components

import androidx.compose.runtime.Composable
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.HorizontalDivider

import com.iwaji.vietstep.data.model.CategoryStats

@Composable
fun CategoryLevelCard(
    title: String,
    stats: CategoryStats
) {

    Card {
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    "平均 Lv %.1f / %d"
                        .format(
                            stats.averageLevel,
                            stats.maxLevel
                    ),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(Modifier.height(12.dp))

                repeat(5) { index ->

                    val count = stats.levelCounts[index]

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            "Lv${index + 1}",
                            modifier = Modifier.width(36.dp)
                        )

                        LinearProgressIndicator(
                            progress = {
                                if (stats.total == 0)
                                    0f
                                else
                                    count.toFloat() / stats.total
                            },
                            modifier = Modifier.weight(1f)
                        )

                        Spacer(Modifier.width(8.dp))

                        Text(
                            "$count",
                            modifier = Modifier.widthIn(min = 36.dp, max = 64.dp),
                            textAlign = TextAlign.End
                        )
                    }

                    Spacer(Modifier.height(2.dp))
                }

                HorizontalDivider()

                Spacer(Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        "学習数",
                        modifier = Modifier.weight(1f),
                        fontWeight = FontWeight.SemiBold
                    )

                    Text(
                        "${stats.learned}/${stats.total}",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
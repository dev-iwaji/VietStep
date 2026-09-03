package com.iwaji.vietstep.ui.search

import kotlinx.coroutines.launch
import kotlin.math.roundToInt

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight

import com.iwaji.vietstep.ui.word.WordViewModel
import com.iwaji.vietstep.data.model.Word
import com.iwaji.vietstep.data.model.deckKey
import com.iwaji.vietstep.data.model.getPosColor

@Composable
fun SearchScreen(
    wordViewModel: WordViewModel,
    onSelect: (Word) -> Unit
) {

    val uiState by wordViewModel.uiState.collectAsState()

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

        val collator = remember {
            java.text.Collator.getInstance(
                java.util.Locale("vi", "VN")
            )
        }

        val filtered = uiState.words
            .filter {
                query.isEmpty() ||
                        it.vietnamese.contains(query, true) ||
                        it.japanese.contains(query, true)
            }
            .filter {
                if (favoriteOnly) {
                    uiState.favorites.contains(it.deckKey())
                } else {
                    true
                }
            }
            .sortedWith { a, b ->
                collator.compare(
                    a.vietnamese,
                    b.vietnamese
                )
            }

        val listState = rememberLazyListState()

        val scope = rememberCoroutineScope()

        val density = LocalDensity.current

        val thumbHeightPx =
            with(density) {
                32.dp.toPx()
            }

        val bubbleHalfHeightPx =
            with(density) {
                22.dp.toPx()
            }

        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 20.dp)
            ) {
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
                                color = if (uiState.favorites.contains(word.deckKey())) Color(
                                    0xFFFFC107
                                ) else Color.Gray,

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

            val indexLetters = listOf(
                "A", "Ă", "Â",
                "B", "C",
                "D", "Đ",
                "E", "Ê",
                "G", "H", "I",
                "K", "L", "M", "N",
                "O", "Ô", "Ơ",
                "P", "Q", "R", "S", "T",
                "U", "Ư",
                "V", "X", "Y"
            )

            var activeLetter by remember {mutableStateOf<String?>(null)}

            var activeY by remember {mutableFloatStateOf(0f)}

            var scrollerHeight by remember {mutableFloatStateOf(0f)}

            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .width(28.dp)
                    .fillMaxHeight()
                    .onGloballyPositioned {
                        scrollerHeight =
                            it.size.height.toFloat()
                    }
                    .pointerInput(filtered) {

                        fun updatePosition(y: Float) {

                            if (size.height <= 0) return

                            val safeY =
                                y.coerceIn(
                                    0f,
                                    size.height.toFloat()
                                )

                            activeY = safeY

                            val index =
                                ((safeY / size.height) *
                                        indexLetters.size)
                                    .toInt()
                                    .coerceIn(
                                        0,
                                        indexLetters.lastIndex
                                    )

                            val letter =
                                indexLetters[index]

                            if (letter != activeLetter) {

                                activeLetter = letter

                                val target =
                                    findIndexForVietnameseLetter(
                                        filtered,
                                        letter
                                    )

                                if (target >= 0) {
                                    scope.launch {
                                        listState.scrollToItem(target)
                                    }
                                }
                            }
                        }

                        detectVerticalDragGestures(

                            onDragStart = { offset ->
                                updatePosition(offset.y)
                            },

                            onVerticalDrag = { change, _ ->
                                updatePosition(
                                    change.position.y
                                )

                                change.consume()
                            },

                            onDragEnd = {
                                activeLetter = null
                            },

                            onDragCancel = {
                                activeLetter = null
                            }
                        )
                    }
            ) {

                // ✅ ドラッグ中だけ薄いスクロールバー
                if (
                    activeLetter != null &&
                    scrollerHeight > 0f
                ) {

                    // 薄いレール
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .width(3.dp)
                            .fillMaxHeight()
                            .background(
                                MaterialTheme.colorScheme.primary
                                    .copy(alpha = 0.12f)
                            )
                    )

                    // 現在位置
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .offset {
                                val y =
                                    (activeY - thumbHeightPx / 2)
                                        .coerceIn(
                                            0f,
                                            scrollerHeight -
                                                    thumbHeightPx
                                        )

                                IntOffset(
                                    x = 0,
                                    y = y.roundToInt()
                                )
                            }
                            .width(3.dp)
                            .height(32.dp)
                            .background(
                                MaterialTheme.colorScheme.primary
                            )
                    )
                }
            }
            if (
                activeLetter != null &&
                scrollerHeight > 0f
            ) {

                Surface(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(end = 36.dp)
                        .offset {
                            val y =
                                (activeY - bubbleHalfHeightPx)
                                    .coerceIn(
                                        0f,
                                        scrollerHeight -
                                                bubbleHalfHeightPx * 2
                                    )

                            IntOffset(
                                x = 0,
                                y = y.roundToInt()
                            )
                        },
                    shape = CircleShape,
                    tonalElevation = 4.dp
                ) {

                    Box(
                        modifier = Modifier.size(44.dp),
                        contentAlignment =
                        Alignment.Center
                    ) {
                        Text(
                            text = activeLetter!!,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

fun getVietnameseIndexLetter(
    text: String
): String {

    if (text.isEmpty()) {
        return ""
    }

    val first = text.first().lowercaseChar()

    return when (first) {

        'a', 'à', 'á', 'ả', 'ã', 'ạ' ->
            "A"

        'ă', 'ằ', 'ắ', 'ẳ', 'ẵ', 'ặ' ->
            "Ă"

        'â', 'ầ', 'ấ', 'ẩ', 'ẫ', 'ậ' ->
            "Â"

        'd' ->
            "D"

        'đ' ->
            "Đ"

        'e', 'è', 'é', 'ẻ', 'ẽ', 'ẹ' ->
            "E"

        'ê', 'ề', 'ế', 'ể', 'ễ', 'ệ' ->
            "Ê"

        'o', 'ò', 'ó', 'ỏ', 'õ', 'ọ' ->
            "O"

        'ô', 'ồ', 'ố', 'ổ', 'ỗ', 'ộ' ->
            "Ô"

        'ơ', 'ờ', 'ớ', 'ở', 'ỡ', 'ợ' ->
            "Ơ"

        'u', 'ù', 'ú', 'ủ', 'ũ', 'ụ' ->
            "U"

        'ư', 'ừ', 'ứ', 'ử', 'ữ', 'ự' ->
            "Ư"

        else ->
            first.uppercase()
    }
}

fun findIndexForVietnameseLetter(
    words: List<Word>,
    letter: String
): Int {

    return words.indexOfFirst { word ->
        getVietnameseIndexLetter(
            word.vietnamese
        ) == letter
    }
}

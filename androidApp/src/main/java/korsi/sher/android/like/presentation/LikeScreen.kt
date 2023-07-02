package korsi.sher.android.like.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import korsi.sher.android.core.presentation.components.PoemComponent
import korsi.sher.poem.presentation.like.LikeEvent
import korsi.sher.poem.presentation.like.LikeState

@Composable
fun LikeScreen(
    state: LikeState,
    onEvent: (LikeEvent) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color(state.colors.first))
            .padding(16.dp)
            .fillMaxSize()
    ) {

        if (state.likedPoems.isEmpty()) {
            item {
                Text(
                    text = "هنوز هیچ کرسی‌شعری رو لایک نکردی، وقتی لایک کنی میان اینجا.",
                    textAlign = TextAlign.Right,
                    color = Color(state.colors.second),
                    modifier = Modifier.padding(16.dp),
                    fontSize = 16.sp
                )
            }
        } else {
            items(
                count = state.likedPoems.size,
                key = {
                    state.likedPoems[it].id ?: it
                }
            ) {
                val poemItem = state.likedPoems[it]
                PoemComponent(
                    verse1 = poemItem.verse1,
                    verse2 = poemItem.verse2,
                    poet = poemItem.poet,
                    textColor = Color(state.colors.second),
                    onCopyClicked = { /*TODO*/ },
                    onFavoriteClicked = null
                )
            }

        }
    }
}
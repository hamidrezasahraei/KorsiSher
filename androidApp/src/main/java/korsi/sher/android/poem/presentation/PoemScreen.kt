package korsi.sher.android.poem.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import korsi.sher.android.poem.presentation.components.ProgressButton
import korsi.sher.poem.presentation.PoemEvent
import korsi.sher.poem.presentation.PoemState

@Composable
fun PoemScreen(
    state: PoemState,
    onEvent: (PoemEvent) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color(state.colors.first))
            .padding(16.dp)
            .fillMaxSize()
    ) {

        if (state.poemItem == null) {
            Text(
                text = "در قدیم در منطقه (طالقان)، مردان دور کرسی جمع می شدند و مشاعره می کردند.. بعد از چند دور که اشعار در ذهن کم می شد، بعضی از خودشان شعر می گفتند! دیگران می گفتند که فلانی کرسی شعر گفت! (در لفظ: برو بابا بازم کرسی شعر گفتی)",
                textAlign = TextAlign.Right,
                color = Color(state.colors.second),
                modifier = Modifier.padding(16.dp),
                fontSize = 16.sp
            )
        } else {
            state.poemItem?.let {
                Text(
                    text = it.verse1,
                    color = Color(state.colors.second),
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = it.verse2,
                    color = Color(state.colors.second),
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = "«${it.poet}»",
                    color = Color(state.colors.second),
                    fontSize = 14.sp
                )
            }
        }
        Spacer(modifier = Modifier.size(16.dp))

        ProgressButton(
            text = "شعری بگو",
            isLoading = state.isLoading,
            onClick = { onEvent(PoemEvent.RandomPoem) },
            color = state.colors.second
        )
    }
}
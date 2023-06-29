package korsi.sher.android.poem.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
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
            .padding(16.dp)
            .fillMaxSize()
    ) {

        if (state.poemItem == null) {
            Text(
                text = "در قدیم در منطقه (طالقان)، مردان دور کرسی جمع می شدند و مشاعره می کردند.. بعد از چند دور که اشعار در ذهن کم می شد، بعضی از خودشان شعر می گفتند! دیگران می گفتند که فلانی کرسی شعر گفت! (در لفظ: برو بابا بازم کرسی شعر گفتی)",
                textAlign = TextAlign.Right
            )
        } else {
            state.poemItem?.let {
                Text(text = it.verse1)
                Spacer(modifier = Modifier.size(16.dp))
                Text(text = it.verse2)
            }
        }
        Spacer(modifier = Modifier.size(16.dp))
        Button(onClick = { onEvent(PoemEvent.RandomPoem) }) {
            Text(text = "شعری بگو!")
        }
    }
}
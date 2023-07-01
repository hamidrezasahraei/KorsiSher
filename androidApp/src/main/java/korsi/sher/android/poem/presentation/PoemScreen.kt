package korsi.sher.android.poem.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
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
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(state.colors.first), Color(0xffdbd7d2))
                )
            )
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
                Card(
                    backgroundColor = Color(state.colors.first),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = it.verse1,
                            color = Color(state.colors.second),
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.size(16.dp))
                        Text(
                            text = it.verse2,
                            color = Color(state.colors.second),
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.size(16.dp))
                        Text(
                            text = "« ${it.poet} »",
                            color = Color(state.colors.second),
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.size(16.dp))

        ProgressButton(
            text = "شعری بگو",
            isLoading = state.isLoading,
            onClick = { onEvent(PoemEvent.RandomPoem) },
            color = MaterialTheme.colors.onBackground
        )
    }
}
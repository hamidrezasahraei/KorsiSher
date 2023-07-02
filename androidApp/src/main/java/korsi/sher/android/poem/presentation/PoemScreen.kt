package korsi.sher.android.poem.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import korsi.sher.android.core.presentation.components.PoemComponent
import korsi.sher.android.poem.presentation.components.ProgressButton
import korsi.sher.poem.presentation.poem.PoemEvent
import korsi.sher.poem.presentation.poem.PoemState

@Composable
fun PoemScreen(
    state: PoemState,
    onEvent: (PoemEvent) -> Unit,
    onLikedScreenClicked: () -> Unit
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
                PoemComponent(
                    verse1 = it.verse1,
                    verse2 = it.verse2,
                    poet = it.poet,
                    textColor = Color(state.colors.second),
                    onCopyClicked = { /*TODO*/ },
                    onFavoriteClicked = {
                        onEvent(PoemEvent.LikePoem(it))
                    }
                )
            }
        }
        Spacer(modifier = Modifier.size(16.dp))

        ProgressButton(
            text = "📝 یه بیت دیگه بگو",
            isLoading = state.isLoading,
            onClick = { onEvent(PoemEvent.RandomPoem) }
        )
        Button(
            onClick = {
                onLikedScreenClicked()
            },
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.DarkGray,contentColor = Color.White)
        ){
            Text(
                text = "❤️ قلبی‌ها ",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 14.sp
                )
            )
        }

    }
}
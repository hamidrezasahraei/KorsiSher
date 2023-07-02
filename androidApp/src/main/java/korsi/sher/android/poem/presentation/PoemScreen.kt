package korsi.sher.android.poem.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .border(width = 1.dp, color = Color.White, shape = RoundedCornerShape(8.dp))
                        .padding(32.dp)

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

                    Spacer(modifier = Modifier.size(32.dp))

                    Row {
                        IconButton(
                            onClick = {
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ContentCopy,
                                contentDescription = "Copy Poem",
                                tint = Color.White
                            )
                        }

                        Spacer(modifier = Modifier.size(8.dp))
                        IconButton(
                            onClick = {

                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.FavoriteBorder,
                                contentDescription = "Save Poem",
                                tint = Color.White
                            )
                        }
                    }

                }
            }
        }
        Spacer(modifier = Modifier.size(16.dp))

        ProgressButton(
            text = "شعری بگو",
            isLoading = state.isLoading,
            onClick = { onEvent(PoemEvent.RandomPoem) },
            color = Color(state.colors.second)
        )
    }
}
package korsi.sher.android.poem.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PoemComponent(
    verse1: String,
    verse2: String,
    poet: String,
    textColor: Color,
    onCopyClicked: () -> Unit,
    onFavoriteClicked: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .border(width = 1.dp, color = Color.White, shape = RoundedCornerShape(8.dp))
            .padding(32.dp)

    ) {
        Text(
            text = verse1,
            color = textColor,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = verse2,
            color = textColor,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = "« $poet »",
            color = textColor,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.size(32.dp))

        Row {
            IconButton(
                onClick = {
                    onCopyClicked()
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
                    onFavoriteClicked()
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
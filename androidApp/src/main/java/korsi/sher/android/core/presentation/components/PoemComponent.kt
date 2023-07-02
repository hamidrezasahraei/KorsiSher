package korsi.sher.android.core.presentation.components

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PoemComponent(
    verse1: String,
    verse2: String,
    poet: String,
    textColor: Color,
    poemCopyText: String,
    onFavoriteClicked: (() -> Unit)?
) {
    val context = LocalContext.current
    val clipboardManager: ClipboardManager = LocalClipboardManager.current

    var liked by remember {
        mutableStateOf(false)
    }
    // this is used to disable the ripple effect
    val interactionSource = remember {
        MutableInteractionSource()
    }
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
                    clipboardManager.setText(AnnotatedString(poemCopyText))
                    Toast.makeText(context, "کپی شد :)", Toast.LENGTH_SHORT).show()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ContentCopy,
                    contentDescription = "Copy Poem",
                    tint = Color.White
                )
            }
            if (onFavoriteClicked != null) {
                Spacer(modifier = Modifier.size(8.dp))


                Spacer(modifier = Modifier.size(8.dp))

                IconToggleButton(
                    checked = liked,
                    onCheckedChange = {
                        liked = it
                    }
                ) {
                    Icon(
                        imageVector = if (liked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite Item",
                        modifier = Modifier
                            .clickable(
                                indication = null, // assign null to disable the ripple effect
                                interactionSource = interactionSource
                            ) {
                                liked = !liked
                                onFavoriteClicked()
                            }
                            .size(24.dp),
                        tint = if (liked) Color.Red else Color.White
                    )
                }
            }
        }
    }
}
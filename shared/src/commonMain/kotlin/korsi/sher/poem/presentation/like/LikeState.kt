package korsi.sher.poem.presentation.like

import korsi.sher.poem.domain.history.PoemItem
import korsi.sher.poem.domain.poem.PoemError
import korsi.sher.poem.presentation.util.generateRandomColors

data class LikeState(
    val isLoading: Boolean = false,
    val likedPoems: List<PoemItem> = emptyList(),
    val error: PoemError? = null,
    val colors: Triple<Int, Int, Int> = generateRandomColors()
)
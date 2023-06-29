package korsi.sher.poem.presentation

import korsi.sher.poem.domain.history.PoemItem
import korsi.sher.poem.domain.poem.PoemError
import korsi.sher.poem.presentation.util.generateRandomColors

data class PoemState(
    val poemItem: PoemItem? = null,
    val isLoading: Boolean = false,
    val history: List<PoemItem> = emptyList(),
    val error: PoemError? = null,
    val colors: Triple<Int, Int, Int> = generateRandomColors()
)
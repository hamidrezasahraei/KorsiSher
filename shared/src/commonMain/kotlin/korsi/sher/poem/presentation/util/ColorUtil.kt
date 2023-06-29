package korsi.sher.poem.presentation.util

import kotlin.random.Random

fun generateRandomColors(): Triple<Int, Int, Int> {
    val backgroundColor = generateRandomColor()
    val textColor = getReadableTextColor(backgroundColor)
    return Triple(backgroundColor, textColor, textColor)
}

private fun generateRandomColor(): Int {
    val alpha = 255
    val red = Random.nextInt(256)
    val green = Random.nextInt(256)
    val blue = Random.nextInt(256)
    return (alpha shl 24) or (red shl 16) or (green shl 8) or blue
}

private fun getReadableTextColor(backgroundColor: Int): Int {
    val red = (backgroundColor shr 16) and 0xFF
    val green = (backgroundColor shr 8) and 0xFF
    val blue = backgroundColor and 0xFF

    val darkness = 1 - (0.299 * red + 0.587 * green + 0.114 * blue) / 255
    return if (darkness < 0.5) 0xFF000000.toInt() else 0xFFFFFFFF.toInt()
}
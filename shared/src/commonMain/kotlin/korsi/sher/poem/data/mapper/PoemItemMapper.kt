package korsi.sher.poem.data.mapper

import database.PoemEntity
import korsi.sher.poem.data.poem.PoemDto
import korsi.sher.poem.domain.history.PoemItem

fun PoemEntity.toPoemItem(): PoemItem {
    return PoemItem(
        id = id,
        verse1 = verse1,
        verse2 = verse2,
        poet = poet
    )
}

fun PoemDto.toPoemItem(): PoemItem {
    return PoemItem(
        id = "$verse1+$verse2".hashCode().toLong(),
        verse1 = verse1,
        verse2 = verse2,
        poet = poet
    )
}


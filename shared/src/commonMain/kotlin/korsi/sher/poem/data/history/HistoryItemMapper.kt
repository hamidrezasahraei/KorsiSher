package korsi.sher.poem.data.history

import database.PoemEntity
import korsi.sher.poem.domain.history.PoemItem

fun PoemEntity.toPoemItem(): PoemItem {
    return PoemItem(
        id = id,
        verse1 = verse1,
        verse2 = verse2,
        poet = poet
    )
}
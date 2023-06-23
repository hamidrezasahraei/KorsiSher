package korsi.sher.poem.domain.poem

import korsi.sher.poem.domain.history.PoemItem

interface PoemClient {
    suspend fun randomPoem(): PoemItem
}
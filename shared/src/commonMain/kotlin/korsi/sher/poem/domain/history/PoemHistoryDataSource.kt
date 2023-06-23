package korsi.sher.poem.domain.history

import korsi.sher.core.domain.util.CommonFlow

interface PoemHistoryDataSource {
    fun getHistory(): CommonFlow<List<PoemItem>>
    suspend fun insertHistoryItem(item: PoemItem)
}
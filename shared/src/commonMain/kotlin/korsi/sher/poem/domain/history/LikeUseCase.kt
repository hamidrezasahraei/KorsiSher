package korsi.sher.poem.domain.history

import korsi.sher.core.domain.util.Resource
import korsi.sher.poem.domain.history.PoemHistoryDataSource
import korsi.sher.poem.domain.history.PoemItem
import korsi.sher.poem.domain.poem.PoemException

class LikeUseCase(
    private val poemHistoryDataSource: PoemHistoryDataSource
) {

    suspend fun execute(poemItem: PoemItem): Resource<Unit> {
        return try {
            poemHistoryDataSource.insertHistoryItem(poemItem)
            Resource.Success(Unit)
        } catch (e: PoemException) {
            e.printStackTrace()
            Resource.Error(e)
        }
    }
}
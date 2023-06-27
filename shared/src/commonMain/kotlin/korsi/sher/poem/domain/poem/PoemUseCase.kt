package korsi.sher.poem.domain.poem

import korsi.sher.core.domain.util.Resource
import korsi.sher.poem.domain.history.PoemHistoryDataSource
import korsi.sher.poem.domain.history.PoemItem

class PoemUseCase(
    private val poemClient: PoemClient,
    private val poemHistoryDataSource: PoemHistoryDataSource
) {

    suspend fun execute(): Resource<PoemItem> {
        return try {
            val poemItem = poemClient.randomPoem()
            poemHistoryDataSource.insertHistoryItem(poemItem)
            Resource.Success(poemItem)
        } catch (e: PoemException) {
            e.printStackTrace()
            Resource.Error(e)
        }
    }
}
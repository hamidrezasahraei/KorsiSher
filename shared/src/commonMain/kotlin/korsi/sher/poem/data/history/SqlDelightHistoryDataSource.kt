package korsi.sher.poem.data.history

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import korsi.sher.core.domain.util.CommonFlow
import korsi.sher.core.domain.util.toCommonFlow
import korsi.sher.database.PoemDatabase
import korsi.sher.poem.data.mapper.toPoemItem
import korsi.sher.poem.domain.history.PoemHistoryDataSource
import korsi.sher.poem.domain.history.PoemItem
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock

class SqlDelightHistoryDataSource(
    db: PoemDatabase
) : PoemHistoryDataSource {

    private val queries = db.poemQueries

    override fun getHistory(): CommonFlow<List<PoemItem>> {
        return queries
            .getHistory()
            .asFlow()
            .mapToList()
            .map { poemEntity ->
                poemEntity.map { it.toPoemItem() }
            }
            .toCommonFlow()
    }

    override suspend fun insertHistoryItem(item: PoemItem) {
        queries.insertHistoryEntity(
            id = item.id,
            verse1 = item.verse1,
            verse2 = item.verse2,
            poet = item.poet,
            timestamp = Clock.System.now().toEpochMilliseconds()
        )
    }
}
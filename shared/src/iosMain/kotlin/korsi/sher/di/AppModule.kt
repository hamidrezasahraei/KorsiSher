package korsi.sher.di

import korsi.sher.database.PoemDatabase
import korsi.sher.poem.data.history.SqlDelightHistoryDataSource
import korsi.sher.poem.data.local.DatabaseDriverFactory
import korsi.sher.poem.data.poem.KtorPoemClient
import korsi.sher.poem.data.remote.HttpClientFactory
import korsi.sher.poem.domain.history.LikeUseCase
import korsi.sher.poem.domain.history.PoemHistoryDataSource
import korsi.sher.poem.domain.poem.PoemClient
import korsi.sher.poem.domain.poem.PoemUseCase

class AppModule {

    val poemHistoryDataSource: PoemHistoryDataSource by lazy {
        SqlDelightHistoryDataSource(
            PoemDatabase(
                DatabaseDriverFactory().create()
            )
        )
    }

    private val poemClient: PoemClient by lazy {
        KtorPoemClient(
            HttpClientFactory().create()
        )
    }

    val poemUseCase: PoemUseCase by lazy {
        PoemUseCase(poemClient, poemHistoryDataSource)
    }

    val likeUseCase: LikeUseCase by lazy {
        LikeUseCase(poemHistoryDataSource)
    }
}
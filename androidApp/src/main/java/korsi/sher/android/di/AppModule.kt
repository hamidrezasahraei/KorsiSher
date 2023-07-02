package korsi.sher.android.di

import android.app.Application
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import korsi.sher.database.PoemDatabase
import korsi.sher.poem.data.history.SqlDelightHistoryDataSource
import korsi.sher.poem.data.local.DatabaseDriverFactory
import korsi.sher.poem.data.poem.KtorPoemClient
import korsi.sher.poem.data.remote.HttpClientFactory
import korsi.sher.poem.domain.history.LikeUseCase
import korsi.sher.poem.domain.history.PoemHistoryDataSource
import korsi.sher.poem.domain.poem.PoemClient
import korsi.sher.poem.domain.poem.PoemUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClientFactory().create()
    }

    @Provides
    @Singleton
    fun providePoemClient(httpClient: HttpClient): PoemClient {
        return KtorPoemClient(httpClient)
    }

    @Provides
    @Singleton
    fun provideDatabaseDriver(app: Application): SqlDriver {
        return DatabaseDriverFactory(app).create()
    }

    @Provides
    @Singleton
    fun provideHistoryDataSource(sqlDriver: SqlDriver): PoemHistoryDataSource {
        return SqlDelightHistoryDataSource(PoemDatabase(sqlDriver))
    }

    @Provides
    @Singleton
    fun providePoemUseCase(
        poemClient: PoemClient,
        poemHistoryDataSource: PoemHistoryDataSource
    ): PoemUseCase {
        return PoemUseCase(
            poemClient = poemClient,
            poemHistoryDataSource = poemHistoryDataSource
        )
    }

    @Provides
    @Singleton
    fun provideLikeUseCase(
        poemHistoryDataSource: PoemHistoryDataSource
    ): LikeUseCase {
        return LikeUseCase(
            poemHistoryDataSource = poemHistoryDataSource
        )
    }
}
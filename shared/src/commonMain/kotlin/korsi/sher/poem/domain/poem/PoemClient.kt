package korsi.sher.poem.domain.poem

interface PoemClient {
    suspend fun randomPoem(): String
}
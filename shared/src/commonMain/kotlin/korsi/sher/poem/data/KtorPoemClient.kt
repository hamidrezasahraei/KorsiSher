package korsi.sher.poem.data

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.utils.io.errors.*
import korsi.sher.poem.data.NetworkConstants.RANDOM_POEM_URL
import korsi.sher.poem.domain.poem.PoemClient
import korsi.sher.poem.domain.poem.PoemError
import korsi.sher.poem.domain.poem.PoemException

class KtorPoemClient(
    private val httpClient: HttpClient //Will differ on Android and IOS but the logic is the same
): PoemClient {
    override suspend fun randomPoem(): String {
        val result = try {
            httpClient.get {
                url(RANDOM_POEM_URL)
                contentType(ContentType.Application.Json)
            }
        } catch (ioException: IOException) {
            throw PoemException(PoemError.SERVICE_UNAVAILABLE)
        }

        when(result.status.value) {
            in 200..299 -> Unit
            500 -> throw PoemException(PoemError.SERVER_ERROR)
            in 400..499 -> throw PoemException(PoemError.CLIENT_ERROR)
            else -> throw PoemException(PoemError.UNKNOWN_ERROR)
        }

        return try {
            result.body<PoemDto>().verse1
        } catch (e: Exception) {
            throw PoemException(PoemError.SERVER_ERROR)
        }
    }
}

object NetworkConstants {
    const val RANDOM_POEM_URL = "https://c.ganjoor.net/beyt-json.php"
}
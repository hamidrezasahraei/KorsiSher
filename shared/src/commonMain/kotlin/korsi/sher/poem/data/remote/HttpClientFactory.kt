package korsi.sher.poem.data.remote

import io.ktor.client.*

expect class HttpClientFactory {
    fun create(): HttpClient
}
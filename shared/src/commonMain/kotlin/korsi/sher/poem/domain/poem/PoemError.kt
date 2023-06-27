package korsi.sher.poem.domain.poem

enum class PoemError {
    SERVICE_UNAVAILABLE,
    CLIENT_ERROR,
    SERVER_ERROR,
    UNKNOWN_ERROR
}

class PoemException(val error: PoemError): Exception(
    "An error occurred when getting a poem: $error"
)
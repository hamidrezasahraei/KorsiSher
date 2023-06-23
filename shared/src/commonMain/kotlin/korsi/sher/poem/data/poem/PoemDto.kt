package korsi.sher.poem.data.poem

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class PoemDto(
    @SerialName("m1") val verse1: String,
    @SerialName("m2") val verse2: String,
    val poet: String,
    val url: String
)

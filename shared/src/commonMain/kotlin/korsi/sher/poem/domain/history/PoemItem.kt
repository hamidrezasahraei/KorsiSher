package korsi.sher.poem.domain.history

data class PoemItem(
    val id: Long?,
    val verse1: String,
    val verse2: String,
    val poet: String
) {
    fun getTextForShare(): String {
        return "$verse1\n$verse2\n« $poet »"
    }
}

package korsi.sher.poem.presentation.poem

import korsi.sher.poem.domain.history.PoemItem

sealed class PoemEvent {
    object RandomPoem: PoemEvent()
    data class SharePoem(val poem: PoemItem): PoemEvent()
    data class LikePoem(val poem: PoemItem): PoemEvent()
    object OnErrorSeen: PoemEvent()
}

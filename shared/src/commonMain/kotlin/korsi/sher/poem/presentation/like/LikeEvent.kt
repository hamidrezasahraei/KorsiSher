package korsi.sher.poem.presentation.like

import korsi.sher.poem.domain.history.PoemItem

sealed class LikeEvent {
    data class SharePoem(val poem: PoemItem): LikeEvent()
    object OnErrorSeen: LikeEvent()
}

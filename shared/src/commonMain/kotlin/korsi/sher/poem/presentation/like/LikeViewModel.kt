package korsi.sher.poem.presentation.like

import korsi.sher.core.domain.util.Resource
import korsi.sher.core.domain.util.toCommonStateFlow
import korsi.sher.poem.domain.history.LikeUseCase
import korsi.sher.poem.domain.history.PoemHistoryDataSource
import korsi.sher.poem.domain.history.PoemItem
import korsi.sher.poem.domain.poem.PoemException
import korsi.sher.poem.presentation.util.generateRandomColors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LikeViewModel(
    private val likeUseCase: LikeUseCase,
    private val poemHistoryDataSource: PoemHistoryDataSource,
    private val coroutineScope: CoroutineScope?
) {

    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(LikeState())
    val state = combine(
        _state,
        poemHistoryDataSource.getHistory()
    ) { state, likedPoems ->
        if (state.likedPoems != likedPoems) {
            state.copy(
                likedPoems = likedPoems,
                colors = generateRandomColors()
            )
        } else state
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), LikeState())
        .toCommonStateFlow()

    fun onEvent(event: LikeEvent) {
        when (event) {
            LikeEvent.OnErrorSeen -> {
                _state.update {
                    it.copy(
                        error = null
                    )
                }
            }
            is LikeEvent.SharePoem -> TODO()
        }
    }

    private fun unLikePoem(poemItem: PoemItem) {
        if (state.value.isLoading) {
            return
        }
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            when (val result = likeUseCase.execute(poemItem)) {
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            colors = generateRandomColors()
                        )
                    }
                }
                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = (result.throwable as? PoemException)?.error
                        )
                    }
                }
            }
        }
    }
}
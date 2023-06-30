package korsi.sher.poem.presentation

import korsi.sher.core.domain.util.Resource
import korsi.sher.core.domain.util.toCommonStateFlow
import korsi.sher.poem.domain.history.PoemHistoryDataSource
import korsi.sher.poem.domain.poem.PoemException
import korsi.sher.poem.domain.poem.PoemUseCase
import korsi.sher.poem.presentation.util.generateRandomColors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch

class PoemViewModel(
    private val poemUseCase: PoemUseCase,
    private val poemHistoryDataSource: PoemHistoryDataSource,
    private val coroutineScope: CoroutineScope?
) {

    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(PoemState())
    val state = combine(
        _state,
        poemHistoryDataSource.getHistory()
    ) { state, history ->
        if (state.history != history) {
            state.copy(
                history = history
            )
        } else state
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PoemState())
        .toCommonStateFlow()

    fun onEvent(event: PoemEvent) {
        when(event) {
            PoemEvent.OnErrorSeen -> {
                _state.update {
                    it.copy(
                        isLoading = true
                    )
                }
            }
            PoemEvent.RandomPoem -> {
                randomPoem()
            }
            is PoemEvent.SharePoem -> {
                TODO()
            }
        }
    }

    private fun randomPoem() {
        if (state.value.isLoading) {
            return
        }
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            when (val result = poemUseCase.execute()) {
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            poemItem = result.data,
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
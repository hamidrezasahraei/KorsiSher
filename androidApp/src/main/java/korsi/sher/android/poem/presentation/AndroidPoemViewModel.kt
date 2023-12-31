package korsi.sher.android.poem.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import korsi.sher.poem.domain.history.LikeUseCase
import korsi.sher.poem.domain.history.PoemHistoryDataSource
import korsi.sher.poem.domain.poem.PoemUseCase
import korsi.sher.poem.presentation.poem.PoemEvent
import korsi.sher.poem.presentation.poem.PoemViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidPoemViewModel @Inject constructor(
    private val poemUseCase: PoemUseCase,
    private val likeUseCase: LikeUseCase,
    private val poemHistoryDataSource: PoemHistoryDataSource
) : ViewModel() {

    private val viewModel by lazy {
        PoemViewModel(
            poemUseCase = poemUseCase,
            likeUseCase = likeUseCase,
            poemHistoryDataSource = poemHistoryDataSource,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state

    fun onEvent(event: PoemEvent) {
        viewModel.onEvent(event)
    }
}
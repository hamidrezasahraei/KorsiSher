package korsi.sher.android.like.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import korsi.sher.poem.domain.history.LikeUseCase
import korsi.sher.poem.domain.history.PoemHistoryDataSource
import korsi.sher.poem.presentation.like.LikeEvent
import korsi.sher.poem.presentation.like.LikeViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidLikeViewModel @Inject constructor(
    private val likeUseCase: LikeUseCase,
    private val poemHistoryDataSource: PoemHistoryDataSource
) : ViewModel() {

    private val viewModel by lazy {
        LikeViewModel(
            likeUseCase = likeUseCase,
            poemHistoryDataSource = poemHistoryDataSource,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state

    fun onEvent(event: LikeEvent) {
        viewModel.onEvent(event)
    }
}
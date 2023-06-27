package korsi.sher.android.poem.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import korsi.sher.poem.domain.history.PoemHistoryDataSource
import korsi.sher.poem.domain.poem.PoemUseCase
import korsi.sher.poem.presentation.PoemEvent
import korsi.sher.poem.presentation.PoemViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidPoemViewModel @Inject constructor(
    private val poemUseCase: PoemUseCase,
    private val poemHistoryDataSource: PoemHistoryDataSource
) : ViewModel() {

    private val viewModel by lazy {
        PoemViewModel(
            poemUseCase = poemUseCase,
            poemHistoryDataSource = poemHistoryDataSource,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state

    fun onEvent(event: PoemEvent) {
        viewModel.onEvent(event)
    }
}
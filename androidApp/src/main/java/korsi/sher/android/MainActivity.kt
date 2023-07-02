package korsi.sher.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import korsi.sher.android.core.presentation.Routes
import korsi.sher.android.like.presentation.AndroidLikeViewModel
import korsi.sher.android.like.presentation.LikeScreen
import korsi.sher.android.poem.presentation.AndroidPoemViewModel
import korsi.sher.android.poem.presentation.PoemScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    PoemRoot()
                }
            }
        }
    }
}

@Composable
fun PoemRoot() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.POEM
    ) {
        composable(route = Routes.POEM) {
            val viewModel = hiltViewModel<AndroidPoemViewModel>()
            val state by viewModel.state.collectAsState()
            PoemScreen(
                state = state,
                onEvent = viewModel::onEvent,
                onLikedScreenClicked = {
                    navController.navigate(Routes.LIKE)
                }
            )
        }
        composable(route = Routes.LIKE) {
            val viewModel = hiltViewModel<AndroidLikeViewModel>()
            val state by viewModel.state.collectAsState()
            LikeScreen(
                state = state,
                onEvent = viewModel::onEvent
            )
        }
    }
}

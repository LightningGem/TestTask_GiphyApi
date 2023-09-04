package com.example.giphy.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.giphy.presentation.screens.gif.GifScreen
import com.example.giphy.presentation.screens.gif.GifViewModel
import com.example.giphy.presentation.screens.trending_gifs.TrendingGifsScreen
import com.example.giphy.presentation.screens.trending_gifs.TrendingGifsViewModel
import com.example.giphy.presentation.theme.GiphyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GiphyTheme {

                val navController = rememberNavController()
                NavHost(
                    modifier = Modifier.background(MaterialTheme.colorScheme.surface),
                    navController = navController,
                    startDestination = "gifs",
                    enterTransition = { fadeIn(animationSpec = tween()) },
                    exitTransition = { fadeOut(animationSpec = tween()) }
                ) {
                    composable(route = "gifs") {
                        val viewModel : TrendingGifsViewModel = hiltViewModel()
                        TrendingGifsScreen(
                            pagingItems = viewModel.gifs.collectAsLazyPagingItems(),
                            onItemClick = { navController.navigate("gif/${it}") }
                        )
                    }

                    composable(route = "gif/{${GifViewModel.ID_KEY}}") {
                        val viewModel : GifViewModel = hiltViewModel()
                        val screenState = viewModel.screenState.collectAsState()
                        GifScreen(
                            state = screenState.value,
                            reload = viewModel::load,
                            navigateBack = { navController.navigateUp() }
                        )
                    }
                }
            }
        }
    }
}
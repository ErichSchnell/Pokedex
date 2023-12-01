package com.jereschneider.pokedex


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.jereschneider.pokedex.ui.navigation.PokedexNavGraph
import com.jereschneider.pokedex.ui.screens.details.DetailViewModel
import com.jereschneider.pokedex.ui.screens.home.HomeViewModel
import com.jereschneider.pokedex.ui.theme.PokedexTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val lastIndex = remember { mutableIntStateOf(1) }
            val homeViewModel = hiltViewModel<HomeViewModel>()
            val detailViewModel = hiltViewModel<DetailViewModel>()
            PokedexTheme {
                PokedexNavGraph(
                    navController = navController,
                    lastIndex = lastIndex.intValue,
                    onLastIndex = { lastIndex.intValue = it },
                    homeViewModel = homeViewModel,
                    detailViewModel = detailViewModel
                )
            }
        }
    }
}

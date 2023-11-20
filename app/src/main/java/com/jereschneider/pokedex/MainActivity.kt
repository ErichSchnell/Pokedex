package com.jereschneider.pokedex


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.jereschneider.pokedex.ui.navigation.PokedexNavGraph
import com.jereschneider.pokedex.ui.screens.home.HomeViewModel
import com.jereschneider.pokedex.ui.theme.PokedexTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val homeViewModel = hiltViewModel<HomeViewModel>()
            PokedexTheme {
                PokedexNavGraph(
                    navController = navController,
                    homeViewModel = homeViewModel
                )
            }
        }
    }
}

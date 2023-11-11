package com.jereschneider.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.jereschneider.pokedex.domain.models.HomeState
import com.jereschneider.pokedex.domain.models.PokemonModel
import com.jereschneider.pokedex.ui.screens.home.HomeScreen
import com.jereschneider.pokedex.ui.theme.PokedexTheme

class MainActivity : ComponentActivity() {
    val pokemons = listOf(
        PokemonModel(0, "Bulbasaur", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png", listOf("grass", "poison")),
        PokemonModel(1, "Ivysaur", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/2.png", listOf("grass", "poison")),
        PokemonModel(2, "Ventasaur", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/3.png", listOf("grass", "poison"))
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(HomeState.Success(pokemons))
                }
            }
        }
    }
}

package com.jereschneider.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.jereschneider.pokedex.domain.models.About
import com.jereschneider.pokedex.domain.models.DetailState
import com.jereschneider.pokedex.domain.models.PokemonDetailModel
import com.jereschneider.pokedex.domain.models.PokemonModel
import com.jereschneider.pokedex.ui.screens.details.DetailScreen
import com.jereschneider.pokedex.ui.theme.PokedexTheme

class MainActivity : ComponentActivity() {
    val pokemons = listOf(
        PokemonModel(0, "Bulbasaur", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png", listOf("grass", "poison")),
        PokemonModel(1, "Ivysaur", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/2.png", listOf("grass", "poison")),
        PokemonModel(2, "Ventasaur", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/3.png", listOf("grass", "poison"))
    )
    val pokemonModel = PokemonModel(
        id = 0,
        name = "bulbasaur",
        types = listOf("grass","poison"),
        urlImg = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
    )
    val about = About(
        species = "Seed",
        height = "2`3.6\" (0.70 cm)",
        weight = "11.2 lbs (6.9 kg)",
        abilities = "Overgrow, Chlorophyl",
        gender = "???",
        eggGroup = "Monster",
        eggCycle = "Grass"
    )
    val detailPokemon = PokemonDetailModel(pokemonModel, about)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    HomeScreen(HomeState.Success(pokemons))
                    DetailScreen(
                        state = DetailState.Success(detailPokemon),
                        onBackClick = {},
                        onSubscribe = {}
                    )
                }
            }
        }
    }
}

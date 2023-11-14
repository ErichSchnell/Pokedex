package com.jereschneider.pokedex


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.jereschneider.pokedex.data.datasources.PokemonDetailRemoteDataSource
import com.jereschneider.pokedex.data.datasources.PokemonListDataSource
import com.jereschneider.pokedex.data.network.BaseClient
import com.jereschneider.pokedex.data.repository.PokemonDetailRepository
import com.jereschneider.pokedex.data.repository.PokemonRawListRepository
import com.jereschneider.pokedex.domain.usecases.GetPokemonListUseCase
import com.jereschneider.pokedex.ui.screens.home.HomeScreen
import com.jereschneider.pokedex.ui.screens.home.HomeViewModel
import com.jereschneider.pokedex.ui.theme.PokedexTheme
import io.ktor.client.engine.okhttp.OkHttp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = HomeViewModel(
            GetPokemonListUseCase(
                pokemonRawListRepository = PokemonRawListRepository(
                    PokemonListDataSource(BaseClient(OkHttp.create()))),
                pokemonDetailRepository = PokemonDetailRepository(
                    PokemonDetailRemoteDataSource(BaseClient(OkHttp.create()))
                )
            )
        )
        setContent {
            PokedexTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(viewModel.state.value){
                    }
                }
            }
        }
    }
}

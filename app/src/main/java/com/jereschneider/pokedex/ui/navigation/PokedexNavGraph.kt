package com.jereschneider.pokedex.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jereschneider.pokedex.data.datasources.PokemonDetailRemoteDataSource
import com.jereschneider.pokedex.data.datasources.PokemonListDataSource
import com.jereschneider.pokedex.data.network.BaseClient
import com.jereschneider.pokedex.data.repository.PokemonDetailRepository
import com.jereschneider.pokedex.data.repository.PokemonRawListRepository
import com.jereschneider.pokedex.domain.models.DetailState
import com.jereschneider.pokedex.domain.models.PokemonDetailModel
import com.jereschneider.pokedex.domain.usecases.GetPokemonDetailUseCase
import com.jereschneider.pokedex.domain.usecases.GetPokemonListUseCase
import com.jereschneider.pokedex.ui.navigation.utils.navigateTo
import com.jereschneider.pokedex.ui.navigation.utils.parcelable
import com.jereschneider.pokedex.ui.screens.details.DetailScreen
import com.jereschneider.pokedex.ui.screens.details.DetailViewModel
import com.jereschneider.pokedex.ui.screens.home.HomeScreen
import com.jereschneider.pokedex.ui.screens.home.HomeViewModel
import io.ktor.client.engine.okhttp.OkHttp

@Composable
fun PokedexNavGraph(
    navController: NavHostController,
    initialRoute: String = PokedexNavigation.Home.route
) {
    NavHost(
        navController = navController,
        startDestination = initialRoute
    ) {
        Log.e("PokedexNavGraph: ", "CREATE VIEWMODEL")
        val viewModel = HomeViewModel(
            GetPokemonListUseCase(
                pokemonRawListRepository = PokemonRawListRepository(
                    PokemonListDataSource(BaseClient(OkHttp.create()))
                ),
                getPokemonDetailUseCase = GetPokemonDetailUseCase(
                    PokemonDetailRepository(
                        PokemonDetailRemoteDataSource(
                            BaseClient(OkHttp.create())
                        )
                    )
                )
            )
        )
        val viewModelDetail = DetailViewModel(
            GetPokemonDetailUseCase(
                PokemonDetailRepository(
                    PokemonDetailRemoteDataSource(
                        BaseClient(OkHttp.create())
                    )
                )
            )
        )
        composable(route = PokedexNavigation.Home.route) {
            Log.e("PokedexNavGraph: ", "CREO HOME")
            HomeScreen(
                homeViewModel = viewModel
            ) { namePokemon ->
                Log.e("PokedexNavGraph: ", "NAVEGO a $namePokemon")
                navController.navigateTo(
                    route = PokedexNavigation.Detail.route,
                    value = namePokemon
                )
            }
        }
        composable(route = PokedexNavigation.Detail.route) {
            Log.e("PokedexNavGraph: ", "CREO DETAIL")
            val pokemonName = navController.parcelable<String>() ?: ""

            DetailScreen(
                viewModel = viewModelDetail,
                pokemonName = pokemonName,
                onBackClick = { navController.popBackStack() },
                onSubscribe = {}
            )
        }
    }
}

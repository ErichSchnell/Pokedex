package com.jereschneider.pokedex.ui.navigation

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
import com.jereschneider.pokedex.domain.usecases.GetPokemonListUseCase
import com.jereschneider.pokedex.ui.screens.details.DetailScreen
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
        composable(route = PokedexNavigation.Home.route) {
            val viewModel = HomeViewModel(
                GetPokemonListUseCase(
                    pokemonRawListRepository = PokemonRawListRepository(
                        PokemonListDataSource(BaseClient(OkHttp.create()))
                    ),
                    pokemonDetailRepository = PokemonDetailRepository(
                        PokemonDetailRemoteDataSource(BaseClient(OkHttp.create()))
                    )
                )
            )
            HomeScreen(
                homeState = viewModel.state.value
            ){}
        }
        composable(route = PokedexNavigation.Detail.route) {
            val controller = navController
                .previousBackStackEntry
                ?.savedStateHandle
                ?.get<PokemonDetailModel>(PokeNavArgs.PokemonDetail.key)
            val state = controller?.let { DetailState.Success(it) } ?: DetailState.Error("Pokemon no encontrado")
            DetailScreen(state = state, onBackClick = { navController.popBackStack() }) {

            }
        }
    }
}

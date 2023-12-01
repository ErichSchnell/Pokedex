package com.jereschneider.pokedex.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jereschneider.pokedex.domain.models.PokemonDetailModel
import com.jereschneider.pokedex.ui.navigation.utils.navigateTo
import com.jereschneider.pokedex.ui.navigation.utils.parcelable
import com.jereschneider.pokedex.ui.screens.details.DetailContent
import com.jereschneider.pokedex.ui.screens.details.DetailViewModel
import com.jereschneider.pokedex.ui.screens.home.HomeScreen
import com.jereschneider.pokedex.ui.screens.home.HomeViewModel

@Composable
fun PokedexNavGraph(
    navController: NavHostController,
    initialRoute: String = PokedexNavigation.Home.route,
    lastIndex: Int,
    onLastIndex: (Int) -> Unit,
    homeViewModel: HomeViewModel,
    detailViewModel: DetailViewModel
) {
    NavHost(
        navController = navController,
        startDestination = initialRoute
    ) {
        composable(route = PokedexNavigation.Home.route) {
            HomeScreen(
                homeState = homeViewModel.state.collectAsState().value,
                lastIndex = lastIndex,
                onFetchMorePokemons = { homeViewModel.loadMorePokemons() },
                onLastIndex = { onLastIndex(it) },
                goToDetail = { pokemonDetail ->
                    navController.navigateTo(
                        route = PokedexNavigation.Detail.route,
                        value = pokemonDetail
                    )
                }
            )
        }
        composable(route = PokedexNavigation.Detail.route) {
            val detailPokemon = navController.parcelable<PokemonDetailModel>()
            detailPokemon?.let {
                val pokemon = it.copy(pokemon = it.pokemon.copy(isFav = true))
                DetailContent(
                    detailPokemon = detailPokemon,
                    onBackClick = { navController.popBackStack() },
                    onSubscribe = { detailViewModel.setFavourite(pokemon) },
                    onUnsubscribe = { detailViewModel.deleteFavourite(pokemon) }
                )
            }
        }
    }
}

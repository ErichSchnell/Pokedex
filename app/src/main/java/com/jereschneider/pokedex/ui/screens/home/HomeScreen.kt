package com.jereschneider.pokedex.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jereschneider.pokedex.R
import com.jereschneider.pokedex.domain.models.PokemonDetailModel
import com.jereschneider.pokedex.ui.models.ComponentState
import com.jereschneider.pokedex.ui.models.HomeState
import com.jereschneider.pokedex.ui.screens.common.ErrorView

@Composable
fun HomeScreen(
    homeState: HomeState,
    lastIndex: Int,
    onLastIndex: (Int) -> Unit,
    goToDetail: (PokemonDetailModel) -> Unit,
    onFetchMorePokemons: () -> Unit,
) {
    Column(Modifier.backgroundContent()) {
        Title()
        when (val pokeFavs = homeState.favPokemons) {
            ComponentState.Loading -> HomeContentLoadingFavPokemons()
            is ComponentState.Success -> HomeContentFavPokemons(pokeFavs.pokemons) { goToDetail(it) }
            is ComponentState.Error -> ErrorView(pokeFavs.message)
        }
        Spacer(modifier = Modifier.size(12.dp))
        when (val pokeList = homeState.listPokemons) {
            ComponentState.Loading -> HomeContentLoading()
            is ComponentState.Success -> HomeContent(
                pokemons = pokeList.pokemons,
                isLoading = homeState.isLoadingFetch,
                lastIndex = lastIndex,
                goToDetail = { goToDetail(it) },
                onFetchMorePokemons = { onFetchMorePokemons() },
                onlastIndex = { onLastIndex(it) }
            )

            is ComponentState.Error -> ErrorView(pokeList.message)
        }
    }
}

@Composable
private fun Title() = Text(
    modifier = Modifier
        .fillMaxWidth()
        .padding(top = 16.dp, start = 16.dp),
    text = stringResource(R.string.title),
    color = Color.White,
    fontSize = 36.sp,
    fontWeight = FontWeight.Bold
)

private fun Modifier.backgroundContent() = composed {
    background(
        brush = Brush.verticalGradient(
            colors = listOf(
                colorResource(id = R.color.black),
                colorResource(id = R.color.background),
            )
        )
    )
}
package com.jereschneider.pokedex.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jereschneider.pokedex.domain.models.About
import com.jereschneider.pokedex.domain.models.PokemonDetailModel
import com.jereschneider.pokedex.domain.models.PokemonModel
import com.jereschneider.pokedex.ui.components.ItemPokedex
import com.jereschneider.pokedex.ui.components.ShimmerBox


@Composable
fun HomeContent(
    pokemons: List<PokemonDetailModel>,
    isLoading: Boolean,
    lastIndex: Int,
    onlastIndex: (Int) -> Unit,
    goToDetail: (PokemonDetailModel) -> Unit,
    onFetchMorePokemons: () -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier.padding(top = 10.dp),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(
            count = pokemons.size,
            key = { pokemons[it].pokemon.id }
        ) { index ->
            ItemPokedex(
                pokemonDetailModel = pokemons[index],
                goToDetail = { goToDetail(pokemons[index]) }
            )
            if (lastIndex < index) onlastIndex(index)
        }
        if (isLoading) {
            item { ShimmerBox() }
            item { ShimmerBox() }
        }
    }

    LaunchedEffect(lastIndex) {
        if (lastIndex >= pokemons.size - 5) {
            onFetchMorePokemons()
        }
    }

}

@Preview(device = "id:pixel_7_pro")
@Composable
private fun HomeContentPreview() {
    val pokemons = listOf(
        PokemonDetailModel(
            PokemonModel(0, "Bulbasaur", "", listOf("grass", "poison"), false),
            About("", "", "", "", emptyList()),
        ),
        PokemonDetailModel(
            PokemonModel(1, "Ivysaur", "", listOf("grass", "poison"), false),
            About("", "", "", "", emptyList())
        ),
        PokemonDetailModel(
            PokemonModel(2, "Ventasaur", "", listOf("grass", "poison"), false),
            About("", "", "", "", emptyList()),
        )
    )
    HomeContent(
        pokemons = pokemons,
        isLoading = true,
        lastIndex = 0,
        goToDetail = {},
        onFetchMorePokemons = {},
        onlastIndex = {}
    )
}
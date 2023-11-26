package com.jereschneider.pokedex.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jereschneider.pokedex.R
import com.jereschneider.pokedex.domain.models.PokemonDetailModel
import com.jereschneider.pokedex.ui.components.ItemPokedex

@Composable
fun HomeContentFavPokemons(
    pokemons: List<PokemonDetailModel>,
    goToDetail: (PokemonDetailModel) -> Unit
) {
    if (pokemons.isNotEmpty()) {
        Column {
            Title()
            LazyRow(contentPadding = PaddingValues(8.dp)) {
                items(
                    count = pokemons.size,
                    key = { pokemons[it].pokemon.id }
                ) { index ->
                    ItemPokedex(
                        pokemonDetailModel = pokemons[index],
                        goToDetail = { goToDetail(pokemons[index]) }
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                }
            }
        }
    }
}

@Composable
private fun Title() = Text(
    modifier = Modifier
        .padding(start = 8.dp, top = 12.dp)
        .width(150.dp),
    text = stringResource(R.string.favourites),
    color = Color.White,
    fontFamily = FontFamily.SansSerif,
    fontSize = 18.sp,
    fontWeight = FontWeight.SemiBold
)

@Preview
@Composable
private fun HomeContentLoadingPokemonsPreview() = HomeContentLoadingFavPokemons()
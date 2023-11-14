package com.jereschneider.pokedex.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jereschneider.pokedex.domain.models.PokemonModel
import com.jereschneider.pokedex.ui.components.ItemPokedex


@Composable
fun HomeContent(
    pokemons: List<PokemonModel>,
    goToDetail: () -> Unit
    ){
    Column {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, start = 16.dp),
            text = "Pokedex",
            color = Color.Black,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
        )
        LazyVerticalGrid(
            modifier = Modifier.padding(top = 10.dp),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(
                count = pokemons.size,
                key = { pokemons[it].id }
            ) { index ->
                ItemPokedex(pokemonModel = pokemons[index]){ goToDetail() }
            }
        }
    }
}

@Preview(device = "id:pixel_7_pro")
@Composable
private fun HomeContentPreview(){
    val pokemons = listOf(
        PokemonModel(0, "Bulbasaur", "", listOf("grass", "poison")),
        PokemonModel(1, "Ivysaur", "", listOf("grass", "poison")),
        PokemonModel(2, "Ventasaur", "", listOf("grass", "poison"))
    )
    HomeContent(pokemons = pokemons){}
}

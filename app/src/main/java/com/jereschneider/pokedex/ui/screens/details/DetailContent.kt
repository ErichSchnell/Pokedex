package com.jereschneider.pokedex.ui.screens.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jereschneider.pokedex.domain.models.About
import com.jereschneider.pokedex.domain.models.PokemonDetailModel
import com.jereschneider.pokedex.domain.models.PokemonModel
import com.jereschneider.pokedex.ui.components.Toolbar

@Composable
fun DetailContent(
    detailPokemon: PokemonDetailModel,
    onBackClick: () -> Unit,
    onSubscribe: () -> Unit
){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Toolbar(onBackClick = { onBackClick() }, onSubscribe = { onSubscribe() })
        Title(text = detailPokemon.pokemon.name)
        Identificator(id = detailPokemon.pokemon.id.toString())
    }
}

@Composable
private fun Title(text: String){
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp),
        text = text,
        color = Color.White,
        fontSize = 42.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun Identificator(id: String){
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        text = id,
        color = Color.White,
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.End
    )
}

@Preview
@Composable
private fun DetailContentPreview() {
    val pokemonModel = PokemonModel(
        id = 1,
        name = "bulbasaur",
        types = listOf("grass","poison"),
        urlImg = ""
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
    DetailContent(detailPokemon = detailPokemon, onBackClick = { }, onSubscribe = {})
}
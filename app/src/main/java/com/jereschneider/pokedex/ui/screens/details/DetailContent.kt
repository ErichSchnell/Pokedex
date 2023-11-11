package com.jereschneider.pokedex.ui.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jereschneider.pokedex.R
import com.jereschneider.pokedex.domain.models.About
import com.jereschneider.pokedex.domain.models.PokemonDetailModel
import com.jereschneider.pokedex.domain.models.PokemonModel
import com.jereschneider.pokedex.ui.components.Chip
import com.jereschneider.pokedex.ui.components.Toolbar
import com.jereschneider.pokedex.ui.components.getBackgroundColor

@Composable
fun DetailContent(
    detailPokemon: PokemonDetailModel,
    onBackClick: () -> Unit,
    onSubscribe: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = detailPokemon.pokemon.getBackgroundColor())
    ) {
        Toolbar(onBackClick = { onBackClick() }, onSubscribe = { onSubscribe() })
        Title(text = detailPokemon.pokemon.name)
        Types(types = detailPokemon.pokemon.types)
        Identificator(id = detailPokemon.pokemon.id.toString())
        Box {
            DecorateImage()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 230.dp)
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .background(color = Color.White)
            ) {
                About()
                Separator()
                Details(detailPokemon.about)
            }
            PokeImage(url = detailPokemon.pokemon.urlImg)
        }
    }
}

@Composable
private fun DecorateImage(){
    Image(
        modifier = Modifier
            .padding(start = 180.dp)
            .width(250.dp)
            .height(310.dp),
        painter = painterResource(id = R.drawable.pokeball_icon),
        alpha = 0.2f,
        alignment = Alignment.CenterStart,
        contentScale = ContentScale.FillHeight,
        contentDescription = null
    )
}

@Composable
private fun Title(text: String){
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 6.dp),
        text = text.capitalize(Locale.current),
        color = Color.White,
        fontSize = 42.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun Types(types: List<String>){
    LazyRow(Modifier.padding(horizontal = 24.dp)) {
        items(types){
            Chip(title = it)
            Spacer(modifier = Modifier.size(12.dp))
        }
    }
}

@Composable
private fun Identificator(id: String){
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        text = id,
        color = Color.White,
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.End
    )
}

@Composable
private fun PokeImage(url: String){
    AsyncImage(
        modifier = Modifier
            .padding(top = 24.dp)
            .fillMaxWidth()
            .height(250.dp)
            .padding(horizontal = 50.dp),
        model = url,
        contentScale = ContentScale.Fit,
        contentDescription = null
    )
}

@Composable
private fun About(){
    Text(
        modifier = Modifier.padding(start = 24.dp, top = 32.dp, bottom = 12.dp),
        text = "About",
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun Separator(){
    Spacer(modifier = Modifier
        .fillMaxWidth()
        .height(1.dp)
        .padding(horizontal = 12.dp)
        .background(color = Color.LightGray)
    )
}

@Composable
private fun Details(detailAbout: About){
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)) {
        item{ DetailItem(title = "Species", value = detailAbout.species) }
        item{ DetailItem(title = "Height", value = detailAbout.height) }
        item{ DetailItem(title = "Weight", value = detailAbout.weight) }
        item{ DetailItem(title = "Abilities", value = detailAbout.abilities) }
        item { Spacer(Modifier.size(40.dp)) }
        item{ DetailItem(title = "Gender", value = detailAbout.gender) }
        item{ DetailItem(title = "Egg Groups", value = detailAbout.eggGroup) }
        item{ DetailItem(title = "Egg Cycle", value = detailAbout.eggCycle) }
    }
}

@Composable
private fun DetailItem(title: String, value: String){
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 12.dp)) {
        Text(
            text = title,
            color = Color.Gray
        )
        Spacer(Modifier.width(30.dp))
        Text(text = value)
    }
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
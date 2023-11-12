package com.jereschneider.pokedex.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jereschneider.pokedex.R
import com.jereschneider.pokedex.domain.models.PokemonModel

@Composable
fun ItemPokedex(modifier: Modifier = Modifier, pokemonModel: PokemonModel){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(RoundedCornerShape(14))
    ) {
        Row(Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1.2f)
                    .background(color = pokemonModel.getBackgroundColor()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = pokemonModel.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.size(8.dp))
                LazyColumn{
                    items(pokemonModel.types){
                        Chip(title = it)
                        Spacer(modifier = Modifier.size(4.dp))
                    }
                }
            }
            CustomImage(
                modifier = Modifier.weight(1f),
                pokemonModel = pokemonModel
            )
        }
    }
}

@Composable
private fun DecorateImage(){
    Image(
        modifier = Modifier.height(120.dp).scale(1.5f),
        painter = painterResource(id = R.drawable.pokeball_icon),
        alpha = 0.2f,
        alignment = Alignment.BottomStart,
        contentScale = ContentScale.Crop,
        contentDescription = null
    )
}

@Composable
private fun CustomImage(modifier: Modifier, pokemonModel: PokemonModel){
    val showShimmer = remember { mutableStateOf(true) }
    Box(modifier.background(color = pokemonModel.getBackgroundColor())){
        DecorateImage()
        AsyncImage(
            modifier = Modifier
                .shimmerEffect(showShimmer.value)
                .background(color = Color.Transparent)
                .padding(top = 24.dp)
                .fillMaxSize(),
            model = pokemonModel.urlImg,
            onSuccess = { showShimmer.value = false },
            contentDescription = null
        )
    }
}

@Preview
@Composable
private fun ItemPokedexPreview() {
    val pokemonModel = PokemonModel(
        id = 0,
        name = "bulbasaur",
        types = listOf("grass","poison"),
        urlImg = ""
    )
    ItemPokedex(pokemonModel = pokemonModel)
}
package com.jereschneider.pokedex.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jereschneider.pokedex.ui.components.shimmerEffect

@Composable
fun HomeContentLoadingFavPokemons() {
    Column {
        ShimmerText()
        Spacer(modifier = Modifier.size(12.dp))
        LazyRow {
            item { Spacer(modifier = Modifier.size(8.dp)) }
            item { ShimmerBox() }
            item { Spacer(modifier = Modifier.size(8.dp)) }
            item { ShimmerBox() }
            item { Spacer(modifier = Modifier.size(8.dp)) }
            item { ShimmerBox() }
            item { Spacer(modifier = Modifier.size(8.dp)) }
        }
    }
}

@Composable
private fun ShimmerText() = Box(
    modifier = Modifier
        .padding(8.dp)
        .width(150.dp)
        .height(50.dp)
        .clip(RoundedCornerShape(14))
        .shimmerEffect()
)

@Composable
private fun ShimmerBox() = Box(
    modifier = Modifier
        .width(200.dp)
        .height(150.dp)
        .clip(RoundedCornerShape(14))
        .shimmerEffect()
)

@Preview
@Composable
private fun HomeContentLoadingPokemonsPreview() = HomeContentLoadingFavPokemons()
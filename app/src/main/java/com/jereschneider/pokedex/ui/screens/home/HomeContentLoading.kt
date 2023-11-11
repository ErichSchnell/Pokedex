package com.jereschneider.pokedex.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jereschneider.pokedex.ui.components.shimmerEffect

@Composable
fun HomeContentLoading() {
    LazyVerticalGrid(
        modifier = Modifier.semantics { contentDescription = "Lista de pokemones" },
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        item{ Box(modifier = Modifier.height(150.dp).clip(RoundedCornerShape(14)).shimmerEffect()) }
        item{ Box(modifier = Modifier.height(150.dp).clip(RoundedCornerShape(14)).shimmerEffect()) }
        item{ Box(modifier = Modifier.height(150.dp).clip(RoundedCornerShape(14)).shimmerEffect()) }
        item{ Box(modifier = Modifier.height(150.dp).clip(RoundedCornerShape(14)).shimmerEffect()) }
        item{ Box(modifier = Modifier.height(150.dp).clip(RoundedCornerShape(14)).shimmerEffect()) }
        item{ Box(modifier = Modifier.height(150.dp).clip(RoundedCornerShape(14)).shimmerEffect()) }
        item{ Box(modifier = Modifier.height(150.dp).clip(RoundedCornerShape(14)).shimmerEffect()) }
        item{ Box(modifier = Modifier.height(150.dp).clip(RoundedCornerShape(14)).shimmerEffect()) }
    }
}

@Preview
@Composable
private fun HomeLoadingPreview() = HomeContentLoading()
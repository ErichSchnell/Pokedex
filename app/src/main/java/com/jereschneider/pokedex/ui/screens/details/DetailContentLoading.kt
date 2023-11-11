package com.jereschneider.pokedex.ui.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jereschneider.pokedex.R
import com.jereschneider.pokedex.ui.components.shimmerEffect

@Composable
fun DetailContentLoading(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Gray)
    ) {
        Spacer(modifier = Modifier.size(45.dp))
        Title()
        Types()
        Identificator()
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
                Details()
            }
            PokeImage()
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
private fun Title(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .padding(horizontal = 24.dp, vertical = 6.dp)
            .padding(end = 80.dp)
            .shimmerEffect(),
    )
}

@Composable
private fun Types(){
    LazyRow(Modifier.padding(horizontal = 24.dp, vertical = 8.dp)) {
        item{
            Box(Modifier
                .width(60.dp)
                .height(25.dp)
                .clip(CircleShape)
                .shimmerEffect())
        }
        item{ Spacer(modifier = Modifier.size(12.dp)) }
        item{
            Box(Modifier
                .width(60.dp)
                .height(25.dp)
                .clip(CircleShape)
                .shimmerEffect())
        }
    }
}

@Composable
private fun Identificator(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp)
            .padding(start = 230.dp)
            .padding(horizontal = 24.dp)
            .shimmerEffect(),
    )
}

@Composable
private fun PokeImage(){
    Box(
        modifier = Modifier
            .padding(top = 24.dp)
            .fillMaxWidth()
            .height(230.dp)
            .padding(horizontal = 80.dp)
            .clip(RoundedCornerShape(14.dp))
            .shimmerEffect(),
    )
}

@Composable
private fun About(){
    Box(
        modifier = Modifier
            .padding(start = 24.dp, top = 32.dp, bottom = 12.dp)
            .shimmerEffect(),
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
private fun Details(){
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)) {
        item{ DetailItem() }
        item{ DetailItem() }
        item{ DetailItem() }
        item{ DetailItem() }
        item { Spacer(Modifier.size(40.dp)) }
        item{ DetailItem() }
        item{ DetailItem() }
        item{ DetailItem() }
    }
}

@Composable
private fun DetailItem(){
    Box(
        Modifier
            .fillMaxWidth()
            .height(45.dp)
            .padding(top = 12.dp)
            .shimmerEffect()
    )
}

@Preview
@Composable
private fun DetailContentPreview() = DetailContentLoading()

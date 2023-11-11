package com.jereschneider.pokedex.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Chip(title: String){
    Box(modifier = Modifier
        .clip(CircleShape)
        .background(color = Color.White.copy(alpha = 0.2f))
    ){
        Text(
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 18.dp),
            text = title,
            color = Color.White
        )
    }
}
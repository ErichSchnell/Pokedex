package com.jereschneider.pokedex.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jereschneider.pokedex.R

@Composable
fun Toolbar(
    onBackClick: () -> Unit,
    onSubscribe: (() -> Unit)? = null
){
    Row {
        IconButton(
            modifier = Modifier.padding(horizontal = 12.dp),
            onClick = { onBackClick() }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                tint = Color.White,
                contentDescription = stringResource(R.string.toolbar_start_icon_description)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        onSubscribe?.let { onClick ->
            IconButton(
                modifier = Modifier.padding(12.dp),
                onClick = { onClick() }
            ) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    tint = Color.White,
                    contentDescription = stringResource(R.string.toolbar_end_icon_description)
                )
            }
        }
    }
}

@Preview
@Composable
private fun ToolbarPreview() = Toolbar(onBackClick = {}, onSubscribe = {})
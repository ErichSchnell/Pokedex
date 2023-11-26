package com.jereschneider.pokedex.ui.components

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import com.jereschneider.pokedex.domain.models.PokemonModel

fun Modifier.shimmerEffect(
    isShow: Boolean = true,
    baseColor: Color = Color(0xFFB8B5B5),
    transitionColor: Color = Color(0xFF8F8B8B)
): Modifier = composed {
    if (isShow) {
        var size by remember { mutableStateOf(IntSize.Zero) }
        val transition = rememberInfiniteTransition(label = "")
        val startOffsetX by transition.animateFloat(
            initialValue = -2 * size.width.toFloat(),
            targetValue = 2 * size.width.toFloat(),
            animationSpec = infiniteRepeatable(
                animation = tween(1000)
            ), label = ""
        )
        background(
            brush = Brush.linearGradient(
                colors = listOf(
                    baseColor,
                    transitionColor,
                    baseColor,
                ),
                start = Offset(startOffsetX, 0f),
                end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
            )
        ).onGloballyPositioned { size = it.size }
    } else {
        background(color = Color.Transparent)
    }
}

fun PokemonModel.getBackgroundColor(): Color {
    types.apply {
        return when {
            containerOrFalse("grass") -> Color(71, 209, 177)
            containerOrFalse("poison") -> Color(71, 209, 177)
            containerOrFalse("fire") -> Color(251, 108, 108)
            containerOrFalse("flying") -> Color(251, 108, 108)
            containerOrFalse("water") -> Color(118, 191, 254)
            containerOrFalse("electric") -> Color(255, 216, 111)
            containerOrFalse("bug") -> Color(191, 142, 75, 255)
            contains("flying") -> Color(144, 186, 201, 255)
            else -> Color(160, 160, 160, 255)
        }
    }
}

fun List<String>.containerOrFalse(value: String) = firstOrNull()?.contains(value) ?: false
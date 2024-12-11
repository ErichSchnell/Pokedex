package com.jereschneider.pokedex.ui.components

import android.text.TextPaint
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jereschneider.pokedex.domain.models.PokemonModel
import com.jereschneider.pokedex.ui.theme.StatColorAttack
import com.jereschneider.pokedex.ui.theme.StatColorDefense
import com.jereschneider.pokedex.ui.theme.StatColorHp
import com.jereschneider.pokedex.ui.theme.StatColorSpecialAttack
import com.jereschneider.pokedex.ui.theme.StatColorSpecialDefense
import com.jereschneider.pokedex.ui.theme.StatColorSpeed

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
            containerOrFalse("electric") -> Color(255, 216, 111)
            containerOrFalse("bug") -> Color(191, 142, 75, 255)
            containerOrFalse("ground") -> Color(191, 142, 75, 255)
            containerOrFalse("psychic") -> Color(178, 139, 179, 255)
            contains("fairy") -> Color(118, 191, 254)
            contains("water") -> Color(118, 191, 254)
            contains("flying") -> Color(144, 186, 201, 255)
            else -> Color(160, 160, 160, 255)
        }
    }
}

fun List<String>.containerOrFalse(value: String) = firstOrNull()?.contains(value) ?: false

sealed class StateColor(val color: Color) {
    object HP: StateColor(StatColorHp)
    object ATTACK: StateColor(StatColorAttack)
    object DEFENSE: StateColor(StatColorSpecialAttack)
    object SPECIAL_ATTACK: StateColor(StatColorDefense)
    object SPECIAL_DEFENSE: StateColor(StatColorSpecialDefense)
    object SPEED: StateColor(StatColorSpeed)
}

fun DrawScope.createHexagonPath(center: Offset, radiusMax: Float): Path {
    val statsName = listOf("hp","attack","special-attack","speed","special-defense","defense")
    val textPaint = TextPaint().apply {
        color = Color.Black.toArgb()
        textSize = 12.sp.toPx()
        isAntiAlias = true
    }

    val path = Path()
    val angleStep = Math.PI / 3 // 60 grados en radianes
    val startAngle = -Math.PI / 2 // Iniciar con una punta hacia arriba (90° en radianes)

    if (radiusMax.toInt() % 50 != 0) return path
    val times = radiusMax.toInt() / 50

    for (i in 1 .. times){
        val radius = i * 50
        var xInit = 0f
        var yInit = 0f
        for (j in 0..5) {
            val angle = startAngle + angleStep * j
            val x = (center.x + radius * Math.cos(angle)).toFloat()
            val y = (center.y + radius * Math.sin(angle)).toFloat()
            if (j == 0) {
                xInit = x
                yInit = y
                path.moveTo(center.x, center.y)
                path.lineTo(x, y)

            } else {
                path.lineTo(x, y)
                path.moveTo(center.x, center.y)
                path.lineTo(x, y)
            }

            if (i == times){
                val xText = (center.x + (radius * 1.1) * Math.cos(angle)).toFloat()
                val yText = (center.y + (radius * 1.1) * Math.sin(angle)).toFloat()
                val text = statsName[j]
                val textWidth = textPaint.measureText(text)
                drawContext.canvas.nativeCanvas.drawText(text, xText- (textWidth/2), yText, textPaint)
            }
        }
        path.lineTo(xInit, yInit)
    }
    path.close()
    return path
}

fun DrawScope.drawStatsPath(
    center: Offset,
    hp: Long,
    attack: Long,
    defense: Long,
    specialAttack: Long,
    specialDefense: Long,
    speed: Long,
): Path {
    val path = Path()
    val angleStep = Math.PI / 3 // 60 grados en radianes
    val startAngle = -Math.PI / 2 // Iniciar con una punta hacia arriba (90° en radianes)
    val radius = listOf(
        hp.toFloat() * 1.5f,
        attack.toFloat() * 1.5f,
        specialAttack.toFloat() * 1.5f,
        speed.toFloat() * 1.5f,
        specialDefense.toFloat() * 1.5f,
        defense.toFloat() * 1.5f,
    )
    radius.forEachIndexed { index, rd ->
        val angle = startAngle + angleStep * index
        val x = (center.x + rd * Math.cos(angle)).toFloat()
        val y = (center.y + rd * Math.sin(angle)).toFloat()
        if (index == 0) {
            path.moveTo(x, y)

        } else {
            path.lineTo(x, y)
        }
    }
    path.close()
    return path
}
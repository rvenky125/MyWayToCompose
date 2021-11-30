package com.famas.mywaytocompose.presentation.screens.screen_progress.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.famas.mywaytocompose.presentation.screens.screen_progress.util.ProgressPieStyle
import com.famas.mywaytocompose.presentation.screens.screen_progress.util.ProgressValues
import kotlinx.coroutines.launch

@Composable
fun ProgressPie(
    modifier: Modifier = Modifier,
    values: List<ProgressValues>,
    animate: Boolean = true,
    style: ProgressPieStyle = ProgressPieStyle.Stroke
) {
    val total = remember {
        values.map { it.value }.sum()
    }

    val animatableSweepAngles = remember {
        values.map { Animatable(0f) }
    }

    var startAngle = remember { 0f }

    BoxWithConstraints(modifier = modifier.size(100.dp)) {
        val width = maxWidth
        Canvas(modifier = Modifier.fillMaxSize()) {
            values.forEachIndexed { index, prValue ->
                drawArc(
                    color = prValue.color,
                    startAngle = startAngle,
                    sweepAngle = animatableSweepAngles[index].value,
                    useCenter = style == ProgressPieStyle.Full,
                    style = when (style) {
                        ProgressPieStyle.Stroke -> Stroke(
                            width = width.times(0.20f).value
                        )
                        ProgressPieStyle.Full -> Fill
                    }
                )
                val sweep = (prValue.value.div(total) * 360f)
                startAngle += sweep
            }
        }
    }

    LaunchedEffect(key1 = Unit, block = {
        animatableSweepAngles.forEachIndexed { index, animatable ->
            launch {
                val sweep = (values[index].value.div(total) * 360f)
                if (animate) animatable.animateTo(
                    sweep,
                    animationSpec = tween(durationMillis = 1000)
                ) else animatable.snapTo(sweep)
            }
        }
    })
}
package com.famas.mywaytocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.famas.mywaytocompose.ui.theme.*
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyWayToComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val progressValues = remember {
                        listOf(
                            ProgressValues(50f, progressColor1),
                            ProgressValues(90f, progressColor2),
                            ProgressValues(120f, progressColor3),
                            ProgressValues(70f, progressColor4),
                            ProgressValues(100f, progressColor5)
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .scrollable(rememberScrollState(), orientation = Orientation.Vertical),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "animate=true")
                        Spacer(modifier = Modifier.height(10.dp))
                        ProgressPie(modifier = Modifier.size(200.dp), values = progressValues)
                        Spacer(modifier = Modifier.height(20.dp))
                        ProgressPie(
                            modifier = Modifier.size(200.dp),
                            values = progressValues,
                            style = ProgressPieStyle.Full
                        )
                        Spacer(modifier = Modifier.height(20.dp))

                        Text(text = "animate=false")
                        Spacer(modifier = Modifier.height(10.dp))
                        ProgressPie(
                            modifier = Modifier.size(200.dp),
                            values = progressValues,
                            style = ProgressPieStyle.Full,
                            animate = false
                        )
                    }
                }
            }
        }
    }
}


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

data class ProgressValues(
    val value: Float,
    val color: Color
)

enum class ProgressPieStyle { Full, Stroke }
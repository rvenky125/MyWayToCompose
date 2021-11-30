package com.famas.mywaytocompose.presentation.screens.screen_progress

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.famas.mywaytocompose.presentation.screens.screen_progress.components.ProgressPie
import com.famas.mywaytocompose.presentation.screens.screen_progress.util.ProgressPieStyle
import com.famas.mywaytocompose.presentation.screens.screen_progress.util.ProgressValues
import com.famas.mywaytocompose.presentation.ui.theme.*

@Composable
fun ProgressScreen() {
    val progressValues = remember {
        listOf(
            ProgressValues(50f, progressColor1),
            ProgressValues(90f, progressColor2),
            ProgressValues(120f, progressColor3),
            ProgressValues(70f, progressColor4),
            ProgressValues(100f, progressColor5)
        )
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
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
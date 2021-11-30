package com.famas.mywaytocompose.presentation.screens.screen_progress.util

import androidx.compose.ui.graphics.Color

data class ProgressValues(
    val value: Float,
    val color: Color
)

enum class ProgressPieStyle { Full, Stroke }
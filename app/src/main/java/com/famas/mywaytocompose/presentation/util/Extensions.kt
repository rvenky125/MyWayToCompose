package com.famas.mywaytocompose.presentation.util

import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics
import android.util.TypedValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.util.*

fun Dp.toPx(): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.value,
        Resources.getSystem().displayMetrics
    )
}

@Composable
fun Float.toDp(): Dp {
    return with(LocalDensity.current) { toDp() }
}

fun Float.toDp(context: Context): Dp {
    return (this / context.resources.displayMetrics.density).dp
}

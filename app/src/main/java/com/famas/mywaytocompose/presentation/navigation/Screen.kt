package com.famas.mywaytocompose.presentation.navigation

import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String,
    val getRoute: (String) -> String = { route }
) {
    object ProgressScreen : Screen(
        route = "_progress_screen",
        title = "Progress Components"
    )

    object MainScreen : Screen(
        route = "_main_screen",
        title = "Main Screen"
    )

    object DropDowns : Screen(
        route = "_drop_downs_screen",
        title = "DropDown Components"
    )
}
package com.famas.mywaytocompose.presentation.navigation

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.famas.mywaytocompose.presentation.screens.screen_drop_downs.DropViewsScreen
import com.famas.mywaytocompose.presentation.screens.screen_main.ListOfScreens
import com.famas.mywaytocompose.presentation.screens.screen_progress.ProgressScreen

@Composable
fun MyNavGraph(
    navController: NavHostController,
    scaffoldState: ScaffoldState
) {
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(Screen.MainScreen.route) {
            ListOfScreens(navController = navController)
        }

        composable(Screen.ProgressScreen.route) {
            ProgressScreen()
        }

        composable(Screen.DropDowns.route) {
            DropViewsScreen()
        }
    }
}
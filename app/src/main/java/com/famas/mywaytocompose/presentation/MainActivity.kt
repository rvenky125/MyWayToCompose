package com.famas.mywaytocompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.famas.mywaytocompose.presentation.navigation.MyNavGraph
import com.famas.mywaytocompose.presentation.screens.screen_progress.components.ProgressPie
import com.famas.mywaytocompose.presentation.ui.theme.*
import com.famas.mywaytocompose.presentation.screens.screen_progress.util.ProgressPieStyle
import com.famas.mywaytocompose.presentation.screens.screen_progress.util.ProgressValues
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyWayToComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    val scaffoldState = rememberScaffoldState()

                    Scaffold(scaffoldState = scaffoldState, modifier = Modifier.fillMaxSize()) {
                        MyNavGraph(navController = navController, scaffoldState = scaffoldState)
                    }
                }
            }
        }
    }
}
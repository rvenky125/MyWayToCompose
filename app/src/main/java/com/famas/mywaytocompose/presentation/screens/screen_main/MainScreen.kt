package com.famas.mywaytocompose.presentation.screens.screen_main

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.famas.mywaytocompose.presentation.util.UiEvent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ListOfScreens(
    viewModel: MainScreenViewModel = hiltViewModel(),
    navController: NavController
) {

    LaunchedEffect(key1 = Unit, block = {
        viewModel.uiEventFlow.collectLatest {
            when (it) {
                is UiEvent.Navigate -> {
                    navController.navigate(it.route)
                }
                is UiEvent.ShowSnackbar -> TODO()
            }
        }
    })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        viewModel.listOfScreens.forEach {
            Button(
                onClick = { viewModel.onEvent(MainScreenEvent.OnScreenBtnClick(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(text = it.title)
            }
        }
    }
}
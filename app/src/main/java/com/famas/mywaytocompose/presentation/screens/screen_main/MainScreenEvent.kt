package com.famas.mywaytocompose.presentation.screens.screen_main

import com.famas.mywaytocompose.presentation.navigation.Screen

sealed class MainScreenEvent {
    data class OnScreenBtnClick(val screen: Screen) : MainScreenEvent()
}
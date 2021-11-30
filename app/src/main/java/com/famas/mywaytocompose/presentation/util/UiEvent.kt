package com.famas.mywaytocompose.presentation.util

sealed class UiEvent {
    data class ShowSnackbar(val message: String): UiEvent()
    data class Navigate(val route: String) : UiEvent()
}

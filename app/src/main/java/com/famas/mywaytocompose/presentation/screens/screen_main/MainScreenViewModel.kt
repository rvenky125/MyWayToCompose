package com.famas.mywaytocompose.presentation.screens.screen_main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.famas.mywaytocompose.presentation.navigation.Screen
import com.famas.mywaytocompose.presentation.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(

) : ViewModel() {

    private val _uiEventFlow = MutableSharedFlow<UiEvent>()
    val uiEventFlow = _uiEventFlow.asSharedFlow()

    val listOfScreens = listOf(
        Screen.ProgressScreen,
        Screen.DropDowns
    )

    fun onEvent(event: MainScreenEvent) {
        viewModelScope.launch {
            when(event) {
                is MainScreenEvent.OnScreenBtnClick -> {
                    _uiEventFlow.emit(UiEvent.Navigate(event.screen.route))
                }
            }
        }
    }

}
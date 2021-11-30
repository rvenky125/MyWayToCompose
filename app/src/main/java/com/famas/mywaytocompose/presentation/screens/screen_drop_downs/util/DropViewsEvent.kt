package com.famas.mywaytocompose.presentation.screens.screen_drop_downs.util

sealed class DropViewsEvent {
    data class OnEmployeeSelected(val index: Int?) : DropViewsEvent()
    data class OnAlphabetsSelected(val index: Int?) : DropViewsEvent()
    data class OnStudentSelected(val index: Int?) : DropViewsEvent()
    data class OnEmployeeTextValue(val value: String) : DropViewsEvent()
    data class OnAlphabetsTextValue(val value: String) : DropViewsEvent()
}
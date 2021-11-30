package com.famas.mywaytocompose.presentation.screens.screen_drop_downs.util

data class DropDownPickerState<T>(
    val list: List<T>,
    val selectedIndex: Int? = null,
)

data class DropDownTextFieldPickerState<T>(
    val fieldValue: String,
    val list: List<T>,
    val selectedIndex: Int? = null,
)


package com.famas.mywaytocompose.presentation.screens.screen_drop_downs.util

sealed class DropType {
    object DropUp : DropType()
    object DropDown : DropType()
}
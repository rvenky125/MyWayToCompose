package com.famas.mywaytocompose.presentation.screens.screen_drop_downs.util

data class DropViewsState(
    val studentDropDown: DropDownPickerState<String> = DropDownPickerState(
        list = listOf(
            "A",
            "B",
            "C",
            "D",
            "E",
            "F",
            "G",
            "H",
            "I",
            "J",
            "K",
            "L",
            "M",
            "N",
            "O",
            "P",
            "Q",
            "R",
        )
    ),

    val employeeDropDown: DropDownTextFieldPickerState<String> = DropDownTextFieldPickerState(
        fieldValue = "",
        list = listOf(
            "emp1",
            "emp2",
            "emp3",
            "emp4",
            "emp5",
            "ABC",
            "BCD",
            "CDE",
            "DEF",
            "EFG",
            "FGH",
        )
    ),

    val alphabets: DropDownTextFieldPickerState<String> = DropDownTextFieldPickerState(
        fieldValue = "",
        list = listOf(
            "ABC",
            "BCD",
            "CDE",
            "DEF",
            "EFG",
            "FGH",
            "GHI",
            "HIJ",
            "IJK",
            "JKL",
        )
    )
)
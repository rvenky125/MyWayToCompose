package com.famas.mywaytocompose.presentation.screens.screen_drop_downs

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.famas.mywaytocompose.presentation.screens.screen_drop_downs.components.DropDown
import com.famas.mywaytocompose.presentation.screens.screen_drop_downs.components.DropTextField
import com.famas.mywaytocompose.presentation.screens.screen_drop_downs.util.DropType
import com.famas.mywaytocompose.presentation.screens.screen_drop_downs.util.DropViewsEvent

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DropViewsScreen(viewModel: DropViewsViewModel = hiltViewModel()) {
    val state = viewModel.dropViewsState.value
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        DropDown(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            heading = "Please select student",
            hint = "Student",
            dropDownItems = state.studentDropDown.list,
            selectedIndex = state.studentDropDown.selectedIndex,
            onItemSelected = { viewModel.onEvent(DropViewsEvent.OnStudentSelected(it)) }
        )
        Spacer(modifier = Modifier.height(20.dp))

        DropTextField(
            value = state.employeeDropDown.fieldValue,
            onValueChange = { viewModel.onEvent(DropViewsEvent.OnEmployeeTextValue(it)) },
            dropDownItems = state.employeeDropDown.list,
            selectedIndex = state.employeeDropDown.selectedIndex,
            onItemSelected = { viewModel.onEvent(DropViewsEvent.OnEmployeeSelected(it)) },
            heading = "Please select employee",
            hint = "Employee",
            modifier = Modifier.fillMaxWidth(),
            dropType = DropType.DropDown
        )
        Spacer(modifier = Modifier.height(30.dp))

        DropTextField(
            value = state.alphabets.fieldValue,
            onValueChange = { viewModel.onEvent(DropViewsEvent.OnAlphabetsTextValue(it)) },
            dropDownItems = state.alphabets.list,
            selectedIndex = state.alphabets.selectedIndex,
            onItemSelected = { viewModel.onEvent(DropViewsEvent.OnAlphabetsSelected(it)) },
            heading = "Please select alphabets",
            hint = "Alphabets",
            modifier = Modifier.fillMaxWidth(),
            dropType = DropType.DropUp
        )
    }
}
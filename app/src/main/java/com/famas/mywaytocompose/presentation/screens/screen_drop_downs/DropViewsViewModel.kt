package com.famas.mywaytocompose.presentation.screens.screen_drop_downs

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.famas.mywaytocompose.presentation.screens.screen_drop_downs.util.DropViewsEvent
import com.famas.mywaytocompose.presentation.screens.screen_drop_downs.util.DropViewsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DropViewsViewModel @Inject constructor() : ViewModel() {

    private val _dropViewsState = mutableStateOf(DropViewsState())
    val dropViewsState: State<DropViewsState> = _dropViewsState

    fun onEvent(event: DropViewsEvent) {
        viewModelScope.launch {
            when (event) {
                is DropViewsEvent.OnEmployeeTextValue -> {
                    _dropViewsState.value = dropViewsState.value.copy(
                        employeeDropDown = dropViewsState.value.employeeDropDown.copy(
                            fieldValue = event.value
                        )
                    )

                    //TODO: You can change the list from server like this
                    _dropViewsState.value = dropViewsState.value.copy(
                        employeeDropDown = dropViewsState.value.employeeDropDown.copy(
                            list = DropViewsState().employeeDropDown.list.filter { it.contains(
                                dropViewsState.value.employeeDropDown.fieldValue,
                                true
                            ) }
                        )
                    )
                }

                is DropViewsEvent.OnStudentSelected -> {
                    _dropViewsState.value = dropViewsState.value.copy(
                        studentDropDown = dropViewsState.value.studentDropDown.copy(
                            selectedIndex = event.index
                        )
                    )
                }
                is DropViewsEvent.OnEmployeeSelected -> {
                    _dropViewsState.value = dropViewsState.value.copy(
                        employeeDropDown = dropViewsState.value.employeeDropDown.copy(
                            selectedIndex = event.index
                        )
                    )
                }
                is DropViewsEvent.OnAlphabetsSelected -> {
                    _dropViewsState.value = dropViewsState.value.copy(
                        alphabets = dropViewsState.value.alphabets.copy(
                            selectedIndex = event.index
                        )
                    )
                }
                is DropViewsEvent.OnAlphabetsTextValue -> {
                    _dropViewsState.value = dropViewsState.value.copy(
                        alphabets = dropViewsState.value.alphabets.copy(
                            fieldValue = event.value
                        )
                    )

                    //TODO: You can change the list from server like this
                    _dropViewsState.value = dropViewsState.value.copy(
                        alphabets = dropViewsState.value.alphabets.copy(
                            list = DropViewsState().alphabets.list.filter { it.contains(
                                dropViewsState.value.alphabets.fieldValue,
                                true
                            ) }
                        )
                    )
                }
            }
        }
    }

}
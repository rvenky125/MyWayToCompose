package com.famas.mywaytocompose.presentation.screens.screen_drop_downs.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.famas.mywaytocompose.presentation.screens.screen_drop_downs.util.DropType
import com.famas.mywaytocompose.presentation.util.toDp
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@OptIn(ExperimentalComposeUiApi::class)
@ExperimentalAnimationApi
@Composable
fun DropTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    dropDownItems: List<String>,
    selectedIndex: Int?,
    onItemSelected: (Int?) -> Unit,
    hint: String,
    dropType: DropType = DropType.DropUp,
    textStyle: TextStyle = MaterialTheme.typography.subtitle1.copy(color = MaterialTheme.colors.primary),
    heading: String? = null
) {
    val showPopUp = rememberSaveable { mutableStateOf(false) }
    val coroutine = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused = interactionSource.collectIsFocusedAsState().value
    val rotation = if (isFocused) 180f else 0f
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    var offset by remember {
        mutableStateOf(IntOffset.Zero)
    }

    Column(modifier = modifier) {
        heading?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier.align(Alignment.Start)
            )
        }

        //We are using showPopUp value here to hide the content
        AnimatedVisibility(visible = (selectedIndex == null && isFocused) && showPopUp.value) {
            Popup(offset = offset, onDismissRequest = { showPopUp.value = false }) {
                Card(
                    modifier = Modifier
                        .width(textFieldSize.width.toDp())
                        .heightIn(max = 200.dp)
                        .padding(vertical = 16.dp)
                        .onSizeChanged {
                            offset = offset.copy(
                                y = if (dropType is DropType.DropUp) -it.height else textFieldSize.height.roundToInt()
                            )
                        },
                    elevation = 8.dp
                ) {
                    LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        itemsIndexed(dropDownItems) { index, s ->
                            DropdownMenuItem(
                                onClick = {
                                    coroutine.launch {
                                        onItemSelected(index)
                                        focusManager.clearFocus(true)
                                        showPopUp.value = false
                                        onValueChange(s)
                                    }
                                },
                                enabled = selectedIndex != index
                            ) {
                                Text(text = s)
                            }
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = value,
            onValueChange = {
                if (!showPopUp.value) showPopUp.value = true
                onValueChange(it)
                if (selectedIndex != null) onItemSelected(null)
            },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned {
                    textFieldSize = it.size.toSize()
                }
                .focusRequester(focusRequester)
                .onFocusChanged {
                    if (it.isFocused) {
                        if (!showPopUp.value) showPopUp.value = true
                    }
                },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "drop down menu",
                    modifier = Modifier.graphicsLayer(
                        rotationZ = animateFloatAsState(
                            targetValue = rotation,
                            animationSpec = tween(800)
                        ).value
                    ),

                    tint = contentColorFor(backgroundColor = MaterialTheme.colors.surface).copy(0.8f)
                )
            },
            label = {
                Text(text = hint)
            },
            interactionSource = interactionSource,
            textStyle = textStyle
        )
    }
}
package com.famas.mywaytocompose.presentation.screens.screen_drop_downs.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.Popup
import com.famas.mywaytocompose.presentation.screens.screen_drop_downs.util.DropType
import com.famas.mywaytocompose.presentation.util.toDp
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


@ExperimentalAnimationApi
@Composable
fun DropDown(
    modifier: Modifier = Modifier,
    dropDownItems: List<String>,
    selectedIndex: Int?,
    onItemSelected: (Int) -> Unit,
    heading: String,
    hint: String,
    inactiveIndicatorColor: Color = Color.LightGray,
    indicatorColor: Color = MaterialTheme.colors.primary,
    indicatorWidth: Dp = 5.dp,
    shape: Shape = MaterialTheme.shapes.small
) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    val rotation = if (expanded) 180f else 0f
    var rowSize by remember {
        mutableStateOf(Size.Zero)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Text(
            text = heading,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.align(Alignment.Start)
        )
        Row(
            modifier
                .clip(shape)
                .background(color = MaterialTheme.colors.primary.copy(0.2f))
                .clickable { expanded = true }
                .padding(6.dp)
                .onGloballyPositioned {
                    rowSize = it.size.toSize()
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = selectedIndex?.let { dropDownItems[it] } ?: hint,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(8.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "drop down menu",
                modifier = Modifier.graphicsLayer(
                    rotationZ = animateFloatAsState(
                        targetValue = rotation,
                        animationSpec = tween(800)
                    ).value
                ),
                tint = contentColorFor(backgroundColor = MaterialTheme.colors.surface).copy(0.6f)
            )
        }

        AnimatedVisibility(visible = expanded) {
            Popup(
                onDismissRequest = { expanded = false }
            ) {
                val scrollState = rememberScrollState()
                var lazyColumnSize by remember { mutableStateOf(Size.Zero) }
                val indicatorHeight = (scrollState.value.toFloat() / scrollState.maxValue.toFloat())

                println("indicator height $indicatorHeight")

                Card(
                    modifier = Modifier
                        .width(rowSize.width.toDp())
                        .padding(vertical = 16.dp),
                    elevation = 8.dp
                ) {
                    Row {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .heightIn(max = (rowSize.height * 8).toDp())
                                .onGloballyPositioned {
                                    lazyColumnSize = it.size.toSize()
                                }
                                .verticalScroll(scrollState)
                        ) {
                            dropDownItems.forEachIndexed { index, s ->
                                DropdownMenuItem(
                                    onClick = {
                                        onItemSelected(index)
                                        expanded = false
                                    },
                                    enabled = selectedIndex != index
                                ) {
                                    Text(text = s)
                                }
                            }
                        }

                        Box(modifier = Modifier
                            .width(indicatorWidth)
                            .height(lazyColumnSize.height.toDp())
                            .background(
                                color = inactiveIndicatorColor,
                                shape = MaterialTheme.shapes.small
                            )
                        ) {
                            Box(modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(indicatorHeight)
                                .background(
                                    color = indicatorColor,
                                    shape = MaterialTheme.shapes.small
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}
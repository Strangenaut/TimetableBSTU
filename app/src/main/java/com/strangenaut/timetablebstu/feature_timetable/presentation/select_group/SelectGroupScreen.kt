package com.strangenaut.timetablebstu.feature_timetable.presentation.select_group

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.strangenaut.timetablebstu.R
import com.strangenaut.timetablebstu.feature_timetable.presentation.core.SharedViewModel
import com.strangenaut.timetablebstu.feature_timetable.presentation.core.theme.isDarkThemeEnabled
import com.strangenaut.timetablebstu.feature_timetable.presentation.select_group.components.GroupTitle

@Composable
fun SelectGroupScreen(
    navController: NavController,
    viewModel: SelectGroupViewModel,
    sharedViewModel: SharedViewModel
) {
    val state = viewModel.state.value

    var groupsNumbers by remember {
        mutableStateOf(state.groupsNumbers)
    }

    var typedGroup by remember {
        mutableStateOf("")
    }

    if (state.selectedGroup.isNotEmpty()) {
        sharedViewModel.setSelectedGroup(state.selectedGroup)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Image(
                    painter = painterResource(
                        id = if(isDarkThemeEnabled)
                            R.drawable.ic_baseline_west_dark_theme_24
                        else
                            R.drawable.ic_baseline_west_light_theme_24
                    ),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            navController.navigateUp()
                        }
                )
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .clip(MaterialTheme.shapes.medium)
                        .background(MaterialTheme.colors.surface)
                ) {
                    var isHintVisible by remember {
                        mutableStateOf(true)
                    }

                    BasicTextField(
                        value = typedGroup,
                        onValueChange = { typedValue ->
                            typedGroup = typedValue
                            groupsNumbers = state.groupsNumbers
                            groupsNumbers = groupsNumbers.filter {
                                typedGroup
                                    .replace(" ", "")
                                    .uppercase() in it
                            }
                        },
                        singleLine = true,
                        textStyle = MaterialTheme.typography.body1,
                        cursorBrush = Brush.verticalGradient(
                            0.00f to Color.Transparent,
                            0.35f to Color.Transparent,
                            0.35f to MaterialTheme.colors.onSurface,
                            0.90f to MaterialTheme.colors.onSurface,
                            0.90f to Color.Transparent,
                            1.00f to Color.Transparent
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .onFocusChanged {
                                isHintVisible = !it.isFocused
                            }
                    )
                    if(isHintVisible) {
                        Text(
                            text = stringResource(R.string.search),
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.onSecondary,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    }
                }
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.primary)
        ) {
            items(1) {
                Text(
                    text = stringResource(R.string.groups),
                    softWrap = true,
                    fontSize = MaterialTheme.typography.h1.fontSize,
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier.padding(16.dp)
                )
            }
            items(1) {
                if (groupsNumbers.isEmpty()) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(R.string.no_groups_found),
                            textAlign = TextAlign.Center,
                            softWrap = true,
                            fontSize = MaterialTheme.typography.h2.fontSize,
                            color = MaterialTheme.colors.onSecondary,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
            items(groupsNumbers.size) { i ->
                GroupTitle(
                    title = groupsNumbers[i],
                    onClick = {
                        viewModel.onEvent(SelectGroupEvent.SelectGroup(groupsNumbers[i]))
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}
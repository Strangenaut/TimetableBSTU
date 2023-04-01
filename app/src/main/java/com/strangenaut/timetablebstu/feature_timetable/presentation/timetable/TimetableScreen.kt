package com.strangenaut.timetablebstu.feature_timetable.presentation.timetable

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.strangenaut.timetablebstu.R
import com.strangenaut.timetablebstu.feature_timetable.domain.model.Timetable
import com.strangenaut.timetablebstu.feature_timetable.presentation.core.Screen
import com.strangenaut.timetablebstu.feature_timetable.presentation.core.SharedViewModel
import com.strangenaut.timetablebstu.feature_timetable.presentation.core.filterGroupLessonsByWeekCode
import com.strangenaut.timetablebstu.feature_timetable.presentation.timetable.components.*

@Composable
fun TimetableScreen(
    navController: NavController,
    viewModel: TimetableViewModel,
    sharedViewModel: SharedViewModel,
    appContext: Context
) {
    val state = viewModel.state.value

    var selectedGroupNumber by remember {
        mutableStateOf("")
    }


    var bottomBarHeightDp by remember {
        mutableStateOf(0)
    }

    val scrollState = rememberScrollState()

    val coroutineScope = rememberCoroutineScope()

    sharedViewModel.selectedGroup.observeForever { groupNumber ->
        selectedGroupNumber = groupNumber
    }

    Scaffold(
        topBar = {
            TopBar(
                currentWeekState = when (state.currentWeekCode) {
                    1 -> stringResource(R.string.odd)
                    2 -> stringResource(R.string.even)
                    else -> stringResource(R.string.wait)
                },
                checked = state.isCurrentWeekOnlyVisible,
                onCheckedChange = {
                    viewModel.onEvent(TimetableEvent.ToggleCurrentWeekOnlyVisibility)
                },
                selectedGroupNumber = selectedGroupNumber.ifEmpty {
                    stringResource(R.string.group)
                },
                onChooseGroup = {
                    navController.navigate(Screen.GetGroupTimetableScreen.route)
                }
            )
        },
        bottomBar = {
            val noConnectionString = stringResource(R.string.no_connection)

            BottomBar(
                onClickToday = {
                    viewModel.onEvent(
                        TimetableEvent.ScrollToCurrentWeekDay(scrollState, coroutineScope)
                    )
                },
                onClickUpdate = {
                    if (!viewModel.hasInternetConnection()) {
                        Toast.makeText(appContext, noConnectionString, Toast.LENGTH_SHORT).show()
                        return@BottomBar
                    }

                    state.timetable = Timetable()
                    viewModel.onEvent(TimetableEvent.LoadTimetable(true))
                },
                modifier = Modifier.onSizeChanged { bottomBarHeightDp = it.height }
            )
        }
    ) {
        if (state.timetable.groups.isEmpty() &&
            viewModel.timetableJsonFileDoesNotExist &&
            !state.isConnectionAvailable
        ) {
            NoConnection(
                appContext = appContext,
                onReconnect = {
                    viewModel.onEvent(TimetableEvent.LoadTimetable(false))
                },
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.primary)
            )
        } else {
            Spacer(modifier = Modifier.height(2.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.primary)
                    .padding(
                        bottom =
                        (bottomBarHeightDp.toFloat() /
                                LocalContext.current.resources.displayMetrics.density).dp
                    )
                    .verticalScroll(scrollState)
                    .animateContentSize()
            ) {
                LoadingTimetableShimmer(
                    isLoading = state.timetable.groups.isEmpty(),
                    modifier = Modifier.padding(horizontal = 40.dp),
                    contentAfterLoading = {
                        if (selectedGroupNumber.isEmpty()) {
                            NoGroupSelected(
                                onChooseGroup = {
                                    navController.navigate(Screen.GetGroupTimetableScreen.route)
                                }
                            )
                            return@LoadingTimetableShimmer
                        }

                        val group = state.timetable.groups.find { it.number == selectedGroupNumber }
                        val groupFiltered = group?.takeIf { state.isCurrentWeekOnlyVisible }?.run {
                            this.filterGroupLessonsByWeekCode(state.currentWeekCode)
                        } ?: group

                        groupFiltered?.days?.forEach { day ->
                            DaySection(
                                currentWeekCode = state.currentWeekCode,
                                day = day,
                                modifier = Modifier.onSizeChanged {
                                    viewModel.updateDayContentSize(day.title, it.height)
                                }
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                )
            }
        }
    }
}
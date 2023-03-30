package com.strangenaut.timetablebstu.feature_timetable.presentation.timetable

import android.content.Context
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

//    val scrollStates = mutableMapOf<String, ScrollState>()

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
                selectedGroupNumber = selectedGroupNumber.ifEmpty { "........." },
                onChooseGroup = {
                    navController.navigate(Screen.GetGroupTimetableScreen.route)
                }
            )
        },
        bottomBar = {
            BottomBar(
                onClickToday = {
//                    TODO: onClickToday
//                    viewModel.viewModelScope.launch {
//                        val currentDay = when(LocalDate.now().dayOfWeek.name) {
//                            "MONDAY" -> "Понедельник"
//                            "TUESDAY" -> "Вторник"
//                            "WEDNESDAY" -> "Среда"
//                            "THURSDAY" -> "Четверг"
//                            "FRIDAY" -> "Пятница"
//                            "SATURDAY" -> "Суббота"
//                            "SUNDAY" -> "Воскресенье"
//                            else -> "N/D"
//                        }
//                        val scrollState = scrollStates[currentDay] ?: return@launch
//                            scrollState.scrollTo(scrollState.value)
//                    }
                },
                onClickUpdate = {
                    if (state.timetable.groups.isNotEmpty()) {
                        state.timetable = Timetable()
                        viewModel.onEvent(TimetableEvent.LoadTimetable(true))
                    }
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
                onReconnect = { viewModel.onEvent(TimetableEvent.LoadTimetable(false)) },
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.primary)
            )
        } else {
            Spacer(modifier = Modifier.height(2.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.primary)
                    .padding(
                        bottom =
                        (bottomBarHeightDp.toFloat() /
                                LocalContext.current.resources.displayMetrics.density).dp
                    )
                    .verticalScroll(rememberScrollState())
            ) {
                ShimmerLoading(
                    isLoading = state.timetable.groups.isEmpty(),
                    modifier = Modifier.padding(horizontal = 40.dp),
                    contentAfterLoading = {
                        if (selectedGroupNumber.isEmpty()) {
                            NoGroupChosen(
                                onChooseGroup = {
                                    navController.navigate(Screen.GetGroupTimetableScreen.route)
                                }
                            )
                            return@ShimmerLoading
                        }

                        val group = state.timetable.groups.find { it.number == selectedGroupNumber }
                        val groupFiltered = group?.takeIf { state.isCurrentWeekOnlyVisible }?.run {
                            copy(days = days.map { day ->
                                day.copy(lessons = day.lessons.filter {
                                    it.weekCode == state.currentWeekCode
                                })
                            }.filter { day ->
                                day.lessons.isNotEmpty()
                            })
                        } ?: group

                        groupFiltered?.days?.forEach {
                            DaySection(
                                currentWeekCode = state.currentWeekCode,
                                day = it
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                )
            }
        }
    }
}
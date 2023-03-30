package com.strangenaut.timetablebstu.feature_timetable.presentation.core

sealed class Screen(val route: String) {
    object TimetableScreen: Screen("timetable_screen")
    object GetGroupTimetableScreen: Screen("get_group_timetable_screen")
}
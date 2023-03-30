package com.strangenaut.timetablebstu.feature_timetable.presentation.get_group_timetable

data class GetGroupTimetableState (
    val groupsNumbers: List<String> = listOf(),
    val selectedGroup: String = ""
)
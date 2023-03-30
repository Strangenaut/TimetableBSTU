package com.strangenaut.timetablebstu.feature_timetable.presentation.get_group_timetable

sealed class GetGroupTimetableEvent {
    object GetGroupsList: GetGroupTimetableEvent()
    data class SelectGroup(val selectedGroup: String): GetGroupTimetableEvent()
}
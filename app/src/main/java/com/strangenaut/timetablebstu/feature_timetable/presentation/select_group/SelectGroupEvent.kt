package com.strangenaut.timetablebstu.feature_timetable.presentation.select_group

sealed class SelectGroupEvent {
    object GetGroupsList: SelectGroupEvent()
    data class SelectGroup(val selectedGroup: String): SelectGroupEvent()
}
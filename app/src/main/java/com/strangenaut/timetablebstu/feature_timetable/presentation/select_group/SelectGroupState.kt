package com.strangenaut.timetablebstu.feature_timetable.presentation.select_group

data class SelectGroupState (
    val groupsNumbers: List<String> = listOf(),
    val selectedGroup: String = ""
)
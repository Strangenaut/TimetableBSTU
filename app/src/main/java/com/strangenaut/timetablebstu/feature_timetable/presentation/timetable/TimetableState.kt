package com.strangenaut.timetablebstu.feature_timetable.presentation.timetable

import com.strangenaut.timetablebstu.feature_timetable.domain.model.Timetable

data class TimetableState(
    var timetable: Timetable = Timetable(),
    val currentWeekCode: Int = 0,
    val isCurrentWeekOnlyVisible: Boolean = false,
    val isConnectionAvailable: Boolean = false,
    val daysContentSizeList: MutableMap<String, Int> = mutableMapOf()
)
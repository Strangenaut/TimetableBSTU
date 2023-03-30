package com.strangenaut.timetablebstu.feature_timetable.presentation.timetable

sealed class TimetableEvent {
    data class LoadTimetable(val fromNetworkOnly: Boolean): TimetableEvent()
    object GetCurrentWeekCode: TimetableEvent()
    object ToggleCurrentWeekOnlyVisibility: TimetableEvent()
}
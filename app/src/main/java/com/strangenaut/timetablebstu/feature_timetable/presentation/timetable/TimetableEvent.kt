package com.strangenaut.timetablebstu.feature_timetable.presentation.timetable

import androidx.compose.foundation.ScrollState
import kotlinx.coroutines.CoroutineScope

sealed class TimetableEvent {
    data class LoadTimetable(val fromNetworkOnly: Boolean): TimetableEvent()
    object GetCurrentWeekCode: TimetableEvent()
    data class ScrollToCurrentWeekDay(
        val scrollState: ScrollState,
        val uiThreadScope: CoroutineScope
    ) : TimetableEvent()
    object ToggleCurrentWeekOnlyVisibility: TimetableEvent()
}
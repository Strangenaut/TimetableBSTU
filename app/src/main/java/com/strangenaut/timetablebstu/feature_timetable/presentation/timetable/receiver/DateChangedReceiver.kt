package com.strangenaut.timetablebstu.feature_timetable.presentation.timetable.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.strangenaut.timetablebstu.feature_timetable.presentation.timetable.TimetableEvent
import com.strangenaut.timetablebstu.feature_timetable.presentation.timetable.TimetableViewModel

class DateChangedReceiver(
    private val viewModel: TimetableViewModel
) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.action
        if (Intent.ACTION_DATE_CHANGED == action){
            viewModel.onEvent(TimetableEvent.GetCurrentWeekCode)
        }
    }
}
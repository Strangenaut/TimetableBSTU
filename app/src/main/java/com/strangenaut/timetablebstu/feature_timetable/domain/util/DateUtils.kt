package com.strangenaut.timetablebstu.feature_timetable.domain.util

import com.strangenaut.timetablebstu.feature_timetable.domain.model.Day
import java.time.LocalDate

class DateUtils {
    companion object {
        val currentWeekDay: String
            get() = when(LocalDate.now().dayOfWeek.name) {
                "MONDAY" -> "Понедельник"
                "TUESDAY" -> "Вторник"
                "WEDNESDAY" -> "Среда"
                "THURSDAY" -> "Четверг"
                "FRIDAY" -> "Пятница"
                "SATURDAY" -> "Суббота"
                "SUNDAY" -> "Воскресенье"
                else -> "N/D"
            }

        fun isToday(day: Day): Boolean {
            return day.title == currentWeekDay
        }
    }
}
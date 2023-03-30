package com.strangenaut.timetablebstu.feature_timetable.presentation.core

import com.strangenaut.timetablebstu.feature_timetable.domain.model.Group

fun Group.filterGroupLessonsByWeekCode(
    currentWeekCode: Int,
): Group {
    return this.copy( days = this.days.map { day ->
        val lessonsList = day.lessons.filter { lesson ->
            lesson.weekCode == currentWeekCode
        }
        day.copy(lessons = lessonsList)
    }.filter { day ->
        day.lessons.isNotEmpty()
    })
}
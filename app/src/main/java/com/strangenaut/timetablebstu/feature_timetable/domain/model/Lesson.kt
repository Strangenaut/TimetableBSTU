package com.strangenaut.timetablebstu.feature_timetable.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Lesson(
    val weekCode: Int = 0,
    val timeBegin: String = "",
    val timeEnd: String = "",
    val discipline: String = "",
    val lessonType: LessonType = LessonType.UNIDENTIFIED,
    val lecturers: String = "",
    val classroom: String = "",
)
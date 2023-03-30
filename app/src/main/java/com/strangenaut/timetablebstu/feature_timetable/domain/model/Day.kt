package com.strangenaut.timetablebstu.feature_timetable.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Day(
    val title: String,
    val lessons: List<Lesson> = emptyList()
)
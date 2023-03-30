package com.strangenaut.timetablebstu.feature_timetable.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Group(
    val number: String = "",
    val days: List<Day> = emptyList()
)
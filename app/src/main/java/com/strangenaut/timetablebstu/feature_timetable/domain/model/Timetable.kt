package com.strangenaut.timetablebstu.feature_timetable.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Timetable(
    val startDate: String = "",
    val groups: List<Group> = emptyList()
)
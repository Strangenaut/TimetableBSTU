package com.strangenaut.timetablebstu.feature_timetable.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class DayDTO(

    @SerialName("@Title")
    var title: String = "",

    @SerialName("GroupLessons")
    val groupLessonsJson: JsonElement
)
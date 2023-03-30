package com.strangenaut.timetablebstu.feature_timetable.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class LessonDTO(

    @SerialName("WeekCode")
    val weekCode: Int = 0,

    @SerialName("Time")
    val time: String = "",

    @SerialName("Discipline")
    val discipline: String = "",

    @SerialName("Lecturers")
    val lecturersJson: JsonElement? = null,

    @SerialName("Classroom")
    var classroom: String? = null
)
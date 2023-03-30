package com.strangenaut.timetablebstu.feature_timetable.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class GroupDTO(

    @SerialName("@Number")
    val number: String = "",

    @SerialName("Days")
    val daysJson: JsonElement? = null
)
package com.strangenaut.timetablebstu.feature_timetable.data.util

import com.strangenaut.timetablebstu.feature_timetable.data.model.DayDTO
import com.strangenaut.timetablebstu.feature_timetable.data.model.GroupDTO
import com.strangenaut.timetablebstu.feature_timetable.domain.model.Group
import com.strangenaut.timetablebstu.feature_timetable.domain.util.DomainMapper
import kotlinx.serialization.json.*

class GroupDTOMapper : DomainMapper<GroupDTO, Group> {

    override fun mapToDomainModel(model: GroupDTO): Group {
        val json = Json { ignoreUnknownKeys = true }
        val days = model.daysJson?.jsonObject?.values?.flatMap { jsonElement ->
            when (jsonElement) {
                is JsonArray -> jsonElement.jsonArray
                else -> listOf(jsonElement)
            }.map { json.decodeFromJsonElement<DayDTO>(it) }
        }?.map { DayDTOMapper().mapToDomainModel(it) }

        return Group(
            number = model.number,
            days = days ?: emptyList()
        )
    }
}
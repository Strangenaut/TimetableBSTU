package com.strangenaut.timetablebstu.feature_timetable.data.util

import com.strangenaut.timetablebstu.feature_timetable.data.model.GroupDTO
import com.strangenaut.timetablebstu.feature_timetable.data.model.TimetableDTO
import com.strangenaut.timetablebstu.feature_timetable.domain.model.Timetable
import com.strangenaut.timetablebstu.feature_timetable.domain.util.DomainMapper
import kotlinx.serialization.json.*

class TimetableDTOMapper : DomainMapper<TimetableDTO, Timetable> {

    override fun mapToDomainModel(model: TimetableDTO): Timetable {
        val json = Json { ignoreUnknownKeys = true }
        val groups = model.groupsJson?.jsonObject?.values?.flatMap { jsonElement ->
            when (jsonElement) {
                is JsonArray -> jsonElement.jsonArray
                else -> listOf(jsonElement)
            }.map { json.decodeFromJsonElement<GroupDTO>(it) }
        }?.map { GroupDTOMapper().mapToDomainModel(it) }

        val period = model.groupsJson?.jsonObject?.get("Period")?.jsonObject
        val startDateString = listOf(
            period?.get("@StartYear"),
            period?.get("@StartMonth"),
            period?.get("@StartDay")
        ).joinToString("-") {
            it.toString()
                .replace("\"", "")
                .padStart(2, '0')
        }

        return Timetable(
            startDate = startDateString,
            groups = groups ?: emptyList()
        )
    }
}
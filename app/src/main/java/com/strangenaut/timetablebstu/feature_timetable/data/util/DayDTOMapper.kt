package com.strangenaut.timetablebstu.feature_timetable.data.util

import com.strangenaut.timetablebstu.feature_timetable.data.model.DayDTO
import com.strangenaut.timetablebstu.feature_timetable.data.model.LessonDTO
import com.strangenaut.timetablebstu.feature_timetable.domain.model.Day
import com.strangenaut.timetablebstu.feature_timetable.domain.util.DomainMapper
import kotlinx.serialization.json.*

class DayDTOMapper : DomainMapper<DayDTO, Day> {

    override fun mapToDomainModel(model: DayDTO): Day {
        val json = Json { ignoreUnknownKeys = true }
        val lessons = model.groupLessonsJson.jsonObject.values.flatMap { jsonElement ->
            when (jsonElement) {
                is JsonArray -> jsonElement.jsonArray
                else -> listOf(jsonElement)
            }.map { json.decodeFromJsonElement<LessonDTO>(it) }
        }.map { LessonDTOMapper().mapToDomainModel(it) }

        return Day(
            title = model.title,
            lessons = lessons
        )
    }
}
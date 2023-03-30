package com.strangenaut.timetablebstu.feature_timetable.data.util

import com.strangenaut.timetablebstu.feature_timetable.util.LESSON_DURATION_MINUTES
import com.strangenaut.timetablebstu.feature_timetable.domain.model.Lesson
import com.strangenaut.timetablebstu.feature_timetable.domain.util.DomainMapper
import com.strangenaut.timetablebstu.feature_timetable.data.model.LessonDTO
import com.strangenaut.timetablebstu.feature_timetable.domain.model.LessonType
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import java.time.Duration
import java.time.LocalTime

class LessonDTOMapper : DomainMapper<LessonDTO, Lesson> {

    val JsonElement.name: String
        get() = this.jsonObject["ShortName"]
            .toString()
            .replace("\"", "")

    override fun mapToDomainModel(model: LessonDTO): Lesson {
        val timeBegin = model.time
            .substringBefore(" ")
            .padStart(5, '0')

        val timeEnd = LocalTime
            .parse(timeBegin)
            .plus(Duration.ofMinutes(LESSON_DURATION_MINUTES))
            .toString()

        val discipline = model.discipline
            .substringAfter(' ')
            .lowercase()
            .replace(".", ". ")
            .replace("  ", " ")
            .replaceFirstChar { it.titlecase() }

        val lessonType = when (model.discipline.substringBefore(' ')) {
            "лек" -> LessonType.LECTURE
            "пр" -> LessonType.PRACTICE
            "лаб" -> LessonType.LAB
            else -> LessonType.UNIDENTIFIED
        }

        val lecturers = when (val lecturersData = model.lecturersJson?.jsonObject?.values?.firstOrNull()) {
            is JsonArray -> lecturersData.joinToString(", ") { it.name }
            is JsonObject -> lecturersData.jsonObject.name
            else -> ""
        }

        var classroom: String = model.classroom ?: ""
        classroom = classroom.substringBefore(";")

        return Lesson(
            weekCode = model.weekCode,
            timeBegin = timeBegin,
            timeEnd = timeEnd,
            discipline = discipline,
            lessonType = lessonType,
            lecturers = lecturers,
            classroom = classroom
        )
    }
}
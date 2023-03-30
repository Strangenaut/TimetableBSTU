package com.strangenaut.timetablebstu.feature_timetable.domain.use_case

import com.strangenaut.timetablebstu.feature_timetable.domain.model.Timetable
import com.strangenaut.timetablebstu.feature_timetable.domain.repository.TimetableRepository

class GetGroupsNumbersList(
    private val repository: TimetableRepository
) {

    operator fun invoke(): List<String> {
        val timetable = repository.timetable.value ?: Timetable()
        return timetable.groups.filter { group ->
            group.days.isNotEmpty()
        }.map { group ->
            group.number
        }
    }
}
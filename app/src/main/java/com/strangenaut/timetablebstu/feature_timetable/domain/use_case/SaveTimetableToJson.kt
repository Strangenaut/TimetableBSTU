package com.strangenaut.timetablebstu.feature_timetable.domain.use_case

import com.strangenaut.timetablebstu.feature_timetable.domain.repository.TimetableRepository

class SaveTimetableToJson(
    private val repository: TimetableRepository
) {

    suspend operator fun invoke() {
        repository.saveTimetableToJson()
    }
}
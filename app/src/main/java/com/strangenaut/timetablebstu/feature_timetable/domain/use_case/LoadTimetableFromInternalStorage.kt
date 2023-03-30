package com.strangenaut.timetablebstu.feature_timetable.domain.use_case

import com.strangenaut.timetablebstu.feature_timetable.domain.model.Timetable
import com.strangenaut.timetablebstu.feature_timetable.domain.repository.TimetableRepository

class LoadTimetableFromInternalStorage(
    private val repository: TimetableRepository
) {

    suspend operator fun invoke(): Timetable {
        return repository.loadTimetableFromInternalStorage()
    }
}
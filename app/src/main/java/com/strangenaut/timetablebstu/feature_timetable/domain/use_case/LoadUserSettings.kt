package com.strangenaut.timetablebstu.feature_timetable.domain.use_case

import com.strangenaut.timetablebstu.feature_timetable.domain.repository.TimetableRepository

class LoadUserSettings(
    private val repository: TimetableRepository
) {

    operator fun invoke(key: String, defaultValue: Any): Any? {
        return repository.loadUserSettings(key, defaultValue)
    }
}
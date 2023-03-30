package com.strangenaut.timetablebstu.feature_timetable.domain.use_case

import com.strangenaut.timetablebstu.feature_timetable.domain.repository.TimetableRepository

class SaveUserSettings(
    private val repository: TimetableRepository
) {

    suspend operator fun invoke(key: String, value: Any) {
        return repository.saveUserSettings(key, value)
    }
}
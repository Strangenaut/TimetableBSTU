package com.strangenaut.timetablebstu.feature_timetable.domain.repository

import androidx.lifecycle.LiveData
import com.strangenaut.timetablebstu.feature_timetable.domain.model.Timetable

interface TimetableRepository {

    val timetable: LiveData<Timetable>

    suspend fun loadTimetableFromNetwork(): Timetable

    suspend fun loadTimetableFromInternalStorage(): Timetable

    suspend fun saveTimetableToJson()

    fun loadUserSettings(key: String, defaultValue: Any): Any?

    suspend fun saveUserSettings(key: String, value: Any)

}
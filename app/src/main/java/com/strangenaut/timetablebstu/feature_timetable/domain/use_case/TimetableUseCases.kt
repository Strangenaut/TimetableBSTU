package com.strangenaut.timetablebstu.feature_timetable.domain.use_case

data class TimetableUseCases(
    val loadTimetableFromInternalStorage: LoadTimetableFromInternalStorage,
    val loadTimetableFromNetwork: LoadTimetableFromNetwork,
    val getCurrentWeekCode: GetCurrentWeekCode,
    val getGroupsNumbersList: GetGroupsNumbersList,
    val saveTimetableToJson: SaveTimetableToJson,
    val loadUserSettings: LoadUserSettings,
    val saveUserSettings: SaveUserSettings
)
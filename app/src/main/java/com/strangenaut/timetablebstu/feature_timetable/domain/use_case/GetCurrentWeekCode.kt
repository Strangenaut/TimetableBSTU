package com.strangenaut.timetablebstu.feature_timetable.domain.use_case

import com.strangenaut.timetablebstu.feature_timetable.domain.repository.TimetableRepository
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class GetCurrentWeekCode(
    private val repository: TimetableRepository
) {

    operator fun invoke(): Int {
        val startDateString = repository.timetable.value?.startDate ?: return 0

        if (startDateString.isEmpty()) {
            return 0
        }

        val startDate = LocalDate.parse(startDateString)
        val currentWeekCode = (ChronoUnit.WEEKS.between(startDate, LocalDate.now()) + 1) % 2

        // Приведение кода текущей недели к такому же виду, как на сайте (1 - нечётная, 2 - чётная)
        return if (currentWeekCode == 0L) 2 else currentWeekCode.toInt()
    }
}
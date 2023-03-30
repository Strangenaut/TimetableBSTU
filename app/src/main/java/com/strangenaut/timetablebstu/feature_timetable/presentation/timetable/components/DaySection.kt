package com.strangenaut.timetablebstu.feature_timetable.presentation.timetable.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.strangenaut.timetablebstu.R
import com.strangenaut.timetablebstu.feature_timetable.domain.model.Day
import com.strangenaut.timetablebstu.feature_timetable.presentation.core.theme.Shapes

@Composable
fun DaySection(
    currentWeekCode: Int,
    day: Day,
) {
    DayLabel(
        text = when(day.title) {
            "Понедельник" -> stringResource(R.string.monday)
            "Вторник" -> stringResource(R.string.tuesday)
            "Среда" -> stringResource(R.string.wednesday)
            "Четверг" -> stringResource(R.string.thursday)
            "Пятница" -> stringResource(R.string.friday)
            "Суббота" -> stringResource(R.string.saturday)
            "Воскресенье" -> stringResource(R.string.sunday)
            else -> "N/D"
        },
        modifier = Modifier.offset(y = 14.dp)
    )
    Surface(
        modifier = Modifier
            .clip(shape = Shapes.small)
            .background(color = MaterialTheme.colors.surface)
            .animateContentSize()
    ) {
        Column {
            day.lessons.forEach {
                LessonSection(
                    currentWeekCode = currentWeekCode,
                    lesson = it,
                )
            }
        }
        Spacer(modifier = Modifier.height(2.dp))
    }
}
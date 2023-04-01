package com.strangenaut.timetablebstu.feature_timetable.presentation.timetable.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.primarySurface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.strangenaut.timetablebstu.R
import com.strangenaut.timetablebstu.feature_timetable.domain.model.Day
import com.strangenaut.timetablebstu.feature_timetable.domain.util.DateUtils
import com.strangenaut.timetablebstu.feature_timetable.presentation.core.theme.Shapes

@Composable
fun DaySection(
    currentWeekCode: Int,
    day: Day,
    modifier: Modifier = Modifier
) {
    val labelFontColor = if (DateUtils.isToday(day))
        MaterialTheme.colors.secondary
    else
        MaterialTheme.colors.onSurface

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
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
            fontColor = labelFontColor,
            modifier = Modifier.offset(y = 14.dp)
        )
        Surface(
            modifier = Modifier
                .clip(Shapes.small)
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
}
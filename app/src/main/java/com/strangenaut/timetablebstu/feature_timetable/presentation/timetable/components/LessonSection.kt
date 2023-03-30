package com.strangenaut.timetablebstu.feature_timetable.presentation.timetable.components

import android.content.res.Resources
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.foundation.Canvas
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.strangenaut.timetablebstu.R
import com.strangenaut.timetablebstu.feature_timetable.domain.model.LessonType
import com.strangenaut.timetablebstu.feature_timetable.domain.model.Lesson
import com.strangenaut.timetablebstu.feature_timetable.presentation.core.theme.Blue300
import com.strangenaut.timetablebstu.feature_timetable.presentation.core.theme.Green300
import com.strangenaut.timetablebstu.feature_timetable.presentation.core.theme.Orange300

@Composable
fun LessonSection(
    currentWeekCode: Int,
    lesson: Lesson,
) {
    var rowHeight by remember {
        mutableStateOf(0)
    }
    val lineColor: Color = when (lesson.lessonType) {
        LessonType.LECTURE -> Green300
        LessonType.PRACTICE -> Orange300
        LessonType.LAB -> Blue300
        else -> Color.Gray
    }

    val isLessonOnCurrentWeek = lesson.weekCode == currentWeekCode

    val fontColor = if (isLessonOnCurrentWeek) {
        MaterialTheme.colors.onSurface
    } else {
        MaterialTheme.colors.onSecondary
    }

    Row(
        modifier = Modifier.padding(8.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth(fraction = 0.22f)
                .height(rowHeight.dp / Resources.getSystem().displayMetrics.density)
        ) {
            LessonParameter(
                text = lesson.timeBegin,
                fontColor = fontColor
            )
            LessonParameter(
                text = lesson.timeEnd,
                fontColor = fontColor
            )
        }
        Canvas(
            modifier = Modifier.fillMaxWidth(fraction = 0.05f),
            onDraw = {
                drawLine(
                    color = lineColor,
                    strokeWidth = 8.dp.value,
                    start = Offset(x = 0f, y = 0f),
                    end = Offset(x = 0f, y = rowHeight.toFloat())
                )
            }
        )
        Column(
            modifier = Modifier
                .fillMaxWidth(fraction = 0.7f)
                .clip(shape = RoundedCornerShape(8.dp))
                .onGloballyPositioned {
                    rowHeight = it.size.height
                }
        ) {
            LessonParameter(
                text = lesson.discipline,
                fontWeight = FontWeight.Bold,
                fontColor = fontColor
            )
            LessonParameter(
                text = when (lesson.lessonType) {
                    LessonType.LECTURE -> stringResource(R.string.lecture)
                    LessonType.PRACTICE -> stringResource(R.string.practice)
                    LessonType.LAB -> stringResource(R.string.lab)
                    else -> "N/D"
                },
                fontColor = fontColor
            )
            LessonParameter(
                text = lesson.lecturers,
                fontColor = fontColor
            )
            LessonParameter(
                text = lesson.classroom,
                fontColor = fontColor
            )
        }
    }
}
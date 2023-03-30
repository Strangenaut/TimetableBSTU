package com.strangenaut.timetablebstu.feature_timetable.presentation.timetable.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun LessonParameter(
    text: String,
    fontWeight: FontWeight? = MaterialTheme.typography.body1.fontWeight,
    fontColor: Color
) {
    Text(
        text = text,
        softWrap = true,
        fontWeight = fontWeight,
        color = fontColor,
    )
}
package com.strangenaut.timetablebstu.feature_timetable.presentation.timetable.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*

@Composable
fun DayLabel(
    text: String = "--",
    modifier: Modifier
) {
    Surface(
        modifier = modifier.clip(RoundedCornerShape(8.dp))
    ) {
        Text(
            text,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(8.dp, 8.dp, 8.dp, 12.dp)
        )
    }
}

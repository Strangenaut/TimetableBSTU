package com.strangenaut.timetablebstu.feature_timetable.presentation.get_group_timetable.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.strangenaut.timetablebstu.feature_timetable.presentation.core.theme.Shapes
import com.strangenaut.timetablebstu.feature_timetable.presentation.get_group_timetable.GetGroupTimetableEvent

@Composable
fun GroupTitle(
    title: String = "",
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 8.dp, end = 16.dp)
            .clip(shape = Shapes.small)
            .background(color = MaterialTheme.colors.surface)
            .clickable {
                onClick()
            }
    ) {
        Text(
            text = title,
            softWrap = true,
            fontSize = MaterialTheme.typography.h2.fontSize,
            color = MaterialTheme.colors.onSurface,
            modifier = Modifier.padding(16.dp)
        )
    }
}
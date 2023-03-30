package com.strangenaut.timetablebstu.feature_timetable.presentation.timetable.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun NoGroupChosen(
    onChooseGroup: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxHeight()
            .clickable (
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                onChooseGroup()
            }
    ) {
        Text(
            text = "Выберите группу",
            fontSize = MaterialTheme.typography.h1.fontSize,
            color = MaterialTheme.colors.onSurface,
        )
        Text(
            text = "Нажмите, чтобы выбрать",
            fontSize = MaterialTheme.typography.body1.fontSize,
            color = MaterialTheme.colors.onSecondary,
        )
    }
}
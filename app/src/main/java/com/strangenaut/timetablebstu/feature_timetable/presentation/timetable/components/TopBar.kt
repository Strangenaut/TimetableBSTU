package com.strangenaut.timetablebstu.feature_timetable.presentation.timetable.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(
    currentWeekState: String,
    checked: Boolean,
    onCheckedChange: () -> Unit,
    selectedGroupNumber: String,
    onChooseGroup: () -> Unit,
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = currentWeekState,
                    color = MaterialTheme.colors.onSurface,
                    fontSize = MaterialTheme.typography.h2.fontSize,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                SwitchButton(
                    switchPadding = 6.dp,
                    buttonWidth = 50.dp,
                    buttonHeight = 25.dp,
                    value = checked,
                    onCheckedChange = onCheckedChange
                )
            }
            Surface(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colors.surface)
                    .clickable {
                        onChooseGroup.invoke()
                    }
            ) {
                Text(
                    text = selectedGroupNumber,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}
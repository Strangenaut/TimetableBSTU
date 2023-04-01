package com.strangenaut.timetablebstu.feature_timetable.presentation.timetable.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.strangenaut.timetablebstu.R
import com.strangenaut.timetablebstu.feature_timetable.presentation.core.theme.Shapes

@Composable
fun NoGroupSelected(
    onChooseGroup: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxHeight()
    ) {
        Text(
            text = stringResource(R.string.select_group),
            textAlign = TextAlign.Center,
            fontSize = MaterialTheme.typography.h1.fontSize,
            color = MaterialTheme.colors.onSurface,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        )
        Text(
            text = stringResource(R.string.to_select_group_do_this),
            textAlign = TextAlign.Center,
            fontSize = MaterialTheme.typography.body1.fontSize,
            color = MaterialTheme.colors.onSecondary,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        )
        Surface(
            color = MaterialTheme.colors.secondary,
            modifier = Modifier
                .clip(shape = Shapes.small)
                .clickable {
                    onChooseGroup()
                }
        ) {
            Text(
                text = stringResource(R.string.tap_to_select),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.primary,
                fontSize = MaterialTheme.typography.body1.fontSize,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
package com.strangenaut.timetablebstu.feature_timetable.presentation.timetable.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.strangenaut.timetablebstu.R

@Composable
fun BottomBar(
    onClickToday: () -> Unit,
    onClickUpdate: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BottomAppBar(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.update),
                fontSize = MaterialTheme.typography.body1.fontSize,
                color = MaterialTheme.colors.secondary,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null
                    ) { onClickUpdate() }
            )
            Text(
                text = stringResource(id = R.string.today),
                fontSize = MaterialTheme.typography.body1.fontSize,
                color = MaterialTheme.colors.secondary,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null
                    ) { onClickToday() }
            )
        }
    }
}
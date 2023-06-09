package com.strangenaut.timetablebstu.feature_timetable.presentation.timetable.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.strangenaut.timetablebstu.R
import com.strangenaut.timetablebstu.feature_timetable.presentation.core.theme.Shapes
import com.strangenaut.timetablebstu.feature_timetable.presentation.core.theme.isDarkThemeEnabled

@Composable
fun NoConnection(
    appContext: Context,
    onReconnect: () -> Unit,
    modifier: Modifier = Modifier
) {
    val noConnectionString = stringResource(R.string.no_connection)

    LaunchedEffect(true) {
        Toast.makeText(appContext, noConnectionString, Toast.LENGTH_SHORT).show()
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(
                id = if(isDarkThemeEnabled)
                    R.drawable.ic_baseline_wifi_off_dark_theme_24
                else
                    R.drawable.ic_baseline_wifi_off_light_theme_24),
            contentDescription = "",
            modifier = Modifier
                .scale(3f)
                .padding(bottom = 16.dp)
        )
        Text(
            text = stringResource(R.string.connect_to_the_internet),
            textAlign = TextAlign.Center,
            fontSize = MaterialTheme.typography.h1.fontSize,
            color = MaterialTheme.colors.onSurface,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        )
        Text(
            text = stringResource(R.string.check_connection_and_try_again),
            textAlign = TextAlign.Center,
            fontSize = MaterialTheme.typography.body1.fontSize,
            color = MaterialTheme.colors.onSecondary,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        )
        Surface (
            color = MaterialTheme.colors.secondary,
            modifier = Modifier
                .clip(shape = Shapes.small)
                .clickable {
                    onReconnect.invoke()
                }
        ) {
            Text(
                text = stringResource(R.string.tap_to_retry),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.primary,
                fontSize = MaterialTheme.typography.body1.fontSize,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
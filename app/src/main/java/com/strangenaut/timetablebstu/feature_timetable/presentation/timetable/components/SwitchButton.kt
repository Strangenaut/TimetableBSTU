package com.strangenaut.timetablebstu.feature_timetable.presentation.timetable.components

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SwitchButton(
    switchPadding: Dp,
    buttonWidth: Dp,
    buttonHeight: Dp,
    value: Boolean,
    onCheckedChange: () -> Unit
) {
    val switchSize by remember {
        mutableStateOf(buttonHeight-switchPadding*2)
    }
    var checked by remember {
        mutableStateOf(value)
    }
    var padding by remember {
        mutableStateOf(0.dp)
    }

    padding = if (checked) buttonWidth - switchSize - switchPadding * 2 else 0.dp

    val animateSize by animateDpAsState(
        targetValue = if (checked) padding else 0.dp,
        tween(
            durationMillis = 200,
            delayMillis = 0,
            easing = LinearOutSlowInEasing
        )
    )

    Box(
        modifier = Modifier
            .width(buttonWidth)
            .height(buttonHeight)
            .clip(CircleShape)
            .background(if (checked) MaterialTheme.colors.secondary else MaterialTheme.colors.primaryVariant)
            .clickable {
                checked = !checked
                onCheckedChange()
            }
    ){
        Row(modifier = Modifier.fillMaxSize().padding(switchPadding)) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(animateSize)
                    .background(Color.Transparent)
            )
            Box(modifier = Modifier
                .size(switchSize)
                .clip(CircleShape)
                .background(Color.White)
            )
        }
    }
}
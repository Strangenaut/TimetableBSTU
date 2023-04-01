package com.strangenaut.timetablebstu.feature_timetable.presentation.timetable.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.strangenaut.timetablebstu.feature_timetable.presentation.core.shimmerEffect
import com.strangenaut.timetablebstu.feature_timetable.presentation.core.theme.Shapes

@Composable
fun LoadingTimetableShimmer(
    isLoading: Boolean,
    contentAfterLoading: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    if(isLoading) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.fillMaxSize()
        ) {
            repeat(3) {
                Box(
                    modifier = Modifier
                        .offset(y = 14.dp)
                        .size(width = 40.dp, height = 45.dp)
                        .clip(shape = Shapes.small)
                        .background(MaterialTheme.colors.surface)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                            .clip(shape = Shapes.large)
                            .shimmerEffect()
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = Shapes.small)
                        .background(MaterialTheme.colors.surface)
                ) {
                    Column {
                        repeat(3) {
                            Row(
                                modifier = Modifier.padding(8.dp)
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier
                                        .fillMaxWidth(fraction = 0.27f)
                                        .height(100.dp)
                                        .padding(horizontal = 8.dp, vertical = 2.dp)
                                ) {
                                    repeat(2) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth(fraction = 0.7f)
                                                .height(26.dp)
                                                .clip(shape = Shapes.medium)
                                                .padding(vertical = 4.dp)
                                                .shimmerEffect()
                                        )
                                    }
                                }
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth(fraction = 0.05f)
                                        .height(100.dp)
                                        .clip(shape = Shapes.medium)
                                        .padding(horizontal = 4.dp)
                                        .shimmerEffect()
                                )
                                Column (
                                    verticalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier
                                        .height(100.dp)
                                        .padding(horizontal = 8.dp, vertical = 4.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth(fraction = 0.7f)
                                            .height(22.dp)
                                            .clip(shape = Shapes.medium)
                                            .padding(vertical = 2.dp)
                                            .shimmerEffect()
                                    )
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth(fraction = 0.55f)
                                            .height(22.dp)
                                            .clip(shape = Shapes.medium)
                                            .padding(vertical = 2.dp)
                                            .shimmerEffect()
                                    )
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth(fraction = 0.6f)
                                            .height(22.dp)
                                            .clip(shape = Shapes.medium)
                                            .padding(vertical = 2.dp)
                                            .shimmerEffect()
                                    )
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth(fraction = 0.2f)
                                            .height(22.dp)
                                            .clip(shape = Shapes.medium)
                                            .padding(vertical = 2.dp)
                                            .shimmerEffect()
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    } else {
        contentAfterLoading()
    }
}
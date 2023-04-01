package com.strangenaut.timetablebstu.feature_timetable.presentation

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.strangenaut.timetablebstu.feature_timetable.presentation.core.Screen
import com.strangenaut.timetablebstu.feature_timetable.presentation.core.SharedViewModel
import com.strangenaut.timetablebstu.feature_timetable.presentation.timetable.TimetableScreen
import com.strangenaut.timetablebstu.feature_timetable.presentation.core.theme.TimetableBSTUTheme
import com.strangenaut.timetablebstu.feature_timetable.presentation.select_group.SelectGroupScreen
import com.strangenaut.timetablebstu.feature_timetable.presentation.select_group.SelectGroupViewModel
import com.strangenaut.timetablebstu.feature_timetable.presentation.timetable.TimetableEvent
import com.strangenaut.timetablebstu.feature_timetable.presentation.timetable.TimetableViewModel
import com.strangenaut.timetablebstu.feature_timetable.presentation.timetable.receiver.DateChangedReceiver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val timetableViewModel: TimetableViewModel by viewModels()

    private val selectGroupViewModel: SelectGroupViewModel by viewModels()

    private lateinit var receiver: DateChangedReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        receiver = DateChangedReceiver(timetableViewModel)

        IntentFilter(Intent.ACTION_DATE_CHANGED).also {
            registerReceiver(receiver, it)
        }

        setContent {
            TimetableBSTUTheme {
                val navController = rememberNavController()
                val context = this

                val sharedViewModel: SharedViewModel = hiltViewModel()

                NavHost(
                    navController = navController,
                    startDestination = Screen.TimetableScreen.route
                ) {
                    composable(route = Screen.TimetableScreen.route) {
                        TimetableScreen(
                            appContext = context,
                            navController = navController,
                            viewModel = timetableViewModel,
                            sharedViewModel = sharedViewModel
                        )
                    }

                    composable(route = Screen.GetGroupTimetableScreen.route) {
                        SelectGroupScreen(
                            navController = navController,
                            viewModel = selectGroupViewModel,
                            sharedViewModel = sharedViewModel
                        )
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        timetableViewModel.onEvent(TimetableEvent.GetCurrentWeekCode)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}
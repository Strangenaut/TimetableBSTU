package com.strangenaut.timetablebstu.feature_timetable.presentation.timetable

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.*
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.strangenaut.timetablebstu.feature_timetable.domain.use_case.TimetableUseCases
import com.strangenaut.timetablebstu.feature_timetable.domain.util.DateUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject

@HiltViewModel
class TimetableViewModel @Inject constructor(
    private val useCases: TimetableUseCases,
    private val internalJsonFile: File,
    private val app: Application
) : ViewModel() {

    private val _state = mutableStateOf(TimetableState())
    val state: State<TimetableState> = _state

    val timetableJsonFileDoesNotExist: Boolean
        get() = !internalJsonFile.exists()

    init {
        onEvent(TimetableEvent.LoadTimetable(false))
    }

    private fun checkInternetConnection() {
        _state.value = _state.value.copy(
            isConnectionAvailable = hasInternetConnection()
        )
    }

    private suspend fun loadTimetable(fromNetwork: Boolean) {
        _state.value = _state.value.copy(
            timetable = if (fromNetwork)
                useCases.loadTimetableFromNetwork()
            else
                useCases.loadTimetableFromInternalStorage()
        )
    }

    private suspend fun saveTimetableJson() {
        useCases.saveTimetableToJson()
    }

    fun hasInternetConnection(): Boolean {
        val connectivityManager = app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(TRANSPORT_WIFI) -> true
            capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

    fun updateDayContentSize(dayTitle: String, value: Int) {
        _state.value.daysContentSizeList[dayTitle] = value
    }

    fun onEvent(event: TimetableEvent) {
        when (event) {
            is TimetableEvent.LoadTimetable -> {
                val notFromNetworkOnly = !event.fromNetworkOnly

                viewModelScope.launch {
                    if (internalJsonFile.exists() && notFromNetworkOnly) {
                        loadTimetable(false)
                        _state.value = _state.value.copy(
                            currentWeekCode = useCases.getCurrentWeekCode()
                        )
                        return@launch
                    }

                    checkInternetConnection()
                    if (_state.value.isConnectionAvailable) {
                        loadTimetable(true)
                        if (_state.value.timetable.groups.isEmpty()) {
                            _state.value = _state.value.copy(
                                isConnectionAvailable = false
                            )
                        }
                        _state.value = _state.value.copy(
                            currentWeekCode = useCases.getCurrentWeekCode()
                        )
                        withContext(Dispatchers.IO) {
                            saveTimetableJson()
                        }
                    }
                }
            }
            is TimetableEvent.GetCurrentWeekCode -> {
                viewModelScope.launch {
                    _state.value = _state.value.copy(
                        currentWeekCode = useCases.getCurrentWeekCode()
                    )
                }
            }
            is TimetableEvent.ScrollToCurrentWeekDay -> {
                event.uiThreadScope.launch {
                    if (DateUtils.currentWeekDay in _state.value.daysContentSizeList) {
                        var scrollPosition = 0
                        for (it in _state.value.daysContentSizeList) {
                            if (it.key == DateUtils.currentWeekDay) {
                                break
                            }
                            scrollPosition += it.value
                        }
                        event.scrollState.animateScrollTo(scrollPosition)
                    }
                }
            }
            is TimetableEvent.ToggleCurrentWeekOnlyVisibility -> {
                _state.value = _state.value.copy(
                    isCurrentWeekOnlyVisible = !state.value.isCurrentWeekOnlyVisible
                )
            }
        }
    }
}
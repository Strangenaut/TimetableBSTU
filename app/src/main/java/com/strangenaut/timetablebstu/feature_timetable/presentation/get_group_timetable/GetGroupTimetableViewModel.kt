package com.strangenaut.timetablebstu.feature_timetable.presentation.get_group_timetable

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.strangenaut.timetablebstu.feature_timetable.domain.repository.TimetableRepository
import com.strangenaut.timetablebstu.feature_timetable.domain.use_case.TimetableUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetGroupTimetableViewModel @Inject constructor(
    private val useCases: TimetableUseCases,
    private val repository: TimetableRepository
) : ViewModel() {

    private val _state = mutableStateOf(GetGroupTimetableState())
    val state: State<GetGroupTimetableState> = _state

    init {
        onEvent(GetGroupTimetableEvent.GetGroupsList)
    }

    fun onEvent(event: GetGroupTimetableEvent) {
        when (event) {
            is GetGroupTimetableEvent.GetGroupsList -> {
                viewModelScope.launch {
                    repository.timetable.observeForever {
                        _state.value = _state.value.copy(
                            groupsNumbers = useCases.getGroupsNumbersList()
                        )
                    }
                }
            }
            is GetGroupTimetableEvent.SelectGroup -> {
                viewModelScope.launch {
                    _state.value = _state.value.copy(
                        selectedGroup = event.selectedGroup
                    )
                    useCases.saveUserSettings(key = "selectedGroup", value = event.selectedGroup)
                }
            }
        }
    }
}
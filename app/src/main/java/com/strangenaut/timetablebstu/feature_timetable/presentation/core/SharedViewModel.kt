package com.strangenaut.timetablebstu.feature_timetable.presentation.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.strangenaut.timetablebstu.feature_timetable.domain.use_case.TimetableUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    useCases: TimetableUseCases
) : ViewModel() {

    private val _selectedGroup = MutableLiveData<String>()
    val selectedGroup: LiveData<String> = _selectedGroup

    init {
        _selectedGroup.value = useCases.loadUserSettings(key = "selectedGroup", defaultValue = "") as String
    }

    fun setSelectedGroup(group: String) {
        _selectedGroup.value = group
    }
}
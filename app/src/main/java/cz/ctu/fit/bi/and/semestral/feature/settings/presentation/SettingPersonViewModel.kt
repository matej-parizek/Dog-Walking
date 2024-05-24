package cz.ctu.fit.bi.and.semestral.feature.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.ctu.fit.bi.and.semestral.feature.settings.data.SettingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SettingPersonViewModel(
    private val repository: SettingRepository
) : ViewModel() {
    private val _state = MutableStateFlow(SettingState())
    val state: StateFlow<SettingState> = _state
    private val id = 0

    init {
        viewModelScope.launch {
            repository.initialize(id)
        }
        getData()
    }


    private fun getData() {
        viewModelScope.launch {
            repository.getSetting(id = id).collect {
                _state.value = _state.value.copy(settings = it)
            }
        }
    }

    fun updateHeight(value: Float) {
        viewModelScope.launch {
            repository.updateHeight(id, value)
        }
    }

    fun updateSteps(value: Float) {
        viewModelScope.launch {
            repository.updateSteps(id, value)
        }
    }

    fun updateWeight(value: Float) {
        viewModelScope.launch {
            repository.updateWeight(id, value)
        }
    }
}

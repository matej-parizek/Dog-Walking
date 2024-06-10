package cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.presentation.ui.dog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.data.SettingRepository
import cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.presentation.SettingState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SettingDogViewModel(
    private val repository: SettingRepository
) : ViewModel() {
    private val _state = MutableStateFlow(SettingState())
    val state: StateFlow<SettingState> = _state

    private  val id = 1
    init {
        viewModelScope.launch {
            repository.initialize(id)
        }
        getData()
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

    private fun getData() {
        viewModelScope.launch {
            repository.getSetting(id).collect {
                _state.value = _state.value.copy(settings = it)
            }
        }
    }
}

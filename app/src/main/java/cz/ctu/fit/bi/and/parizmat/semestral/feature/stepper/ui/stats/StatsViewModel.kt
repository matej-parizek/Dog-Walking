package cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.ui.stats

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.ctu.fit.bi.and.parizmat.semestral.core.data.Response
import cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.ScreenState
import cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.ScreenStateEntity
import cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.data.local.StepperRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StatsViewModel(
    repository: StepperRepository
) : ViewModel() {

    private val _state = MutableStateFlow<ScreenState<StatsState>>(ScreenState.Loading)
    val state = _state.asStateFlow()
    init {
        viewModelScope.launch {
            when (val result = repository.loadSteps()) {
                is Response.Loading -> _state.value = ScreenState.Loading
                is Response.Error -> _state.value = ScreenState.Error(result.data)
                is Response.Success -> {
                    _state.value = ScreenState.Loaded(StatsState(data = result.data.first, info = result.data.second))
                }
            }
        }
    }


}


data class StatsState(
    val data: List<Float>,
    val info: List<String>
):ScreenStateEntity
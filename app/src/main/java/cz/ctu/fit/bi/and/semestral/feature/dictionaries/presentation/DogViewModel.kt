package cz.ctu.fit.bi.and.semestral.feature.dictionaries.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.ctu.fit.bi.and.semestral.feature.dictionaries.data.DogRepository
import cz.ctu.fit.bi.and.semestral.feature.dictionaries.domain.Dog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.internal.wait

class DogViewModel(
    private val repository: DogRepository
) : ViewModel() {
    private val _state = MutableStateFlow(DogState())
    val state: StateFlow<DogState> = _state

    init {
        viewModelScope.launch {
            repository.getDogs().collect {
                _state.value = _state.value.copy(dogs = it)
            }
        }
        fetch()
    }
    fun fetch() {
        viewModelScope.launch {
            repository.fetch()
        }
    }

}

data class DogState(
    val dogs: List<Dog> = emptyList(),
)

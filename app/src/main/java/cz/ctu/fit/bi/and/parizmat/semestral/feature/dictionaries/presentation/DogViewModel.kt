package cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.DogRepository
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.domain.Dog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DogViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val repository: DogRepository
) : ViewModel() {
    val name = savedStateHandle.getStateFlow("name", "")

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

    fun retry() {
        savedStateHandle["name"] = ""
        filterByQuery(name.value)
    }

    private fun fetch() {
        viewModelScope.launch {
            repository.fetch()
        }
    }


    override fun onCleared() {
        viewModelScope.launch {
            repository.clear()
        }
        super.onCleared()
    }

    fun onChange(query: String) {
        savedStateHandle["name"] = query
        filterByQuery(query)
    }

    private fun filterByQuery(query: String) {
        viewModelScope.launch {
            repository.filterByQuery(query).collect {
                _state.value = _state.value.copy(dogs = it)
            }
        }
    }

}

data class DogState(
    val dogs: List<Dog> = emptyList(),
)

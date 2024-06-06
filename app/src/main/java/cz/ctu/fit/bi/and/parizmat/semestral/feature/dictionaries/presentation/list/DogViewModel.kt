package cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.presentation.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.ctu.fit.bi.and.parizmat.semestral.core.data.Response
import cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.ScreenState
import cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.ScreenStateEntity
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.DogRepository
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.domain.Dog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class DogViewModel(
    private val savedStateHandle: SavedStateHandle, private val repository: DogRepository
) : ViewModel() {
    val name = savedStateHandle.getStateFlow("name", "")
    private val _screenState = MutableStateFlow<ScreenState<DogState>>(ScreenState.Loading)
    val state: StateFlow<ScreenState<DogState>> = _screenState.asStateFlow()

    init {
        fetch()
    }

    fun retry() {
        savedStateHandle["name"] = ""
        filterByQuery(name.value)
    }

    private fun fetch() {
        viewModelScope.launch {
            when (val result = repository.fetch()) {
                is Response.Success -> _screenState.value=getDogs()

                is Response.Loading -> _screenState.value = ScreenState.Loading

                is Response.Error -> _screenState.value = ScreenState.Error(result.data)
            }
        }
    }

    fun onChange(query: String) {
        savedStateHandle["name"] = query
        filterByQuery(query)
    }

    private suspend fun getDogs(): ScreenState<DogState> {
        return when (val result = repository.getDogs()) {
            is Response.Error -> ScreenState.Error(result.data)
            is Response.Loading -> ScreenState.Loading
            is Response.Success -> ScreenState.Loaded(DogState(dogs = result.data.first()))
        }
    }

    private fun filterByQuery(query: String) {
        viewModelScope.launch {
            repository.filterByQuery(query).collect {
                _screenState.value = ScreenState.Loaded(DogState(dogs = it))
            }
        }
    }
    override fun onCleared() {
        viewModelScope.launch {
            repository.deleteAll()
        }
        super.onCleared()
    }
}

data class DogState(
    val dogs: List<Dog> = emptyList(),
) : ScreenStateEntity

package cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.presentation.detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.ctu.fit.bi.and.parizmat.semestral.core.data.DataError
import cz.ctu.fit.bi.and.parizmat.semestral.core.data.Response
import cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.ScreenState
import cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.Screens
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.DogRepository
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.domain.Dog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DogDetailViewModel(
    private val repository: DogRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _screenState = MutableStateFlow<ScreenState<Dog>>(ScreenState.Loading)
    val screenState: StateFlow<ScreenState<Dog>> = _screenState

    private val id: String?
        get() = savedStateHandle[Screens.Detail.ID]

    init {
        when (id) {
            null -> _screenState.value = ScreenState.Error(DataError.Database.NOT_FOUND)
            else -> {
                Log.i("Info:", id.toString())
                getDog(id!!)
            }
        }
    }

    private fun getDog(id: String) {
        viewModelScope.launch {
            when (val result = repository.getDog(id)) {
                is Response.Success -> {
                    result.data.collect {
                        if (it != null) {
                            repository.randomImage(it)
                            _screenState.value = ScreenState.Loaded(it)
                        }
                        else _screenState.value = ScreenState.Error(DataError.Database.NOT_FOUND)
                    }
                }

                is Response.Loading -> _screenState.value = ScreenState.Loading
                is Response.Error -> _screenState.value = ScreenState.Error(result.data)
            }
        }
    }
}

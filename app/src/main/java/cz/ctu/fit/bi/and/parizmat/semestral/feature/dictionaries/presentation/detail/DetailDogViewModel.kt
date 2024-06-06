package cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.DogRepository

class DetailDogViewModel(
    private val repository: DogRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

}
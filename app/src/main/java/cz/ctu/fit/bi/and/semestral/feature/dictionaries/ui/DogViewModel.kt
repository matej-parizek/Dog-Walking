package cz.ctu.fit.bi.and.semestral.feature.dictionaries.ui

import androidx.lifecycle.ViewModel
import cz.ctu.fit.bi.and.semestral.feature.dictionaries.data.DogResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DogViewModel : ViewModel() {
    // MutableStateFlow s prázdným seznamem
    private val _dogResultsFlow: MutableStateFlow<List<DogResult>> = MutableStateFlow(emptyList())
    val dogResultsFlow: StateFlow<List<DogResult>> get() = _dogResultsFlow.asStateFlow()

    // Metoda pro aktualizaci seznamu
    fun updateDogResults(newResults: List<DogResult>) {
        _dogResultsFlow.value = newResults
    }
}
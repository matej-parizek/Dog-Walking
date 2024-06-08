package cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.ui.stepper

import android.annotation.SuppressLint
import android.icu.math.BigDecimal
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.ctu.fit.bi.and.parizmat.semestral.core.data.Response
import cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.ScreenState
import cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.ScreenStateEntity
import cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.data.local.StepperRepository
import cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.domain.StepCounter
import cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.usecase.StepCounterHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


class StepperViewModel(
    private val repository: StepperRepository,
    private val stepCounter: StepCounterHelper
) : ViewModel() {
    private val _screenState = MutableStateFlow<ScreenState<StepperState>>(ScreenState.Loading)
    val state = _screenState.asStateFlow()

    init {
        viewModelScope.launch {

            Log.e("STEP_COUNT_LISTENER", "1")
            val stepsFromLastBoot = stepCounter.steps()
            Log.d("STEP_COUNT_LISTENER", stepsFromLastBoot.toString())
            repository.storeSteps(stepsFromLastBoot)
        }
        getSteps()
    }

    private fun getSteps() {
        viewModelScope.launch {
            when (val result = repository.loadTodaySteps()) {
                is Response.Loading -> _screenState.value = ScreenState.Loading
                is Response.Error -> _screenState.value = ScreenState.Error(result.data)
                is Response.Success -> {
                    adjustData(result.data)
                }
            }
        }
    }

    private suspend fun adjustData(currentSteps: Long) {
        var height: Double
        var weight: Double
        var minSteps: Double

        val entityPerson = repository.settingsPerson().first()
        height = entityPerson?.height?.toDouble() ?: 160.0
        weight = entityPerson?.weight?.toDouble() ?: 60.0
        minSteps = entityPerson?.minSteps?.toDouble() ?: 6000.0
        val person = personAdjust(
            height = height,
            weight = weight,
            minSteps = minSteps,
            currentSteps = currentSteps
        )

        val entityDog = repository.settingsDog().first()
        height = entityDog?.height?.toDouble() ?: 160.0
        weight = entityDog?.weight?.toDouble() ?: 1.0
        minSteps = entityDog?.minSteps?.toDouble() ?: 6000.0
        val dog = dogAdjust(
            height = height,
            weight = weight,
            minSteps = minSteps,
            currentSteps = currentSteps
        )
        _screenState.value = ScreenState.Loaded(StepperState(dog = dog, person = person))
    }


    private fun personAdjust(
        currentSteps: Long,
        weight: Double,
        height: Double,
        minSteps: Double
    ): StepCounter {
        val kcalNumber = ((currentSteps * weight * height * 0.413 / 100.0) / 1000 * 0.5)
        val kcal = BigDecimal(kcalNumber).setScale(2,BigDecimal.ROUND_HALF_UP).toDouble()
        val progress = BigDecimal(currentSteps.toDouble() / minSteps)
        Log.d("ROUNDED_DATA",progress.toString())
        return StepCounter(
            steps = currentSteps,
            evaluation = 0.0,
            kcal = kcal,
            progress = progress.setScale(2,BigDecimal.ROUND_HALF_UP).toFloat()
        )
    }

    private fun dogAdjust(
        currentSteps: Long,
        weight: Double,
        height: Double,
        minSteps: Double
    ): StepCounter {
        var coefficientHeight = 1.0
        var coefficientKcal = 0.0004
        when (height) {
            0.0 -> {
                coefficientHeight = 1.5
                coefficientKcal = 0.0003
            }

            1.0 -> {
                coefficientHeight = 2.0
                coefficientKcal = 0.0004
            }

            2.0 -> {
                coefficientHeight = 2.5
                coefficientKcal = 0.0005
            }
        }
        val currSteps = coefficientHeight * currentSteps
        val progress = BigDecimal(currSteps / minSteps).setScale(2,BigDecimal.ROUND_HALF_UP)
        Log.d("ROUNDED_DATA",progress.toString())

        val kcal = BigDecimal(weight * currSteps * coefficientKcal).setScale(2,BigDecimal.ROUND_HALF_UP).toDouble()
        return StepCounter(
            steps = currSteps.toLong(),
            evaluation = 0.0,
            kcal = kcal,
            progress = progress.toFloat()
        )
    }

}

data class StepperState(
    val person: StepCounter,
    val dog: StepCounter,
) : ScreenStateEntity


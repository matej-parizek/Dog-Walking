package cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.ui.stepper

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
            val stepsFromLastBoot = stepCounter.steps()
            repository.storeSteps(stepsFromLastBoot)
        }
        getSteps()
    }

    /**
     * Initiates the retrieval of today's step count data from the repository and updates the screen state based on the response.
     * The function handles different states of data retrieval: loading, error, and success. In the case of a success,
     * it proceeds to adjust the step data for both a person and a dog by calling the `adjustData` function.
     * This function effectively manages UI state transitions related to step data fetching and processing.
     */
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

    /**
     * Retrieves user and dog settings from a repository and computes adjusted step data and calorie burn for both.
     * This function first fetches and processes settings for a person, including height, weight, and minimum step goals,
     * and then applies the same process for a dog using the previously defined `personAdjust` and `dogAdjust` functions.
     * The computed values are then used to update the UI or state with the new adjusted data.
     *
     * @param currentSteps The current number of steps taken, which is used for both the person and the dog.
     */
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

    /**
     * Computes the calorie burn and progress toward a step goal for a person based on their actual steps, weight, and height.
     * The function uses a specific formula to calculate calories burned considering the individual's physical dimensions and activity level.
     * Progress towards the minimum step goal is calculated and both the calories and progress are logged for monitoring.
     *
     * @param currentSteps The actual number of steps taken by the person.
     * @param weight The weight of the person in kilograms.
     * @param height The height of the person in centimeters.
     * @param minSteps The minimum step goal for the person to achieve.
     * @return A StepCounter object containing the total steps, evaluation metric, calculated calories, and progress percentage.
     */
    private fun personAdjust(
        currentSteps: Long,
        weight: Double,
        height: Double,
        minSteps: Double
    ): StepCounter {
        val kcalNumber = ((currentSteps * weight * height * 0.413 / 100.0) / 1000 * 0.5)
        val kcal = BigDecimal(kcalNumber).setScale(2, BigDecimal.ROUND_HALF_UP).toDouble()
        val progress = BigDecimal(currentSteps.toDouble() / minSteps)
        Log.d("ROUNDED_DATA", progress.toString())
        return StepCounter(
            steps = currentSteps,
            evaluation = 0.0,
            kcal = kcal,
            progress = progress.setScale(4, BigDecimal.ROUND_HALF_UP).toFloat()
        )
    }

    /**
     * Calculates adjusted step count and estimated calories burned for a dog based on its height, weight, and actual steps taken.
     * Adjustments are made using specific coefficients that vary with the dog's height.
     * This function also computes the progress towards a minimum step goal and logs the rounded data for monitoring.
     *
     * @param currentSteps The actual number of steps taken by the dog.
     * @param weight The weight of the dog in kilograms.
     * @param height The height category of the dog, which influences the adjustment coefficients.
     * @param minSteps The minimum step goal for the dog to achieve.
     * @return A StepCounter object containing the adjusted steps, calculated calories, and progress.
     */
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
                coefficientHeight = 2.5
                coefficientKcal = 0.0005
            }

            1.0 -> {
                coefficientHeight = 2.0
                coefficientKcal = 0.0004
            }

            2.0 -> {
                coefficientHeight = 1.5
                coefficientKcal = 0.0003
            }
        }
        val currSteps = coefficientHeight * currentSteps
        val progress = BigDecimal(currSteps / minSteps).setScale(4, BigDecimal.ROUND_HALF_UP)
        Log.d("ROUNDED_DATA", progress.toString())

        val kcal =
            BigDecimal(weight * currSteps * coefficientKcal).setScale(2, BigDecimal.ROUND_HALF_UP)
                .toDouble()
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


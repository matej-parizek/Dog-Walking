package cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.domain

/**
 * Data class representing settings for an individual, such as a human or a dog.
 * This class stores default settings like height, weight, and daily minimum steps.
 *
 * @param id Unique identifier for the settings, defaulting to 0 for person and 1 for dog.
 * @param height Height of the individual in centimeters, default is 160 cm.
 * @param weight Weight of the individual in kilograms, default is 60 kg.
 * @param minSteps Minimum number of steps the individual should take daily, default is 5000 steps.
 */
data class Setting(
    val id: Int = 0,
    val height: Int = 160,
    val weight: Float = 60f,
    val minSteps: Int = 5000
)
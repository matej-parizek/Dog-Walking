package cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.domain

import cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.ScreenStateEntity

/**
 * Represents a dog entity within the application, encapsulating all details relevant to a dog's characteristics.
 *
 * @property id Unique identifier for the dog; defaults to "Error" if not properly set.
 * @property name Name of the dog; defaults to "Error" if not properly set.
 * @property description Descriptive text about the dog; defaults to "Error" if information is unavailable.
 * @property hypoallergenic Boolean indicating whether the dog is hypoallergenic; defaults to false.
 * @property image URL or URI pointing to an image of the dog; defaults to "Error" if the link is broken or unavailable.
 * @property group Classification or group of the dog; defaults to "Error" if not properly categorized.
 * @property maleWeight Weight range for a male of this breed, encapsulated by a Range object.
 * @property femaleWeight Weight range for a female of this breed, also encapsulated by a Range object.
 * @property life Expected lifespan of the dog, represented as a range of years.
 */
data class Dog(
    val id: String = "Error",
    val name: String = "Error",
    val description: String = "Error",
    val hypoallergenic: Boolean = false,
    var image: String = "Error",
    val group: String = "Error",
    val maleWeight: Range = Range(0, 0),
    val femaleWeight: Range = Range(0, 0),
    val life: Range = Range(0, 0),
) : ScreenStateEntity {
    /**
     * Represents a range of values, typically used for measurements like weight or life expectancy,
     * indicating the minimum and maximum expected values.
     *
     * @property min The minimum value in the range.
     * @property max The maximum value in the range.
     */
    data class Range(
        val min: Int,
        val max: Int,
    )
}

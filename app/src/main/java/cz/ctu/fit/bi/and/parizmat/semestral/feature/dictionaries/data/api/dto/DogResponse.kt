package cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.api.dto

import kotlinx.serialization.Serializable

/**
 * Represents the response structure received from an API call that returns dog data.
 * This class encapsulates a list of `DogDto` objects as its main data.
 *
 * @property data A list of `DogDto` instances representing individual dogs, each containing
 * detailed attributes and relationships as specified by the `DogDto` class.
 */
@Serializable
data class DogResponse(
    val data: List<DogDto>,
)
package cz.ctu.fit.bi.and.semestral.feature.dictionaries.data.api.dto

import cz.ctu.fit.bi.and.semestral.feature.dictionaries.data.api.dto.DogDto
import kotlinx.serialization.Serializable

@Serializable
data class DogResponse(
    val data: List<DogDto>,
) {
}
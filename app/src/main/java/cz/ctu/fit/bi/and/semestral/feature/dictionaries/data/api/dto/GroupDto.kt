package cz.ctu.fit.bi.and.semestral.feature.dictionaries.data.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GroupDto(
    @SerialName("id") val id: String,
    @SerialName("type") val type: String,
    @SerialName("attributes") val attributes: GroupAttributes
) {
    @Serializable
    data class GroupAttributes(
        val name: String
    )
}
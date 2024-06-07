package cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a data transfer object for group, used to classify dogs entities.
 * This class includes details about the group's identity and attributes fetched from an API.
 *
 * @property id Unique identifier for the group.
 * @property type Type of the group, usually describing its classification or category.
 * @property attributes Contains more detailed attributes of the group, such as its name.
 */
@Serializable
data class GroupDto(
    @SerialName("id") val id: String,
    @SerialName("type") val type: String,
    @SerialName("attributes") val attributes: GroupAttributes,
) {
    /**
     * @property name The name of the group which often indicates the category or classification of its members.
     */
    @Serializable
    data class GroupAttributes(
        val name: String
    )
}
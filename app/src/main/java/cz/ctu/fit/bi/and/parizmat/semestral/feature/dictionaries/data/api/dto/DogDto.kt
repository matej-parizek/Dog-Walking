package cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a data transfer object for dog information fetched from an API. This class includes
 * various nested data classes that reflect the structure of the JSON data received.
 *
 * @property id Unique identifier for the dog.
 * @property type General type or category of the dog.
 * @property attributes Detailed attributes of the dog.
 * @property relationships Relationships of the dog with other entities, such as breed groups.
 */

@Serializable
data class DogDto(
    val id: String,
    val type: String,
    val attributes: DogAttributes,
    val relationships: DogRelationships,
) {
    /**
     * @property name Name of the dog.
     * @property description Description or other notable information about the dog.
     * @property life Range of expected life span for the dog.
     * @property maleWeight Weight range for a male of this dog type.
     * @property femaleWeight Weight range for a female of this dog type.
     * @property hypoallergenic Boolean indicating if the dog is hypoallergenic.
     */
    @Serializable
    data class DogAttributes(
        val name: String,
        val description: String,
        val life: DogRange,
        @SerialName("male_weight") val maleWeight: DogRange,
        @SerialName("female_weight") val femaleWeight: DogRange,
        val hypoallergenic: Boolean,
    ) {
        /**
         * @property min Minimum value of the range.
         * @property max Maximum value of the range.
         */
        @Serializable
        data class DogRange(
            val min: Int,
            val max: Int
        )
    }

    /**
     * @property group The group the dog belongs to (e.g., breed group).
     */
    @Serializable
    data class DogRelationships(
        val group: DogGroup,
    ) {
        /**
         * @property data Details about the group, including its ID and type.
         */
        @Serializable
        data class DogGroup(
            val data: GroupData
        ) {
            /**
             * @property id Identifier for the group.
             * @property type Type of the group, typically describing the classification category.
             */
            @Serializable
            data class GroupData(
                val id: String,
                val type: String
            )
        }
    }
}
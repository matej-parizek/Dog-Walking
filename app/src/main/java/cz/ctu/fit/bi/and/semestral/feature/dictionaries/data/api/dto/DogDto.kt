package cz.ctu.fit.bi.and.semestral.feature.dictionaries.data.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DogDto(
    val id: String,
    val type: String,
    val attributes: DogAttributes,
    val relationships: DogRelationships,
) {
    @Serializable
    data class DogAttributes(
        val name: String,
        val description: String,
        val life: DogRange,
        @SerialName("male_weight") val maleWeight: DogRange,
        @SerialName("female_weight") val femaleWeight: DogRange,
        val hypoallergenic: Boolean,
    ) {
        @Serializable
        data class DogRange(
            val min: Int,
            val max: Int
        )
    }
    @Serializable
    data class DogRelationships(
        val group: DogGroup,
    ){
        @Serializable
        data class DogGroup(
            val data : GroupData
        ){
            @Serializable
            data class GroupData(
                val id:String,
                val type: String
            )
        }
    }
}
package cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
/**
 * Represents a dog entity as stored in the database.
 *
 * @property id The unique identifier of the dog.
 * @property name Name of the dog.
 * @property description A textual description of the dog.
 * @property hypoallergenic Boolean indicating whether the dog is hypoallergenic.
 * @property image URL or URI to an image of the dog.
 * @property group The classification group to which the dog belongs.
 * @property maleWeightMin Minimum weight of a male dog of this breed.
 * @property maleWeightMax Maximum weight of a male dog of this breed.
 * @property femaleWeightMin Minimum weight of a female dog of this breed.
 * @property femaleWeightMax Maximum weight of a female dog of this breed.
 * @property lifeMin The minimum expected lifespan of the breed in years.
 * @property lifeMax The maximum expected lifespan of the breed in years.
 */
@Entity("dog_entity")
data class DogEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val hypoallergenic: Boolean,
    val image: String,
    val group: String,
    val maleWeightMin: Int,
    val maleWeightMax: Int,
    val femaleWeightMin: Int,
    val femaleWeightMax: Int,
    val lifeMin: Int,
    val lifeMax: Int,
)
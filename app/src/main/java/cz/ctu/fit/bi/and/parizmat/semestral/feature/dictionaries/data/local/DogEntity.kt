package cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

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
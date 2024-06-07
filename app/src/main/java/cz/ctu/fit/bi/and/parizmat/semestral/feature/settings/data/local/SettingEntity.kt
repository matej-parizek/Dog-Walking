package cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity class representing the settings stored in the database.
 * This class is annotated for Room database integration, with properties that map directly to the database columns.
 *
 * @param id Unique identifier for the settings entity, serves as the primary key.
 * @param weight Weight of the individual in kilograms, default is 50 kg.
 * @param height Height of the individual in centimeters, default is 160 cm.
 * @param minSteps Minimum number of steps the individual should take daily, default is 5000 steps.
 */
@Entity(tableName = "setting_entity")
data class SettingEntity(
    @PrimaryKey val id: Int = 0,
    val weight: Float = 50f,
    val height : Int = 160,
    val minSteps: Int = 5000
)
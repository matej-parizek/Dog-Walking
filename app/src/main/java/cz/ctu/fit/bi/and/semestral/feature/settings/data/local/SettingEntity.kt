package cz.ctu.fit.bi.and.semestral.feature.settings.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "setting_entity")
data class SettingEntity(
    @PrimaryKey val id: Int = 0,
    val weight: Float = 50f,
    val height : Int = 160,
    val minSteps: Int = 5000
)
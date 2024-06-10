package cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class representing an entity in the 'steps' table in the database.
 *
 * @property steps The number of steps counted, stored as a Long.
 * @property createdAt The unique identifier for each entry, also used as the primary key, storing the date and time of step count recording.
 */
@Entity(tableName = "steps")
data class StepCountEntity(
    @ColumnInfo(name = "steps") val steps: Long,
    @PrimaryKey @ColumnInfo(name = "created_at") val createdAt: String
)
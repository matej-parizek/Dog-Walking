package cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "steps")
data class StepCountEntity(
    @ColumnInfo(name = "steps") val steps: Long,
    @PrimaryKey @ColumnInfo(name = "created_at") val createdAt: String
)
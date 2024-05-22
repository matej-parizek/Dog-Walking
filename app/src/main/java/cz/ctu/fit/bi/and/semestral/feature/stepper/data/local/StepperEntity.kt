package cz.ctu.fit.bi.and.semestral.feature.stepper.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "steps")
data class StepperEntity (
    @ColumnInfo(name = "steps") val steps : Int,
    @ColumnInfo(name = "create_at") val createAt : String
)
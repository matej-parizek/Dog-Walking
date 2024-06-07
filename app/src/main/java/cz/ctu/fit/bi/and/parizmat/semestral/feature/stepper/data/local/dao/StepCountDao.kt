package cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.data.local.StepCountEntity

@Dao
interface StepCountDao {
    @Query("SELECT * FROM steps")
    suspend fun getAll(): List<StepCountEntity>

    @Query("SELECT * FROM steps WHERE created_at >= date(:startDateTime) " +
            "AND created_at < date(:startDateTime, '+1 day')")
    suspend fun loadAllStepsFromToday(startDateTime: String): Array<StepCountEntity>

    @Insert
    suspend fun insertAll(vararg steps: StepCountEntity)

    @Delete
    suspend fun delete(steps: StepCountEntity)
}
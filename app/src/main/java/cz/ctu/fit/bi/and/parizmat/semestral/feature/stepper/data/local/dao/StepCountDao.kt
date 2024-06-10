package cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.data.local.StepCountEntity

@Dao
interface StepCountDao {
    /**
     * Retrieves all step count entries from the 'steps' table.
     *
     * @return A list of all StepCountEntity objects from the database.
     */
    @Query("SELECT * FROM steps")
    suspend fun getAll(): List<StepCountEntity>

    /**
     * Fetches all step count entries recorded on a specific date provided by the parameter.
     * This method is useful for obtaining all steps recorded from the start of the given day until just before the next day.
     *
     * @param startDateTime The start date in 'YYYY-MM-DD' format to filter records by created date.
     * @return An array of StepCountEntity objects containing steps data from the specified start date.
     */
    @Query("SELECT * FROM steps WHERE created_at >= date(:startDateTime) " +
            "AND created_at < date(:startDateTime, '+1 day')")
    suspend fun loadAllStepsFromToday(startDateTime: String): Array<StepCountEntity>

    /**
     * Fetches all step count entries from the 'steps' table.
     * Similar to getAll(), but returns the data as an Array.
     *
     * @return An array of all StepCountEntity objects from the database.
     */
    @Query("SELECT * FROM steps")
    suspend fun loadAllSteps(): Array<StepCountEntity>

    /**
     * Inserts one or more step count entries into the database.
     * Utilizes upsert, which will insert new entries or update existing ones based on primary key conflicts.
     *
     * @param steps Variable number of StepCountEntity objects to be inserted or updated.
     */
    @Upsert
    suspend fun insertAll(vararg steps: StepCountEntity)

    /**
     * Deletes a specific step count entry from the database.
     * The entire row corresponding to the provided entity will be removed.
     *
     * @param steps The StepCountEntity to be deleted.
     */
    @Delete
    suspend fun delete(steps: StepCountEntity)
}
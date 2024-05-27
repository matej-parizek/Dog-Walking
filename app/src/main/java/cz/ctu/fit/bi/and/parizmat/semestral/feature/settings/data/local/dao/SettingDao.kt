package cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.data.local.SettingEntity
import kotlinx.coroutines.flow.Flow

/**
 * Function from database for one entity
 **/
@Dao
interface SettingDao {
    /**
     * Initialize insert and updating data
     *
     * @param entity
     * @see SettingEntity
     */
    @Upsert
    suspend fun insert(entity: SettingEntity)

    /**
     * Checks if the `setting_person` table is empty by querying the count of rows.
     *
     * @return The number of rows in the `setting_person` table.
     */
    @Query("SELECT COUNT(*) FROM setting_entity WHERE id = :id")
    suspend fun isEmpty(id: Int): Int

    /**
     * Retrieves a specific setting from the `setting_person` table.
     * This method retrieves the setting with the specified id.
     * Note: This assumes that the id 0 represents a specific setting in the table, because the database contains only two entries:
     * - 0 for Person Settings
     * - 1 for Dog Settings
     *
     * @param id The id of the setting to retrieve (0 for Person Settings, 1 for Dog Settings).
     * @return A Flow emitting the SettingPersonEntity with the specified id.
     */
    @Query("SELECT * FROM setting_entity WHERE id = :id")
    fun get(id: Int): Flow<SettingEntity?>

    /**
     * Updates the height attribute of a specific setting in the `setting_person` table.
     * This method updates the height value for the setting with the specified id.
     * Note: This assumes that the id 0 represents a specific setting in the table, because the database contains only two entries:
     * - 0 for Person Settings
     * - 1 for Dog Settings
     *
     * @param height The new height value to set.
     * @param id The id of the setting to update (0 for Person Settings, 1 for Dog Settings).
     */
    @Query("UPDATE setting_entity SET height = :height WHERE id = :id")
    suspend fun updateHeight(id: Int, height: Int)

    /**
     * Updates the minSteps attribute of a specific setting in the `setting_person` table.
     * This method updates the minimum steps value for the setting with the specified id.
     * Note: This assumes that the id 0 represents a specific setting in the table, because the database contains only two entries:
     * - 0 for Person Settings
     * - 1 for Dog Settings
     *
     * @param id The id of the setting to update (0 for Person Settings, 1 for Dog Settings).
     * @param steps The new minimum steps value to set.
     */
    @Query("UPDATE setting_entity SET minSteps = :steps WHERE id = :id")
    suspend fun updateSteps(id: Int, steps: Int)

    /**
     * Updates the weight attribute of a specific setting in the `setting_person` table.
     * This method updates the weight value for the setting with the specified id.
     * Note: This assumes that the id 0 represents a specific setting in the table, because the database contains only two entries:
     * - 0 for Person Settings
     * - 1 for Dog Settings
     *
     * @param id The id of the setting to update (0 for Person Settings, 1 for Dog Settings).
     * @param weight The new weight value to set.
     */
    @Query("UPDATE setting_entity SET weight = :weight WHERE id = :id")
    suspend fun updateWeight(id: Int, weight: Int)


}
package cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.data.local

import cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.domain.Setting
import kotlinx.coroutines.flow.Flow

interface SettingLocalDataSource {
    /**
     * Retrieves the settings for a given ID as a Flow. The result can be null if no settings exist for the ID.
     *
     * @param id The unique identifier of the settings to retrieve.
     * @return Flow emitting the Setting instance or null.
     */
    fun get(id: Int): Flow<Setting?>

    /**
     * Inserts new settings into the local data source. If settings with the same ID already exist, they will be overwritten.
     *
     * @param setting The Setting instance to insert.
     */
    suspend fun insert(setting: Setting)

    /**
     * Checks if the settings for the specified ID exist in the local data source.
     *
     * @param id The unique identifier of the settings to check.
     * @return Boolean indicating whether the settings are empty (true) or not (false).
     */
    suspend fun isEmpty(id: Int): Boolean

    /**
     * Updates the height for the specified settings ID in the local data source.
     *
     * @param id The unique identifier of the settings to update.
     * @param height The new height value to update.
     */
    suspend fun updateHeight(id: Int, height: Int)

    /**
     * Updates the weight for the specified settings ID in the local data source.
     *
     * @param id The unique identifier of the settings to update.
     * @param weight The new weight value to update.
     */
    suspend fun updateWeight(id: Int, weight: Int)

    /**
     * Updates the minimum daily steps for the specified settings ID in the local data source.
     *
     * @param id The unique identifier of the settings to update.
     * @param steps The new minimum steps value to update.
     */
    suspend fun updateSteps(id: Int, steps: Int)
}
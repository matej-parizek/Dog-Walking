package cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.data

import cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.domain.Setting
import kotlinx.coroutines.flow.Flow

/**
 * Interface for the repository handling the settings data in the database.
 * This repository defines methods for retrieving and updating settings
 * specifically for two types of entities: humans and dogs. The 'id' parameter
 * for these methods should only take two values: 0 for humans and 1 for dog.
 *
 */
interface SettingRepository {
    /**
     * Retrieves the settings for a specific ID.
     *
     * @param id The unique identifier for the settings to retrieve.
     * @return Flow emitting the Setting instance or null if the settings are not found.
     */
    fun getSetting(id: Int): Flow<Setting?>

    /**
     * Asynchronously updates the height for a specified setting ID.
     *
     * @param id The unique identifier of the settings to update.
     * @param height The new height value to set.
     */
    suspend fun updateHeight(id: Int, height: Float)

    /**
     * Asynchronously updates the number of minimum steps for a specified setting ID.
     *
     * @param id The unique identifier of the settings to update.
     * @param steps The new minimum steps value to set.
     */
    suspend fun updateSteps(id: Int, steps: Float)

    /**
     * Asynchronously updates the weight for a specified setting ID.
     *
     * @param id The unique identifier of the settings to update.
     * @param weight The new weight value to set.
     */
    suspend fun updateWeight(id: Int, weight: Float)

    /**
     * Initializes settings for a specific ID. Involving setting default values and
     * preparing the settings for first-time use.
     *
     * @param id The unique identifier of the settings to initialize.
     */
    suspend fun initialize(id: Int)
}
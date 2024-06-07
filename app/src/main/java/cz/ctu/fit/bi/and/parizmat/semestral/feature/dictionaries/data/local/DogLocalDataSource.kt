package cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.local

import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.domain.Dog
import kotlinx.coroutines.flow.Flow

/**
 * Interface defining the local data source operations for managing dog data in the local storage.
 */
interface DogLocalDataSource {
    /**
     * Inserts or updates a list of dog entities in the local database. This method ensures that
     * existing records are updated if they already exist or added if they do not.
     *
     * @param data List of dog entities to be upserted.
     */
    suspend fun upsertAll(data: List<Dog>)

    /**
     * Inserts or updates a single dog entity in the local database.
     *
     * @param data The dog entity to be upserted.
     */
    suspend fun upsert(data: Dog)

    /**
     * Provides a continuous stream of all dogs stored in the local database.
     * This method utilizes a Flow to emit the current list of dogs, updating with any changes to the data.
     *
     * @return Flow emitting the list of all stored dogs.
     */
    fun getDogsStream(): Flow<List<Dog>>

    /**
     * Retrieves a specific dog by its ID, providing a continuous data stream that updates with any changes.
     *
     * @param id The unique identifier of the dog to fetch.
     * @return Flow emitting the dog entity or null if it does not exist.
     */
    fun getDog(id: String): Flow<Dog?>

    /**
     * Streams dogs that match a given search query, using a live data stream to reflect updates.
     * This method is particularly useful for implementing search functionalities within the application.
     *
     * @param query The search term used to filter the dogs.
     * @return Flow emitting a list of dogs that match the query.
     */
    fun filterByQueryStream(query: String): Flow<List<Dog>>

    /**
     * Deletes all dog entities from the local database. This operation is typically used for clearing
     * data during application resets or updates.
     */
    suspend fun deleteAll()

    /**
     * Returns the total number of dogs stored in the local database.
     *
     * @return The count of stored dog entities.
     */
    suspend fun count(): Int
}
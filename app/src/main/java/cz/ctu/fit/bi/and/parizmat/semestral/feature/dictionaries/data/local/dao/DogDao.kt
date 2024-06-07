package cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.local.DogEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for managing dog entities in the database.
 */
@Dao
interface DogDao {
    /**
     * Inserts or updates a list of dog entities in the database. If a dog already exists, it is updated.
     *
     * @param dogs The list of dog entities to be updated or inserted.
     */
    @Upsert
    suspend fun upsertAll(dogs: List<DogEntity>)

    /**
     * Inserts or updates a single dog entity in the database. If the dog already exists, it is updated.
     *
     * @param dog The dog entity to be updated or inserted.
     */
    @Upsert
    suspend fun upsert(dog: DogEntity)

    /**
     * Returns a Flow of a list of all dog entities sorted by their names in ascending order.
     * This method provides a continuous data stream that updates with any changes to the data.
     *
     * @return A Flow that emits the current list of dogs each time the data changes.
     */
    @Query("SELECT * FROM dog_entity ORDER BY dog_entity.name ASC")
    fun getDogsStream(): Flow<List<DogEntity>>

    /**
     * Retrieves a specific dog entity by its ID, emitting changes if the data updates.
     *
     * @param id The unique identifier of the dog to fetch.
     * @return A Flow that emits the dog entity or null if it does not exist.
     */
    @Query("SELECT * FROM dog_entity WHERE id =:id")
    fun getDog(id: String): Flow<DogEntity?>

    /**
     * Returns a Flow of dog entities that match a given query. This method is useful for implementing search features.
     *
     * @param query The search query to match against dog names.
     * @return A Flow that emits a list of dogs that match the query.
     */
    @Query("SELECT * FROM dog_entity WHERE name LIKE  '%' || :query || '%' ")
    fun filterByQueryStream(query: String): Flow<List<DogEntity>>

    /**
     * Deletes all dog entities from the database. Useful for clearing data during app resets or updates.
     */
    @Query("DELETE FROM dog_entity")
    suspend fun deleteAll()

    /**
     * Counts the total number of dog entities in the database.
     *
     * @return The total count of dog entities.
     */
    @Query("SELECT COUNT(*) FROM dog_entity")
    suspend fun count(): Int
}
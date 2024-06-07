package cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data

import cz.ctu.fit.bi.and.parizmat.semestral.core.data.DataError
import cz.ctu.fit.bi.and.parizmat.semestral.core.data.Response
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.domain.Dog
import kotlinx.coroutines.flow.Flow

/**
 * Interface defining the repository operations for managing dog data.
 *
 */
interface DogRepository {
    /**
     * Retrieves a flow of dogs from the repository. This function handles data fetching from
     * various sources and error management, encapsulating it within a `Response` object.
     *
     * @return Response encapsulating a Flow of a list of Dogs or a DataError on failure.
     */
    suspend fun getDogs(): Response<Flow<List<Dog>>, DataError>

    /**
     * Fetches the latest dog data from remote sources to update the local database.
     * Handles potential data fetching errors by encapsulating the operation result in a `Response`.
     *
     * @return Response indicating success (Unit) or failure (DataError).
     */
    suspend fun fetch(): Response<Unit, DataError>

    /**
     * Retrieves a filtered list of dogs based on a query string. This operation is typically
     * performed on data already fetched and stored locally.
     *
     * @param query The string used to filter the dogs.
     * @return Flow of a list of Dogs that match the query.
     */
    suspend fun filterByQuery(query: String): Flow<List<Dog>>

    /**
     * Deletes all dog data from the local storage. This operation may be necessary during
     * app resets, updates, or when refreshing data from a remote source.
     */
    suspend fun deleteAll()

    /**
     * Fetches a specific dog by its ID, handling data integrity and errors. This method
     * provides a robust way to handle data retrieval with error checking.
     *
     * @param id The unique identifier of the dog to fetch.
     * @return Response encapsulating a Flow of Dog or null if not found, and DataError on failure.
     */
    suspend fun getDog(id: String): Response<Flow<Dog?>, DataError>

    /**
     * Requests a random image for a specified dog, encapsulating the operation in a Response
     * to manage errors effectively.
     *
     * @param dog The Dog object for which the image is requested.
     * @return Response indicating success (Unit) or failure (DataError).
     */
    suspend fun randomImage(dog: Dog): Response<Unit, DataError>
}
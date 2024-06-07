package cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.api

import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.api.dto.DogDto
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.api.dto.GroupDto

/**
 * Interface defining the data access operations for fetching data about dogs and their groups from a remote source.
 */
interface DogRemoteDataSource {
    /**
     * Retrieves a paginated list of dogs from a remote source.
     *
     * @param page The pagination index specifying which page of dogs to retrieve.
     * @return A list of `DogDto`, representing individual dogs.
     */
    suspend fun getDogs(page: Int): List<DogDto>

    /**
     * Fetches all available dog groups from the remote source.
     *
     * @return A list of `GroupDto`, representing different dog groups.
     */
    suspend fun getGroups(): List<GroupDto>

    /**
     * Fetches an image URL for a specific breed and name.
     * @param breed The breed of the dog for which the image is requested.
     * @param name The specific name or identifier within the breed for which the image is requested.
     * @return A String URL pointing to the requested dog image.
     */
    suspend fun getImage(breed: String, name: String): String

    /**
     * Fetches an image URL for a one word named breed.
     *
     * @param breed The breed of the dog for which the image is requested.
     * @return A String URL pointing to the requested dog image.
     */
    suspend fun getImage(breed: String): String
}
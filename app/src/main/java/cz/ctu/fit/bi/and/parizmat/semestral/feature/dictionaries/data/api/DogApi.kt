package cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.api

import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.api.dto.DogResponse
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.api.dto.GroupResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface for the API service that interacts with an endpoint to fetch dog breeds and groups.
 */
interface DogApi {
    /**
     * Fetches a paginated list of dog breeds from the API.
     * The page number is used to retrieve a specific subset of the dog breed data.
     *
     * @param number The page number for pagination, allowing the caller to specify which batch of dog breeds to retrieve.
     * @return A `DogResponse` object containing a list of dog breeds as specified by the pagination.
     */
    @GET("breeds")
    suspend fun getBreeds(@Query("page[number]") number: Int): DogResponse

    /**
     * Retrieves all dog groups from the API without pagination, because its only page.
     * This method is typically used to fetch a complete list of available groups.
     *
     * @return A `GroupResponse` object containing a list of all dog groups.
     */
    @GET("groups")
    suspend fun getGroups(): GroupResponse
}
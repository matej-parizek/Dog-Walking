package cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.api

import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.api.dto.ImageDto
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Interface defining the API endpoints for fetching images of dog breeds.
 */
interface ImageApi {
    /**
     * Retrieves an image for a specific dog breed and name.
     *
     * @param breed The breed of the dog for which the image is requested.
     * @param name The specific name or identifier within the breed for which the image is requested.
     * @return An `ImageDto` object containing the image data and status as fetched from the API.
     */
    @GET("breed/{breed}/{name}/images")
    suspend fun getImage(@Path("breed") breed: String, @Path("name") name: String): ImageDto

    /**
     * Retrieves an image for a dog breed without specifying a particular name or variation.
     *
     * @param breed The breed of the dog for which the image is requested.
     * @return An `ImageDto` object containing the image data and status as fetched from the API.
     */
    @GET("breed/{breed}/images")
    suspend fun getImage(@Path("breed") breed: String): ImageDto
}
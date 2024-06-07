package cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.api.dto

import kotlinx.serialization.Serializable

/**
 * Represents a data transfer object for images, commonly used in API responses where images are involved.
 *
 * @property message A list of strings that might contain URLs to images or messages indicating the result of the image retrieval attempt.
 * @property status Indicates the status of the API response, such as 'success' or 'error', reflecting the outcome of the request.
 */
@Serializable
data class ImageDto(
    val message: List<String>,
    val status: String,
)
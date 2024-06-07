package cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.api.dto

import kotlinx.serialization.Serializable

/**
 * Represents the response structure received from an API call that returns group data.
 *
 * @property data A list of `GroupDto` instances, where each `GroupDto` provides detailed information about a specific group,
 * including its identity and attributes as defined in the `GroupDto` class.
 */
@Serializable
data class GroupResponse(
    val data: List<GroupDto>
)
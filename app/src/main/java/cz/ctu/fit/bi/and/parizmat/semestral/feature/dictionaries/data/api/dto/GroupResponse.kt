package cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.api.dto

import kotlinx.serialization.Serializable

@Serializable
data class GroupResponse(
    val data : List<GroupDto>
)
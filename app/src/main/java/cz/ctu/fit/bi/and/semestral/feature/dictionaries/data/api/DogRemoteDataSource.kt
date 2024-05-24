package cz.ctu.fit.bi.and.semestral.feature.dictionaries.data.api

import androidx.lifecycle.SavedStateHandle
import cz.ctu.fit.bi.and.semestral.feature.dictionaries.data.mapper.toDog
import cz.ctu.fit.bi.and.semestral.feature.dictionaries.domain.Dog

class DogRemoteDataSource(
    private val api: DogApi
) {
    suspend fun getDogs(page: Int): List<Dog> =
        api.getBreeds(page).data.map {
            val group = getGroup(it.relationships.group.data.id)
            it.toDog(
                group = group.data.attributes.name
            )
        }

    private suspend fun getGroup(id: String) = api.getGroups(id)

}
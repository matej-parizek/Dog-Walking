package cz.ctu.fit.bi.and.semestral.feature.dictionaries.data.api

import cz.ctu.fit.bi.and.semestral.feature.dictionaries.data.mapper.toDog
import cz.ctu.fit.bi.and.semestral.feature.dictionaries.domain.Dog

class DogRemoteDataSource(
    private val api: DogApi
) {
    suspend fun getDogs(page : Int) : List<Dog> =
            api.getBreeds(page).data.map {
                it.toDog(
                    "error"
                )}

}
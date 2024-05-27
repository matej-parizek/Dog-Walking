package cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.api

import androidx.lifecycle.SavedStateHandle
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.api.dto.DogDto
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.api.dto.DogResponse
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.mapper.toDog
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.domain.Dog

class DogRemoteDataSource(
    private val api: DogApi
) {
    suspend fun getDogs(page: Int): List<DogDto> = api.getBreeds(page).data

    suspend fun getGroups() = api.getGroups().data

}
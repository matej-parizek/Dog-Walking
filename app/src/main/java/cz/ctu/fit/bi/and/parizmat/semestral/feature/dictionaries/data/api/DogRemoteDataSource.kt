package cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.api

import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.api.dto.DogDto
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.api.dto.GroupDto

interface DogRemoteDataSource{
    suspend fun getDogs(page: Int): List<DogDto>
    suspend fun getGroups(): List<GroupDto>

    suspend fun getImage(breed: String, name: String): String
    suspend fun getImage(breed: String): String
}
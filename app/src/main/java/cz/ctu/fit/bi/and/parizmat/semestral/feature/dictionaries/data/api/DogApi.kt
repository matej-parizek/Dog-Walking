package cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.api

import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.api.dto.DogResponse
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.api.dto.GroupDto
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.api.dto.GroupResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DogApi {
    @GET("breeds")
    suspend fun getBreeds(@Query("page[number]")number: Int ): DogResponse

    @GET("groups")
    suspend fun getGroups(): GroupResponse
}
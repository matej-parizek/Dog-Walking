package cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.api

import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.api.dto.ImageDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ImageApi {
    @GET("breed/{breed}/{name}/images")
    suspend fun getImage(@Path("breed")breed:String , @Path("name") name: String) : ImageDto
    @GET("breed/{breed}/images")
    suspend fun getImage(@Path("breed")breed:String) : ImageDto
}
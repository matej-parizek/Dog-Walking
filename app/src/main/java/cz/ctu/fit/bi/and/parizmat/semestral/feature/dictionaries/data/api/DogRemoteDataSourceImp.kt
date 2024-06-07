package cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.api

import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.api.dto.DogDto

class DogRemoteDataSourceImp(
    private val dogApi: DogApi,
    private val imageApi: ImageApi
): DogRemoteDataSource {
    override suspend fun getDogs(page: Int): List<DogDto> = dogApi.getBreeds(page).data

    override suspend fun getGroups() = dogApi.getGroups().data

    override suspend fun getImage(breed: String, name: String): String = imageApi.getImage(breed = breed, name = name).message[0]
    override suspend fun getImage(breed: String): String = imageApi.getImage(breed = breed).message[0]
}
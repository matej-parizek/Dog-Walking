package cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data

import cz.ctu.fit.bi.and.parizmat.semestral.core.data.DataError
import cz.ctu.fit.bi.and.parizmat.semestral.core.data.Response
import cz.ctu.fit.bi.and.parizmat.semestral.core.data.db.EmptyDatabaseException
import cz.ctu.fit.bi.and.parizmat.semestral.core.data.handleExceptionApi
import cz.ctu.fit.bi.and.parizmat.semestral.core.data.handleExceptionDatabase
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.api.DogRemoteDataSource
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.api.dto.DogDto
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.local.DogLocalDataSource
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.mapper.toDog
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.domain.Dog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import retrofit2.HttpException

class DogRepositoryImp(
    private val dogRemoteDataSource: DogRemoteDataSource,
    private val dogLocalDataSource: DogLocalDataSource,
) : DogRepository {

    override suspend fun fetch(): Response<Unit, DataError> {
        return try {
            val listOfDogs = mutableListOf<DogDto>()
            for (page in 1..29) {
                listOfDogs.addAll(dogRemoteDataSource.getDogs(page))
            }
            val listOfGroup = dogRemoteDataSource.getGroups()

            val dogs = listOfDogs.map {
                it.toDog { idDogGroup ->
                    val groupMap = listOfGroup.associateBy { idGroup -> idGroup.id }
                    groupMap[idDogGroup]?.attributes?.name
                }
            }
            dogLocalDataSource.upsertAll(dogs)
            Response.Success(Unit)
        } catch (e: Exception) {
            handleExceptionApi(e)
        }
    }

    override suspend fun deleteAll() {
        dogLocalDataSource.deleteAll()
    }

    override suspend fun getDogs(): Response<Flow<List<Dog>>, DataError> {
        return try {
            dogLocalDataSource.count().takeIf { 0 == it }?.let { throw EmptyDatabaseException() }
            Response.Success(dogLocalDataSource.getDogsStream())
        } catch (e: Exception) {
            handleExceptionDatabase(e)
        }
    }

    //TODO: set to Error Screen
    override suspend fun filterByQuery(query: String): Flow<List<Dog>> {
        return try {
            dogLocalDataSource.filterByQueryStream(query)
                .catch { emit(emptyList()) }
        } catch (e: Throwable) {
            flowOf(emptyList())
        }
    }

    override suspend fun getDog(id: String): Response<Flow<Dog?>, DataError> {
        return try {
            val result = dogLocalDataSource.getDog(id = id)
            Response.Success(result)
        } catch (e: Exception) {
            handleExceptionDatabase(e)
        }
    }

    override suspend fun randomImage(dog: Dog): Response<Unit, DataError> {
        return try {

            val data = dog.name.lowercase().split(' ')
            if (data.size == 2)
                dog.image = dogRemoteDataSource.getImage(breed = data[1], name = data[0])
            if (data.size == 1)
                dog.image = dogRemoteDataSource.getImage(breed = data[0])
            dogLocalDataSource.upsert(dog)
            Response.Success(Unit)
        }catch (e: HttpException){
            if(e.code() != 404)
                throw e
            Response.Success(Unit)
        }catch (e: Exception){
            handleExceptionApi(e)
        }
    }
}

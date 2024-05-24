package cz.ctu.fit.bi.and.semestral.feature.dictionaries.data

import cz.ctu.fit.bi.and.semestral.feature.dictionaries.data.api.DogRemoteDataSource
import cz.ctu.fit.bi.and.semestral.feature.dictionaries.data.local.DogLocalDataSource
import cz.ctu.fit.bi.and.semestral.feature.dictionaries.domain.Dog
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf

class DogRepositoryImp(
    private val dogRemoteDataSource: DogRemoteDataSource,
    private val dogLocalDataSource: DogLocalDataSource,
) : DogRepository {

    override suspend fun fetch() {
        for(page in 1..29){
            dogLocalDataSource.upsertAll(dogRemoteDataSource.getDogs(page))
        }
    }

    //TODO: set to ERROR Screen
    override suspend fun getDogs(): Flow<List<Dog>> {
        return try {
            dogLocalDataSource.getDogsStream().catch { emit(emptyList()) }
        } catch (e: Throwable) {
            flowOf(emptyList())
        }
    }

    //TODO: set to Error Screen
    override suspend fun filterByQuery(query: String): Flow<List<Dog>> {
        return try {
            dogLocalDataSource.filterByQueryStream(query)
                .catch { emit(emptyList()) }
        }catch (e: Throwable){
            flowOf(emptyList())
        }
    }
}
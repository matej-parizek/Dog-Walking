package cz.ctu.fit.bi.and.semestral.feature.dictionaries.data

import cz.ctu.fit.bi.and.semestral.feature.dictionaries.data.api.DogApi
import cz.ctu.fit.bi.and.semestral.feature.dictionaries.data.api.DogRemoteDataSource
import cz.ctu.fit.bi.and.semestral.feature.dictionaries.data.local.DogLocalDataSource
import cz.ctu.fit.bi.and.semestral.feature.dictionaries.domain.Dog
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
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

    override suspend fun getDogs(): Flow<List<Dog>> {
        return try {
            dogLocalDataSource.getDogsStream().catch { emit(emptyList()) }
        } catch (e: Throwable) {
            flowOf(emptyList())
        }
    }
}
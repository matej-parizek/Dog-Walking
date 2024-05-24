package cz.ctu.fit.bi.and.semestral.feature.dictionaries.data.local

import cz.ctu.fit.bi.and.semestral.feature.dictionaries.data.local.dao.DogDao
import cz.ctu.fit.bi.and.semestral.feature.dictionaries.data.mapper.toDog
import cz.ctu.fit.bi.and.semestral.feature.dictionaries.data.mapper.toDogEntity
import cz.ctu.fit.bi.and.semestral.feature.dictionaries.domain.Dog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DogLocalDataSourceImp(
    private val dao: DogDao
) : DogLocalDataSource {
    override suspend fun upsertAll(data: List<Dog>) = dao.upsertAll(data.map { it.toDogEntity() })
    override fun getDogsStream(): Flow<List<Dog>> = dao.getDogsStream()
        .map { it.map { it.toDog() } }

    override fun filterByQueryStream(query: String): Flow<List<Dog>> =
        dao.filterByQueryStream(query)
            .map { it.map { it.toDog() } }

}
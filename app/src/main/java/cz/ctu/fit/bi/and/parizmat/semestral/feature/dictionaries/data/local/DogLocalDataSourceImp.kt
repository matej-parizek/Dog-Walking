package cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.local

import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.local.dao.DogDao
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.mapper.toDog
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.mapper.toDogEntity
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.domain.Dog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DogLocalDataSourceImp(
    private val dao: DogDao
) : DogLocalDataSource {
    override suspend fun upsertAll(data: List<Dog>) = dao.upsertAll(data.map { it.toDogEntity() })
    override fun getDogsStream(): Flow<List<Dog>> = dao.getDogsStream()
        .map { it.map { dogEntity -> dogEntity.toDog() } }

    override fun filterByQueryStream(query: String): Flow<List<Dog>> =
        dao.filterByQueryStream(query)
            .map { it.map { dogEntity -> dogEntity.toDog() } }

    override suspend fun deleteAll()  = dao.deleteAll()
    override suspend fun count(): Int = dao.count()

    override fun getDog(id: String): Flow<Dog?> = dao.getDog(id).map { it?.toDog() }

    override suspend fun upsert(data: Dog) = dao.upsert(data.toDogEntity())
}
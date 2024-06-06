package cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.local

import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.domain.Dog
import kotlinx.coroutines.flow.Flow

interface DogLocalDataSource {
    suspend fun upsertAll(data: List<Dog>)
    fun getDogsStream(): Flow<List<Dog>>
    fun filterByQueryStream(query: String): Flow<List<Dog>>
    suspend fun deleteAll()
    suspend fun count(): Int
}
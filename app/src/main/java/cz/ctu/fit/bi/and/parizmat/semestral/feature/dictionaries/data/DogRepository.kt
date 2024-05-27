package cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data

import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.domain.Dog
import kotlinx.coroutines.flow.Flow

interface DogRepository {
    suspend fun getDogs(): Flow<List<Dog>>
    suspend fun fetch()
    suspend fun filterByQuery(query: String): Flow<List<Dog>>
    suspend fun clear()
}
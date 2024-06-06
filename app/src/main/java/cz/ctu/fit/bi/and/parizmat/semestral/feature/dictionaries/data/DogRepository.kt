package cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data

import cz.ctu.fit.bi.and.parizmat.semestral.core.data.DataError
import cz.ctu.fit.bi.and.parizmat.semestral.core.data.Response
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.domain.Dog
import kotlinx.coroutines.flow.Flow

interface DogRepository {
    suspend fun getDogs(): Response<Flow<List<Dog>>,DataError>
    suspend fun fetch() : Response<Unit, DataError>
    suspend fun filterByQuery(query: String): Flow<List<Dog>>
    suspend fun deleteAll()
}
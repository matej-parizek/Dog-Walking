package cz.ctu.fit.bi.and.semestral.feature.settings.data

import cz.ctu.fit.bi.and.semestral.feature.settings.domain.Setting
import kotlinx.coroutines.flow.Flow


interface SettingRepository {
    fun getSetting(id: Int): Flow<Setting?>
    suspend fun updateHeight(id: Int, height: Float)
    suspend fun updateSteps(id: Int, steps: Float)
    suspend fun updateWeight(id: Int, weight: Float)
    suspend fun initialize(id: Int)
}
package cz.ctu.fit.bi.and.semestral.feature.settings.data.local

import cz.ctu.fit.bi.and.semestral.feature.settings.domain.Setting
import kotlinx.coroutines.flow.Flow

interface SettingLocalDataSource {
    fun get(id: Int): Flow<Setting?>
    suspend fun insert(setting: Setting)
    suspend fun isEmpty(id: Int): Boolean
    suspend fun updateHeight(id: Int, height: Int)
    suspend fun updateWeight(id: Int, weight: Int)
    suspend fun updateSteps(id: Int, steps: Int)
}
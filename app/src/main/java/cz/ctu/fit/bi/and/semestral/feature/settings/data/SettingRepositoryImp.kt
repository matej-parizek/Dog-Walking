package cz.ctu.fit.bi.and.semestral.feature.settings.data

import cz.ctu.fit.bi.and.semestral.feature.settings.data.local.SettingLocalDataSource
import cz.ctu.fit.bi.and.semestral.feature.settings.domain.Setting
import kotlinx.coroutines.flow.Flow
import kotlin.math.roundToInt

class SettingRepositoryImp(
    private val localSettingsSource: SettingLocalDataSource,
) : SettingRepository {
    override fun getSetting(id: Int): Flow<Setting?> =
        localSettingsSource.get(id)

    override suspend fun updateHeight(id: Int, height: Float) =
        localSettingsSource.updateHeight(id = id, height = height.roundToInt())

    override suspend fun updateSteps(id: Int, steps: Float) =
        localSettingsSource.updateSteps(id = id, steps = steps.roundToInt())

    override suspend fun updateWeight(id: Int, weight: Float) =
        localSettingsSource.updateWeight(id = id, weight = weight.roundToInt())

    override suspend fun initialize(id: Int) {
        if (localSettingsSource.isEmpty(id)) {
            if (id == 0) {
                val setting = Setting(id = id)
                localSettingsSource.insert(setting)
            } else {
                val setting = Setting(height = 1, id = id)
                localSettingsSource.insert(setting)
            }
        }
    }


}
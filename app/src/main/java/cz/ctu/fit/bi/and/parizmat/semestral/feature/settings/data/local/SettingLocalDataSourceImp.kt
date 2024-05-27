package cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.data.local

import cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.data.local.dao.SettingDao
import cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.data.mapper.toDomain
import cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.data.mapper.toEntity
import cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.domain.Setting
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class SettingLocalDataSourceImp(
    private val dao: SettingDao,
) : SettingLocalDataSource {
    override fun get(id: Int): Flow<Setting?> = dao.get(id).map { it?.toDomain() }

    override suspend fun insert(setting: Setting) =
        dao.insert(setting.toEntity())

    override suspend fun isEmpty(id: Int): Boolean = dao.isEmpty(id) < 1
    override suspend fun updateHeight(id: Int, height: Int) =
        dao.updateHeight(id = id, height = height)

    override suspend fun updateWeight(id: Int, weight: Int) =
        dao.updateWeight(id = id, weight = weight)

    override suspend fun updateSteps(id: Int, steps: Int) =
        dao.updateSteps(id = id, steps = steps)

}
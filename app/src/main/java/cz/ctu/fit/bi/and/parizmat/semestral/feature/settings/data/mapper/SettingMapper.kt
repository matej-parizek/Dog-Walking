package cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.data.mapper

import cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.data.local.SettingEntity
import cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.domain.Setting


fun Setting.toEntity(): SettingEntity {
    return SettingEntity(
        id = id,
        weight = weight,
        height = height,
        minSteps = minSteps
    )
}

fun SettingEntity.toDomain(): Setting {
    return Setting(
        id = id,
        weight = weight,
        height = height,
        minSteps = minSteps
    )
}

package cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.data.mapper

import cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.data.local.SettingEntity
import cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.domain.Setting

/**
 * Extension function for the Setting class to convert it into a SettingEntity.
 * This allows for easy transformation of domain model instances into database entities
 *
 * @return SettingEntity An instance of SettingEntity with values copied from this Setting instance.
 */
fun Setting.toEntity(): SettingEntity {
    return SettingEntity(
        id = id,
        weight = weight,
        height = height,
        minSteps = minSteps
    )
}

/**
 * Extension function for the SettingEntity class to convert it into a Setting.
 * This function facilitates the transformation of database entities back into domain models
 *
 * @return Setting An instance of Setting with values copied from this SettingEntity instance.
 */
fun SettingEntity.toDomain(): Setting {
    return Setting(
        id = id,
        weight = weight,
        height = height,
        minSteps = minSteps
    )
}

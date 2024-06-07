package cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.presentation

import cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.domain.Setting

/**
 * Data class that encapsulates the state of settings for an individual.
 * This class is used to manage the settings data, allowing for nullable state representation.
 *
 * @param settings An instance of the Setting class or null if no settings have been defined.
 */
data class SettingState(
    val settings: Setting? = null,
)
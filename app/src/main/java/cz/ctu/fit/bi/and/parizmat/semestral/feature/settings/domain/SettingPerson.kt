package cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.domain

data class Setting(
    val id: Int = 0,
    val height: Int = 160,
    val weight: Float = 60f,
    val minSteps: Int = 5000
)
package cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.domain

import cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.ScreenStateEntity

data class Dog(
    val id: String = "Error",
    val name: String = "Error",
    val description: String = "Error",
    val hypoallergenic: Boolean = false,
    var image: String = "Error",
    val group: String = "Error",
    val maleWeight: Range = Range(0, 0),
    val femaleWeight: Range = Range(0, 0),
    val life: Range = Range(0, 0),
) : ScreenStateEntity {
    data class Range(
        val min: Int,
        val max: Int,
    )
}

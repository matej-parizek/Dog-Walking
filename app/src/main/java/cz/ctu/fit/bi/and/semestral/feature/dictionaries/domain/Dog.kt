package cz.ctu.fit.bi.and.semestral.feature.dictionaries.domain

data class Dog(
    val id: String = "Error",
    val name: String = "Error",
    val description: String = "Error",
    val hypoallergenic: Boolean = false,
    val image: String = "Error",
    val group: String = "Error",
    val maleWeight: Range = Range(0, 0),
    val femaleWeight: Range = Range(0, 0),
    val life: Range = Range(0, 0),
) {
    data class Range(
        val min: Int,
        val max: Int,
    )
}

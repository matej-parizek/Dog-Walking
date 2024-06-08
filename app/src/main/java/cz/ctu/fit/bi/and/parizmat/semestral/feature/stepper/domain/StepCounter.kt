package cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.domain

data class StepCounter(
    val steps: Long = 10000,
    val evaluation: Double = 0.0,
    val kcal: Double = 2.0,
    val progress: Float = 0.5f
) {
}
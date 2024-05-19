package cz.ctu.fit.bi.and.semetral.core.ui

sealed class Screens(val route: String) {
    data object Stepper : Screens("stepper")
    data object Stats : Screens("stats")
}
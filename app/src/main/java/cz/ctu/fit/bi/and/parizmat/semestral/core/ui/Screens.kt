package cz.ctu.fit.bi.and.parizmat.semestral.core.ui

sealed class Screens(val route: String) {
    data object Stepper : Screens("stepper")
    data object Stats : Screens("stats")
    data object Settings : Screens("settings")
    data object SettingsDog : Screens("settingsDog")
    data object Dictionaries : Screens("dictionaries")
    data object Error : Screens("error")
}
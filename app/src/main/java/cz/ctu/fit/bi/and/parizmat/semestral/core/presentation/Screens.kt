package cz.ctu.fit.bi.and.parizmat.semestral.core.presentation

import cz.ctu.fit.bi.and.parizmat.semestral.core.data.Error

interface ScreenStateEntity

typealias ErrorState = Error

sealed interface ScreenState<out T : ScreenStateEntity> {
    data object Loading : ScreenState<Nothing>
    data class Error(val data: ErrorState) : ScreenState<Nothing>
    data class Loaded<out T : ScreenStateEntity>(val data: T) : ScreenState<T>
}

sealed class Screens(val route: String) {
    data object Stepper : Screens("stepper")
    data object Stats : Screens("stats")
    data object Settings : Screens("settings")
    data object SettingsDog : Screens("settingsDog")
    data object Dictionaries : Screens("dictionaries")
    data object Error : Screens("error")
    data class Detail(val id: String) : Screens("detail/$id"){
        companion object {
            const val ID = "id"
        }
    }
}
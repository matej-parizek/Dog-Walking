package cz.ctu.fit.bi.and.parizmat.semestral.core.presentation

import cz.ctu.fit.bi.and.parizmat.semestral.core.data.Error

interface ScreenStateEntity

typealias ErrorState = Error

/**
 * Sealed interface defining different states a screen can be in within the application.
 * Utilizing a sealed interface allows exhaustive handling of all possible states, making the UI management
 * more predictable and safer from runtime exceptions related to unhandled states.
 *
 * States:
 * - Loading: Represents a loading state, indicating that some data or operation is in progress and the result is not yet available.
 * - Error: Represents an error state, containing an error object detailing what went wrong during the operation or data fetching.
 * - Loaded: Represents a state where data has successfully been loaded and is ready to be displayed.
 *
 * @param T The type of data expected in the Loaded state, constrained to types that implement ScreenStateEntity.
 */
sealed interface ScreenState<out T : ScreenStateEntity> {
    /**
     * Represents a loading state with no data. This is typically used when the application is fetching or processing data.
     */
    data object Loading : ScreenState<Nothing>

    /**
     * Represents an error state with an associated ErrorState object detailing the nature of the error.
     * This state is used to convey error information to the user, such as network failures or data processing errors.
     *
     * @param data The error object containing details about the error that occurred.
     */
    data class Error(val data: ErrorState) : ScreenState<Nothing>

    /**
     * Represents a successfully loaded state, containing the data to be displayed or processed.
     * This state indicates that the necessary data has been successfully fetched and is ready for use.
     *
     * @param data The actual data loaded, constrained to types implementing ScreenStateEntity.
     */
    data class Loaded<out T : ScreenStateEntity>(val data: T) : ScreenState<T>
}

/**
 * Sealed class representing all the screens used in the application's navigation system.
 *
 * @param route The navigation path associated with the screen, used for routing in the app's navigation system.
 */
sealed class Screens(val route: String) {
    data object Stepper : Screens("stepper")
    data object Settings : Screens("settings")
    data object SettingsDog : Screens("settingsDog")
    data object Dictionaries : Screens("dictionaries")
    data object Error : Screens("error")

    /**
     * Represents a detailed view screen that requires an ID to fetch specific data.
     * The route for this screen includes a dynamic segment that can be replaced with an actual ID at runtime.
     *
     * @param id The unique identifier for the item whose details are to be displayed.
     */
    data class Detail(val id: String) : Screens("detail/$id"){

        companion object {
            const val ID = "id"
        }
    }
}
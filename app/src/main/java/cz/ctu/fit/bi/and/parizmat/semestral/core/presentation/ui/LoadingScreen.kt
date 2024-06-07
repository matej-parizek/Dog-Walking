package cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.ScreenState
import cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.ScreenStateEntity

/**
 * Composable function that renders different UI states based on the screen state of the application.
 * It dynamically switches between loading, loaded content, and error states, providing a common
 * interface for handling asynchronous data fetching UI patterns.
 *
 * @param T The type of data expected when the screen is in the loaded state, constrained to types that implement ScreenStateEntity.
 * @param screenState The current state of the screen, which can be one of Loading, Loaded, or Error.
 * @param screenFunction A composable function that is executed to render the UI when the data is successfully loaded.
 */
@Composable
fun <T : ScreenStateEntity> LoadingScreen(
    screenState: ScreenState<T>,
    screenFunction: @Composable (T) -> Unit,
) {
    when(screenState){
        is ScreenState.Loading-> LoadingState()
        is ScreenState.Loaded<T> -> screenFunction(screenState.data)
        is ScreenState.Error -> ErrorScreen()
    }
}

/**
 * Composable function that displays a loading state with a centered circular progress indicator.
 * This provides a visual cue to the user that data fetching or processing is currently ongoing.
 */
@Composable
private fun LoadingState(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}
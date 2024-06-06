package cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.ScreenState
import cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.ScreenStateEntity

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

@Composable
private fun LoadingState(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}
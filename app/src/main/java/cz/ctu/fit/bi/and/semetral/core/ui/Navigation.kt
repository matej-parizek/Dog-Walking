package cz.ctu.fit.bi.and.semetral.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cz.ctu.fit.bi.and.semetral.feature.stepper.ui.StepperScreen

@Composable
fun Navigation(
    modifier: Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = Screens.Stepper.route,
    ) {
        composable(route = Screens.Stepper.route) {
            StepperScreen()
        }
    }
}
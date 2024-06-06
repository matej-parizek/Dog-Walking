package cz.ctu.fit.bi.and.parizmat.semestral.core.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.presentation.list.ui.DictionariesScreen
import cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.presentation.ui.SettingsDogScreen
import cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.presentation.ui.SettingsScreen
import cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.ui.stats.StatScreen
import cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.ui.stepper.StepperScreen

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
        composable(route = Screens.Stats.route) {
            StatScreen()
        }
        composable(route = Screens.Settings.route) {
            SettingsScreen()
        }
        composable(route = Screens.SettingsDog.route) {
            SettingsDogScreen()
        }
        composable(route = Screens.Dictionaries.route) {
            DictionariesScreen()
        }
    }
}
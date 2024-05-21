package cz.ctu.fit.bi.and.semestral.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cz.ctu.fit.bi.and.semestral.feature.dictionaries.ui.DictionariesScreen
import cz.ctu.fit.bi.and.semestral.feature.dictionaries.ui.Search
import cz.ctu.fit.bi.and.semestral.feature.settings.ui.SettingsDogScreen
import cz.ctu.fit.bi.and.semestral.feature.settings.ui.SettingsScreen
import cz.ctu.fit.bi.and.semestral.feature.stepper.ui.stats.StatScreen
import cz.ctu.fit.bi.and.semestral.feature.stepper.ui.stepper.StepperScreen

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
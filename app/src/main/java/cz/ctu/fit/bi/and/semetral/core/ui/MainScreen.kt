package cz.ctu.fit.bi.and.semetral.core.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import cz.ctu.fit.bi.and.semetral.core.ui.bar.bottom.BottomBar

@Composable
fun MainScreen() {
    Column {
        val navController = rememberNavController()
        Navigation(navController = navController, modifier = Modifier.weight(1f))

        val currentEntry by navController.currentBackStackEntryAsState()
        val currentRoute = currentEntry?.destination?.route
        val showBottomBar = currentRoute?.let(::hasBottomNavigation) ?: false

        if(showBottomBar) {
            BottomBar(
                route = currentRoute!!,
            )
        }
    }
}

private fun hasBottomNavigation(route: String): Boolean {
    return route in listOf(
        Screens.Stepper.route,
        Screens.Stats.route,
    )
}
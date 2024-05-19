package cz.ctu.fit.bi.and.semetral.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import cz.ctu.fit.bi.and.semetral.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
@Preview
fun MainScreen() {
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val drawableState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawableState,
        gesturesEnabled = true,

        drawerContent = {
            ModalDrawerSheet(Modifier.fillMaxWidth(0.6f)) {
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.secondary)
                        .fillMaxWidth()
                        .height(150.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.man),
                        contentDescription = null
                    )
                }
                HorizontalDivider()
                NavItem(
                    coroutineScope = coroutineScope,
                    drawableState = drawableState,
                    navController = navController,
                    name = R.string.stepper,
                    route = Screens.Stepper.route,
                    painter = R.drawable.walking,
                )
                NavItem(
                    coroutineScope = coroutineScope,
                    drawableState = drawableState,
                    navController = navController,
                    name = R.string.dictionaries,
                    route = Screens.Dictionaries.route,
                    painter = R.drawable.dictionary,
                )
                NavItem(
                    coroutineScope = coroutineScope,
                    drawableState = drawableState,
                    navController = navController,
                    name = R.string.settings,
                    route = Screens.Settings.route,
                    painter = R.drawable.settings,
                )
            }
        }
    ) {
        val currentEntry by navController.currentBackStackEntryAsState()
        val currentRoute = currentEntry?.destination?.route
        val showBottomBar = currentRoute != null
        val text = route(currentRoute)

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                AppTopBar(
                    coroutineScope = coroutineScope,
                    drawerState = drawableState,
                    text = text
                )
            }
        ) {
            Column(Modifier.padding(it)) {
                Navigation(navController = navController, modifier = Modifier.weight(1f))
                if (showBottomBar) {
                    BottomBar(
                        route = currentRoute!!,
                        navigationController = navController
                    )
                } else {
                    //TODO: ERROR Screen
                }
            }
        }
    }
}

@Composable
fun NavItem(
    coroutineScope: CoroutineScope,
    drawableState: DrawerState,
    navController: NavHostController,
    name: Int,
    route: String,
    painter: Int,
) {
    NavigationDrawerItem(
        label = { Text(text = stringResource(id = name)) },
        icon = {
            Icon(
                modifier = Modifier.size(36.dp),
                painter = painterResource(id = painter),
                contentDescription = null
            )
        },
        selected = false,
        onClick = {
            coroutineScope.launch {
                drawableState.close()
            }
            navController.navigate(route) {
                popUpTo(0)
            }
        }
    )
}

private fun route(route: String?): Int {
    return when (route) {
        Screens.Stepper.route -> R.string.stepper
        Screens.Settings.route -> R.string.settings
        Screens.SettingsDog.route -> R.string.settings
        Screens.Dictionaries.route -> R.string.dictionaries
        Screens.Search.route -> R.string.search
        Screens.Stats.route -> R.string.stats
        else -> R.string.error
    }
}
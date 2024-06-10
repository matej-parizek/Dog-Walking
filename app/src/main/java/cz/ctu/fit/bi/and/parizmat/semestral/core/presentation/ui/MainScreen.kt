package cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import cz.ctu.fit.bi.and.parizmat.semestral.R
import cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.Navigation
import cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.Screens
import cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.ui.theme.IconSize
import cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.ui.theme.Typography
import cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.ui.theme.sideBarSize
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {

    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val drawableState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawableState,
        gesturesEnabled = true,
        modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer),
        drawerContent = {
            NavBottomBar(
                navController = navController,
                drawableState = drawableState,
                coroutineScope = coroutineScope
            )
        },
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
            },
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.primaryContainer
        ) {
            Column(Modifier.padding(it)) {
                Navigation(navController = navController, modifier = Modifier.weight(1f))
                if (showBottomBar) {
                    BottomBar(
                        route = currentRoute!!,
                        navigationController = navController
                    )
                } else {
                    ErrorScreen()
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
        label = {
            Text(
                text = stringResource(id = name),
                style = Typography.labelMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        icon = {
            Icon(
                modifier = Modifier.size(IconSize.smallHead),
                painter = painterResource(id = painter),
                contentDescription = stringResource(R.string.bottom_icon),
                tint = MaterialTheme.colorScheme.onTertiary
            )
        },
        colors = NavigationDrawerItemDefaults.colors(
            unselectedContainerColor = Color.Transparent, // Background color when not selected
            selectedContainerColor = Color.Transparent, // Background color when selected
            unselectedIconColor = MaterialTheme.colorScheme.onTertiary, // Icon color when not selected
            selectedIconColor = MaterialTheme.colorScheme.onTertiary, // Icon color when selected
            unselectedTextColor = MaterialTheme.colorScheme.onPrimary, // Text color when not selected
            selectedTextColor = MaterialTheme.colorScheme.onPrimary // Text color when selected
        ),
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
    if (route?.takeIf { it.startsWith("detail/") } != null) {
        return R.string.detail
    }
    return when (route) {
        Screens.Stepper.route -> R.string.stepper
        Screens.Settings.route -> R.string.settings
        Screens.SettingsDog.route -> R.string.settings
        Screens.Dictionaries.route -> R.string.dictionaries
        else -> R.string.error
    }
}

@Composable
fun NavBottomBar(
    navController: NavHostController,
    drawableState: DrawerState,
    coroutineScope: CoroutineScope
) {

    ModalDrawerSheet(
        modifier = Modifier.fillMaxWidth(sideBarSize),
        drawerContainerColor = MaterialTheme.colorScheme.secondaryContainer
    ) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.secondary)
                .fillMaxWidth()
                .height(150.dp),
            contentAlignment = Alignment.TopStart
        ) {

            IconButton(
                onClick = {
                    coroutineScope.launch {
                        drawableState.close()
                    }
                }) {
                Icon(
                    imageVector = Icons.Default.Close,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    contentDescription = stringResource(R.string.menu),
                    modifier = Modifier.size(IconSize.largeHead)
                )
            }
            Icon(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.man),
                contentDescription = stringResource(R.string.logo)
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
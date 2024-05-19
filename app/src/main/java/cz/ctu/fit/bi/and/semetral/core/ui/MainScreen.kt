package cz.ctu.fit.bi.and.semetral.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import cz.ctu.fit.bi.and.semetral.R
import cz.ctu.fit.bi.and.semetral.core.ui.bar.bottom.BottomBar
import cz.ctu.fit.bi.and.semetral.core.ui.bar.top.AppTopBar
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
                    Icon(painter = painterResource(id = R.drawable.man),
                        contentDescription = null)
                }
                HorizontalDivider()
                NavigationDrawerItem(
                    label = { Text(text = "Stepper") },
                    icon = { Icon(imageVector = Icons.Default.Home, contentDescription = null) },
                    selected = false,
                    onClick = {
                        coroutineScope.launch {
                            drawableState.close()
                        }
                        navController.navigate(Screens.Stepper.route) {
                            popUpTo(0)
                        }
                    })
            }
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                AppTopBar(
                    coroutineScope = coroutineScope,
                    drawerState = drawableState,
                    text = "Stepper"
                )
            }
        ) {
            Column(Modifier.padding(it)) {
                Navigation(navController = navController, modifier = Modifier.weight(1f))

                val currentEntry by navController.currentBackStackEntryAsState()
                val currentRoute = currentEntry?.destination?.route
                val showBottomBar = currentRoute?.let(::hasBottomNavigation) ?: false

                if (showBottomBar) {
                    BottomBar(
                        route = currentRoute!!,
                    )
                }
            }
        }
    }
}

private fun hasBottomNavigation(route: String): Boolean {
    return route in listOf(
        Screens.Stepper.route,
        Screens.Stats.route,
    )
}
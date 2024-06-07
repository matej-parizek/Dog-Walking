package cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import cz.ctu.fit.bi.and.parizmat.semestral.R
import cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.ui.theme.IconSize
import cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.ui.theme.Typography
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Composable function that creates a top app bar for the application, designed to be used across various screens.
 *
 * @param coroutineScope The CoroutineScope in which to execute asynchronous tasks, such as opening the drawer.
 * @param drawerState The state of the navigation drawer, controlling whether it's open or closed.
 * @param text Resource ID for the title text to be displayed in the top app bar. Defaults to R.string.error if not specified.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    coroutineScope: CoroutineScope,
    drawerState: DrawerState,
    text: Int = R.string.error
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = text),
                style = Typography.headlineMedium,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                coroutineScope.launch {
                    drawerState.open()
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    contentDescription = stringResource(R.string.menu),
                    modifier = Modifier.size(IconSize.largeHead)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )

    )
}
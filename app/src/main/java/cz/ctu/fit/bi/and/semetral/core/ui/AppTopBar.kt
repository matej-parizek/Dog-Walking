package cz.ctu.fit.bi.and.semetral.core.ui

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
import androidx.compose.ui.res.stringResource
import cz.ctu.fit.bi.and.semetral.R
import cz.ctu.fit.bi.and.semetral.ui.theme.Typography
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

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
                    contentDescription = "Menu",
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )

    )
}
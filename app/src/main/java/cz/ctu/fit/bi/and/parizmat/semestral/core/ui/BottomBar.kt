package cz.ctu.fit.bi.and.parizmat.semestral.core.ui

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import cz.ctu.fit.bi.and.parizmat.semestral.R
import cz.ctu.fit.bi.and.parizmat.semestral.core.ui.theme.IconSize
import cz.ctu.fit.bi.and.parizmat.semestral.core.ui.theme.Typography

@Composable
fun BottomBar(
    route: String,
    navigationController: NavHostController
) {
    BottomAppBar(containerColor = MaterialTheme.colorScheme.secondaryContainer) {
        NavigationBarItem(
            painter = painterResource(id = leftNavBarIcon(route)),
            name = stringResource(id = leftNavBarName(route)),
            selected = hasLeftRoute(route= route),
            onClick = {

                navigationController.navigate(navigateLeft(route))
            }
        )
        if(route != Screens.Dictionaries.route) {
            NavigationBarItem(
                painter = painterResource(id = rightNavBarIcon(route)),
                name = stringResource(id = rightNavBarName(route)),
                selected = hasRightRoute(route = route),
                onClick = {
                    navigationController.navigate(navigateRight(route))
                }
            )
        }
    }
}

private fun navigateLeft(route: String): String {
    return when (route) {
        Screens.Stepper.route -> Screens.Stepper.route
        Screens.Stats.route -> Screens.Stepper.route

        Screens.Settings.route -> Screens.Settings.route
        Screens.SettingsDog.route -> Screens.Settings.route
        Screens.Dictionaries.route -> Screens.Dictionaries.route
        else -> Screens.Error.route
    }
}

private fun navigateRight(route: String): String {
    return when (route) {
        Screens.Stepper.route -> Screens.Stats.route
        Screens.Stats.route -> Screens.Stats.route

        Screens.Settings.route -> Screens.SettingsDog.route
        Screens.SettingsDog.route -> Screens.SettingsDog.route
        else -> Screens.Error.route
    }
}

private fun rightNavBarIcon(route: String): Int {
    return when (route) {
        Screens.Stepper.route -> R.drawable.stats
        Screens.Stats.route -> R.drawable.stats
        Screens.Dictionaries.route -> R.drawable.dictionary
        Screens.Settings.route -> R.drawable.dog
        Screens.SettingsDog.route -> R.drawable.dog
        else -> R.drawable.error
    }
}

private fun rightNavBarName(route: String): Int {
    return when (route) {
        Screens.Stepper.route -> R.string.stats
        Screens.Stats.route -> R.string.stats

        Screens.Settings.route -> R.string.dog
        Screens.SettingsDog.route -> R.string.dog

        Screens.Dictionaries.route -> R.string.search
        else -> R.string.error
    }
}

private fun leftNavBarIcon(route: String): Int {
    return when (route) {
        Screens.Stepper.route -> R.drawable.jogging
        Screens.Stats.route -> R.drawable.jogging
        Screens.Dictionaries.route -> R.drawable.dictionary
        Screens.Settings.route -> R.drawable.user
        Screens.SettingsDog.route -> R.drawable.user
        else -> R.drawable.error
    }
}

private fun leftNavBarName(route: String): Int {
    return when (route) {
        Screens.Stepper.route -> R.string.overview
        Screens.Stats.route -> R.string.overview

        Screens.Settings.route -> R.string.person
        Screens.SettingsDog.route -> R.string.person

        Screens.Dictionaries.route -> R.string.dictionaries
        else -> R.string.error
    }
}

private fun hasRightRoute(route: String) = route in listOf(
    Screens.SettingsDog.route,
    Screens.Stats.route,
)

private fun hasLeftRoute(route: String) = route in listOf(
    Screens.Dictionaries.route,
    Screens.Settings.route,
    Screens.Stepper.route,
)
@Composable
private fun RowScope.NavigationBarItem(
    painter: Painter,
    name: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val colorContent =
        if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onTertiary
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = {
            Icon(
                painter = painter,
                contentDescription = null,
                tint = colorContent,
                modifier = Modifier.size(IconSize.smallHead)
            )
        },
        label = {
            Text(
                text = name,
                style = Typography.headlineSmall,
                color = colorContent,
            )
        }
    )
}
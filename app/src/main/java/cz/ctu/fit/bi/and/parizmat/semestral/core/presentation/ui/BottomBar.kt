package cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.ui

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
import cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.Screens
import cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.ui.theme.IconSize
import cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.ui.theme.Typography

/**
 * Composable function that creates a bottom app bar for navigating between different screens in the application.
 *
 * @param route The current navigation route that determines which items are displayed in the bottom bar.
 * @param navigationController The navigation controller used to handle navigation actions triggered by bottom bar item clicks.
 */
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
        if(route != Screens.Dictionaries.route &&  !route.startsWith("detail/")) {
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

/**
 * Determines the navigation target when the left navigation item of the BottomBar is activated.
 * This function considers the current route and maps it to a new route based on predefined navigation logic,
 * enabling intuitive leftward or backward navigation through the app's screens.
 *
 * @param route The current route from which to navigate left or back.
 * @return The route to navigate to based on the input route.
 */
private fun navigateLeft(route: String): String {
    if (route.startsWith("detail/")) return Screens.Dictionaries.route
    return when (route) {
        Screens.Stepper.route -> Screens.Stepper.route
        Screens.Stats.route -> Screens.Stepper.route
        Screens.Settings.route -> Screens.Settings.route
        Screens.SettingsDog.route -> Screens.Settings.route
        Screens.Dictionaries.route -> Screens.Dictionaries.route
        else -> Screens.Error.route
    }
}

/**
 * Determines the navigation target when the right navigation item of the BottomBar is activated.
 * This function evaluates the current route and maps it to a forward or rightward route based on predefined navigation logic,
 * facilitating a natural progression or forward navigation through the app's screens.
 *
 * @param route The current route from which to navigate right or forward.
 * @return The route to navigate to based on the current route, designed to support intuitive user navigation flows.
 */
private fun navigateRight(route: String): String {
    if (route.startsWith("detail/")) return Screens.Dictionaries.route
    return when (route) {
        Screens.Stepper.route -> Screens.Stats.route
        Screens.Stats.route -> Screens.Stats.route

        Screens.Settings.route -> Screens.SettingsDog.route
        Screens.SettingsDog.route -> Screens.SettingsDog.route
        else -> Screens.Error.route
    }
}

private fun rightNavBarIcon(route: String): Int {

    if (route.startsWith("detail/")) return R.drawable.dictionary
    return when (route) {
        Screens.Stepper.route -> R.drawable.stats
        Screens.Stats.route -> R.drawable.stats
        Screens.Dictionaries.route -> R.drawable.dictionary
        Screens.Settings.route -> R.drawable.dog
        Screens.SettingsDog.route -> R.drawable.dog
        else -> R.drawable.error
    }
}

/**
 * Determines the appropriate icon resource for the right navigation item in the BottomBar
 * based on the current route. This function allows for context-sensitive icon display,
 * enhancing user navigation by providing visual cues that are aligned with the navigation logic.
 *
 * @param route The current route from which the icon for the right navigation item is determined.
 * @return The drawable resource ID of the icon to be displayed for the right navigation item.
 */
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
/**
 * Determines the appropriate icon resource for the left navigation item in the BottomBar
 * based on the current route. This function supports intuitive and context-sensitive navigation
 * by visually indicating the nature of the navigation action associated with the left navigation item.
 *
 * @param route The current route from which the icon for the left navigation item is determined.
 * @return The drawable resource ID of the icon to be displayed for the left navigation item.
 */
private fun leftNavBarIcon(route: String): Int {
    if (route.startsWith("detail/")) return R.drawable.dictionary
    return when (route) {
        Screens.Stepper.route -> R.drawable.jogging
        Screens.Stats.route -> R.drawable.jogging
        Screens.Dictionaries.route -> R.drawable.dictionary
        Screens.Settings.route -> R.drawable.user
        Screens.SettingsDog.route -> R.drawable.user
        else -> R.drawable.error
    }
}

/**
 * Determines the appropriate label resource for the left navigation item in the BottomBar
 * based on the current route. This function supports intuitive navigation by providing
 * context-sensitive text labels that describe the destination or action of the left navigation item.
 *
 * @param route The current route from which the label for the left navigation item is determined.
 * @return The string resource ID of the label to be displayed for the left navigation item.
 */
private fun leftNavBarName(route: String): Int {
    if (route.startsWith("detail/")) return R.string.dictionaries
    return when (route) {
        Screens.Stepper.route -> R.string.overview
        Screens.Stats.route -> R.string.overview
        Screens.Settings.route -> R.string.person
        Screens.SettingsDog.route -> R.string.person
        Screens.Dictionaries.route -> R.string.dictionaries
        else -> R.string.error
    }
}

/**
 * Determines whether the current route should have a corresponding right navigation action in the BottomBar.
 * This function checks if the given route is included in a list of routes that are defined to have right navigation actions.
 * It's useful for conditionally rendering navigation items based on the route, ensuring that navigation options are
 * only provided when appropriate.
 *
 * @param route The current route being evaluated.
 * @return Boolean value indicating whether a right navigation action is applicable for the given route.
 */
private fun hasRightRoute(route: String) = route in listOf(
    Screens.SettingsDog.route,
    Screens.Stats.route,
)

/**
 * Checks whether the current route should have a corresponding left navigation action in the BottomBar.
 * This function evaluates if the given route is among those predefined to have left navigation actions,
 * supporting conditional rendering of navigation items to ensure navigation options are provided only where meaningful.
 *
 * @param route The current route being evaluated.
 * @return Boolean value indicating whether a left navigation action is applicable for the given route.
 */
private fun hasLeftRoute(route: String) = route in listOf(
    Screens.Dictionaries.route,
    Screens.Settings.route,
    Screens.Stepper.route,
)

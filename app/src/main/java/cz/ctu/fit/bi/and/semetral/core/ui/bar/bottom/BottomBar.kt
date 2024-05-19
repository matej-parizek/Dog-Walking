package cz.ctu.fit.bi.and.semetral.core.ui.bar.bottom

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
import androidx.compose.ui.unit.dp
import cz.ctu.fit.bi.and.semetral.R
import cz.ctu.fit.bi.and.semetral.core.ui.Screens

@Composable
fun BottomBar(
    route: String,
) {
    BottomAppBar(containerColor = MaterialTheme.colorScheme.secondaryContainer) {
        NavigationBarItem(
            painter = painterResource(id = leftNavBarIcon(route)),
            name = stringResource(id = leftNavBarName(route)),
            //TODO
            selected = true,
            onClick = { }
        )
        NavigationBarItem(
            painter = painterResource(id = rightNavBarIcon(route)),
            name = stringResource(id = rightNavBarName(route)),
            //TODO
            selected = false,
            onClick = { }
        )

    }
}

private fun rightNavBarIcon(route: String): Int{
    return when(route){
        Screens.Stepper.route -> R.drawable.stats
        else-> R.drawable.error
    }
}
private fun rightNavBarName(route: String): Int {
    return when (route) {
        Screens.Stepper.route -> R.string.stats
        else -> R.string.error
    }
}

private fun leftNavBarIcon(route: String): Int{
    return when(route){
        Screens.Stepper.route -> R.drawable.jogging
        else ->R.drawable.error
    }
}

private fun leftNavBarName(route: String): Int {
    return when (route) {
        Screens.Stepper.route -> R.string.overview
        else -> R.string.error
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
                modifier = Modifier.size(48.dp)
            )
        },
        label = {
            Text(
                text = name,
                style = MaterialTheme.typography.labelMedium,
                color = colorContent,
            )
        }
    )
}
package cz.ctu.fit.bi.and.semetral.feature.settings.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cz.ctu.fit.bi.and.semetral.R
import cz.ctu.fit.bi.and.semetral.ui.theme.Typography

@Composable
@Preview
fun SettingsScreen() {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Dog size:",
                style = Typography.bodyLarge
            )
            Spacer(modifier = Modifier.width(10.dp))
            DropBox()
        }
    }

}


@Composable
fun DropBox(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .wrapContentSize(Alignment.TopStart)
    ) {
        var expanded by remember { mutableStateOf(false) }
        var text by remember { mutableStateOf("") }
        val list = listOf(
            stringResource(R.string.small),
            stringResource(R.string.medium),
            stringResource(R.string.large)
        )
        val mod = Modifier.width(120.dp)
        TextField(
            value = text,
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if (expanded) Icons.Filled.KeyboardArrowUp
                        else Icons.Filled.KeyboardArrowDown,
                        contentDescription = null
                    )
                }
            },
            modifier = mod,
            onValueChange = {}
        )
        DropdownMenu(
            modifier = mod,
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            list.forEachIndexed { index, value ->
                DropdownMenuItem(
                    text = { Text(value) },
                    onClick = {
                        expanded = false
                        text = value
                    },
                )
                if (index < list.size - 1)
                    HorizontalDivider()
            }
        }
    }
}
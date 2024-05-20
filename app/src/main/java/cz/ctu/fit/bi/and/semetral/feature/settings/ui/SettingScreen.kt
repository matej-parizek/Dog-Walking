package cz.ctu.fit.bi.and.semetral.feature.settings.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cz.ctu.fit.bi.and.semetral.ui.theme.Typography

@Composable
@Preview
fun SettingsScreen() {


    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        val modifier = Modifier
            .weight(1f)
            .fillMaxSize()
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 20.dp)
                .width(120.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SettingOption(
                modifier = modifier,
                text = "Weight:"
            )

            SettingOption(
                modifier = modifier,
                text = "Min. steps:"
            )

            SettingOption(
                modifier = modifier,
                text = "Dog type:"
            )
        }
        Spacer(modifier = Modifier.width(20.dp))
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(end = 20.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Slider(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                range = 20f..240f,
                slider = 60f
            )
            Slider(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                range = 10000f..100000f,
                slider = 10000f
            )

            Slider(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                range = 0f..2f,
                slider = 1f,
                steps = 1
            )
        }

    }

}

@Composable
fun Slider(
    range: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int = 0,
    modifier: Modifier,
    slider: Float = 10000f
) {
    var sliderPosition by remember {
        mutableFloatStateOf(slider)
    }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        Slider(
            modifier = Modifier
                .semantics { contentDescription = "Localized Description" },
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            valueRange = range,
            steps = steps
        )
    }
}

@Composable
fun SettingOption(
    modifier: Modifier,
    text: String
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = text,
            style = Typography.bodyLarge,
        )

    }
}
package cz.ctu.fit.bi.and.semestral.feature.settings.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cz.ctu.fit.bi.and.semestral.ui.theme.Typography

@Composable
fun SettingsDogScreen() {
    val texts = listOf<@Composable (Modifier) -> Unit>(
        { modifier -> SettingOption(modifier = modifier, text = "Weight:") },
        { modifier -> SettingOption(modifier = modifier, text = "Dog type:") },
        { modifier -> SettingOption(modifier = modifier, text = "Min. steps:") }
    )
    val sliders = listOf<@Composable (Modifier) -> Unit>(
        { modifier ->
            Slider(
                modifier = modifier,
                range = 20f..240f,
                slider = 60f,
                postfix = " kg",
            )
        },
        { modifier ->
            Slider(
                modifier = modifier,
                range = 0f..2f,
                slider = 1f,
                steps = 1,
                label = { labelValue -> DogType.Label(labelValue = labelValue) }
            )

        },
        { modifier ->
            Slider(
                modifier = modifier,
                range = 10000f..100000f,
                slider = 10000f,
                postfix = " steps"
            )
        }
    )
    CustomSettingsScreen(texts = texts, sliders = sliders)
}


@Composable
fun SettingsScreen() {
    val texts = listOf<@Composable (Modifier) -> Unit>(
        { modifier -> SettingOption(modifier = modifier, text = "Weight:") },
        { modifier -> SettingOption(modifier = modifier, text = "Height") },
        { modifier -> SettingOption(modifier = modifier, text = "Min. steps:") }
    )
    val sliders = listOf<@Composable (Modifier) -> Unit>(
        { modifier ->
            Slider(
                modifier = modifier,
                range = 20f..240f,
                slider = 60f,
                postfix = " kg"
            )
        },
        { modifier ->
            Slider(
                modifier = modifier,
                range = 110f..260f,
                slider = 160f,
                postfix = " cm"
            )

        },
        { modifier ->
            Slider(
                modifier = modifier,
                range = 3000f..20000f,
                slider = 6000f,
                steps = 67,
                postfix = " steps"
            )
        }
    )
    CustomSettingsScreen(texts = texts, sliders = sliders)
}

@Composable
fun CustomSettingsScreen(
    texts: List<@Composable (modifier: Modifier) -> Unit> = listOf(),
    sliders: List<@Composable (modifier: Modifier) -> Unit> = listOf()
) {
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
            texts.forEach {
                it(modifier)
            }
        }
        Spacer(modifier = Modifier.width(20.dp))
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(end = 20.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            sliders.forEach {
                it(modifier)
            }
        }
    }
}

@Composable
fun Slider(
    range: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int = 0,
    modifier: Modifier,
    slider: Float = 10000f,
    postfix: String = "",
    label: @Composable (labelValue: Int) -> Unit = { labelValue ->
        CustomSliderDefaults.Label(labelValue = labelValue.toString(), postfix = postfix)
    },
) {
    var sliderPosition by remember {
        mutableFloatStateOf(slider)
    }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        CustomSlider(
            modifier = Modifier
                .semantics { contentDescription = "Localized Description" },
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            valueRange = range,
            steps = steps,
            postfix = postfix,
            label = label
        )
    }
}

@Composable
fun SettingOption(
    modifier: Modifier,
    text: String
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = text,
            style = Typography.bodyLarge,
        )
    }
}

object DogType {
    @Composable
    fun Label(
        labelValue: Int,
        modifier: Modifier = Modifier,
        style: TextStyle = Typography.labelMedium,
    ) {
        Box(modifier = modifier) {
            Text(
                text = chooseLabel(labelValue),
                style = style,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }

    private fun chooseLabel(value: Int): String {
        return when (value) {
            0 -> "Small"
            1 -> "Medium"
            2 -> "Large"
            else -> ""
        }
    }
}
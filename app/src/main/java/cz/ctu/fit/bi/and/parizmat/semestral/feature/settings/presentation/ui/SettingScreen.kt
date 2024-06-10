package cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.presentation.ui

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cz.ctu.fit.bi.and.parizmat.semestral.R
import cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.presentation.ui.dog.SettingDogViewModel
import cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.presentation.ui.person.SettingPersonViewModel
import cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.ui.theme.Typography
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsDogScreen(
    viewModel: SettingDogViewModel = koinViewModel()
) {
    val settingState = viewModel.state.collectAsStateWithLifecycle()
    val setting = settingState.value.settings
    if (setting != null) {
        val texts = listOf<@Composable (Modifier) -> Unit>(
            { modifier ->
                SettingOption(
                    modifier = modifier,
                    text = stringResource(id = R.string.weight)
                )
            },
            { modifier ->
                SettingOption(
                    modifier = modifier,
                    text = stringResource(R.string.dog_type)
                )
            },
            { modifier ->
                SettingOption(
                    modifier = modifier,
                    text = stringResource(R.string.min_steps)
                )
            }
        )
        val sliders = listOf<@Composable (Modifier) -> Unit>(
            { modifier ->
                Slider(
                    modifier = modifier,
                    range = 5f..90f,
                    postfix = stringResource(id = R.string.kg),
                    value = setting.weight,
                    onChange = { value -> viewModel.updateWeight(value= value) }
                )
            },
            { modifier ->
                Slider(
                    modifier = modifier,
                    range = 0f..2f,
                    steps = 1,
                    label = { labelValue -> DogType.Label(labelValue = labelValue) },
                    value = setting.height.toFloat(),
                    onChange = { value -> viewModel.updateHeight(value= value) }
                )

            },
            { modifier ->
                Slider(
                    modifier = modifier,
                    range = 3000f..20000f,
                    steps = 67,
                    postfix = stringResource(id = R.string.steps),
                    value = setting.minSteps.toFloat(),
                    onChange = { value -> viewModel.updateSteps(value= value) }
                )
            }
        )
        CustomSettingsScreen(
            texts = texts, sliders = sliders,
        )
    }
}


@Composable
fun SettingsScreen(
    viewModel: SettingPersonViewModel = koinViewModel()
) {
    val settingState = viewModel.state.collectAsStateWithLifecycle()
    val setting = settingState.value.settings
    if (setting != null) {
        val texts = listOf<@Composable (Modifier) -> Unit>(
            { modifier ->
                SettingOption(
                    modifier = modifier,
                    text = stringResource(id = R.string.weight)
                )
            },
            { modifier ->
                SettingOption(
                    modifier = modifier,
                    text = stringResource(R.string.height)
                )
            },
            { modifier ->
                SettingOption(
                    modifier = modifier,
                    text = stringResource(R.string.min_steps)
                )
            }
        )
        val sliders = listOf<@Composable (Modifier) -> Unit>(
            { modifier ->
                Slider(
                    modifier = modifier,
                    range = 20f..240f,
                    postfix = stringResource(id = R.string.kg),
                    value = setting.weight,
                    onChange = { value -> viewModel.updateWeight(value= value) }
                )
            },
            { modifier ->
                Slider(
                    modifier = modifier,
                    range = 110f..260f,
                    postfix = stringResource(R.string.cm),
                    value = setting.height.toFloat(),
                    onChange = { value -> viewModel.updateHeight(value= value) }
                )

            },
            { modifier ->
                Slider(
                    modifier = modifier,
                    range = 3000f..20000f,
                    steps = 67,
                    postfix = stringResource(id = R.string.steps),
                    value = setting.minSteps.toFloat(),
                    onChange = { value -> viewModel.updateSteps(value= value) }
                )
            }
        )
        CustomSettingsScreen(
            texts = texts, sliders = sliders,
        )
    }
}

@Composable
fun CustomSettingsScreen(
    texts: List<@Composable (modifier: Modifier) -> Unit> = listOf(),
    sliders: List<@Composable (modifier: Modifier) -> Unit> = listOf(),
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
    value: Float,
    onChange: (Float) -> Unit,
    range: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int = 0,
    modifier: Modifier,
    postfix: String = "",
    label: @Composable (labelValue: Int) -> Unit = { labelValue ->
        CustomSliderDefaults.Label(labelValue = labelValue.toString(), postfix = postfix)
    },
) {

    var sliderPosition by remember { mutableFloatStateOf(value) }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        CustomSlider(
            modifier = Modifier
                .semantics { contentDescription = "Localized Description" },
            value = sliderPosition,
            onValueChange = {
                sliderPosition = it
                onChange(it)
                            },
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
            color = MaterialTheme.colorScheme.onPrimary
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
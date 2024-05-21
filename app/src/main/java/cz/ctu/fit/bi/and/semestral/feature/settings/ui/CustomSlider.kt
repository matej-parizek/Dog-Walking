package cz.ctu.fit.bi.and.semestral.feature.settings.ui

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SliderState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import cz.ctu.fit.bi.and.semestral.ui.theme.Typography
import kotlin.math.roundToInt

/**
 * A custom slider composable that allows selecting a value within a given range.
 *
 * <a href="https://m3.material.io/components/sliders/overview" class="external" target="_blank">Material Design slider</a>.
 *
 * Sliders allow users to make selections from a range of values.
 *
 * Sliders reflect a range of values along a bar, from which users may select a single value.
 * They are ideal for adjusting settings such as volume, brightness, or applying image filters.
 *
 * ![Sliders image](https://developer.android.com/images/reference/androidx/compose/material3/sliders.png)
 *
 * @param value The current value of the slider. If outside of [valueRange] provided, value will be
 * coerced to this range.
 * @param onValueChange The callback invoked when the value of the slider changes. In this callback, the value should be updated.
 * @param modifier The modifier to be applied to the slider.
 * @param valueRange The range of values the slider can represent. The passed [value] will be coerced to this range.
 * @param gap The spacing between indicators on the slider.
 * @param showLabel Determines whether to show a label above the slider.
 * @param enabled Determines whether the slider is enabled for interaction. When `false`, this component will not respond to user input, and it will appear visually disabled and disabled to accessibility services.
 * @param thumb The composable used to display the thumb of the slider. The lambda receives a [SliderState] which is used to obtain the current active track.
 * @param track The composable used to display the track of the slider. It is placed underneath the thumb. The lambda receives a [SliderState] which is used to obtain the current active track.
 * @param label The composable used to display the label above the slider.
 * @param colors [SliderColors] that will be used to resolve the colors used for this slider in different states. See [SliderDefaults.colors].
 * @param interactionSource The [MutableInteractionSource] representing the stream of [...]s for this slider. You can create and pass in your own `remember`ed instance to observe [...]s and customize the appearance / behavior of this slider in different states.
 * @param steps If greater than 0, specifies the amount of discrete allowable values, evenly distributed across the whole value range. If 0, the slider will behave continuously and allow any value from the range specified. Must not be negative.
 * @param postfix If are showLabel is true u are able to add postfix to text
 *
 * @author MansuriYamin
 * @author Matej Parizek
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    valueRange: ClosedFloatingPointRange<Float> = ValueRange,
    gap: Int = Gap,
    colors: SliderColors = SliderDefaults.colors(),
    steps: Int = 0,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    showLabel: Boolean = true,
    enabled: Boolean = true,
    postfix: String = "",
    thumb: @Composable (SliderState) -> Unit = {
        SliderDefaults.Thumb(
            interactionSource = interactionSource,
            colors = colors,
            enabled = enabled
        )
    },
    track: @Composable (SliderState) -> Unit = { sliderState ->
        SliderDefaults.Track(
            colors = colors,
            enabled = enabled,
            sliderState = sliderState
        )
    },
    label: @Composable (labelValue: Int) -> Unit = { labelValue ->
        CustomSliderDefaults.Label(labelValue = labelValue.toString(), postfix = postfix)
    },
) {
    val itemCount = (valueRange.endInclusive - valueRange.start).roundToInt()

    Box(modifier = modifier) {
        Layout(
            measurePolicy = customSliderMeasurePolicy(
                itemCount = itemCount,
                gap = gap,
                value = value,
                startValue = valueRange.start
            ),
            content = {
                if (showLabel)
                    Label(
                        modifier = Modifier.layoutId(CustomSliderComponents.LABEL),
                        value = value,
                        label = label,
                    )

                Spacer(modifier = Modifier.layoutId(CustomSliderComponents.THUMB))

                Slider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .layoutId(CustomSliderComponents.SLIDER),
                    value = value,
                    valueRange = valueRange,
                    steps = steps,
                    onValueChange = { onValueChange(it) },
                    thumb = thumb,
                    colors = colors,
                    track = track,
                    enabled = enabled
                )

            })
    }
}

@Composable
private fun Label(
    modifier: Modifier = Modifier,
    value: Float,
    label: @Composable (labelValue: Int) -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        label(value.roundToInt())
    }
}


private fun customSliderMeasurePolicy(
    itemCount: Int,
    gap: Int,
    value: Float,
    startValue: Float
) = MeasurePolicy { measurables, constraints ->
    // Measure the thumb component and calculate its radius.
    val thumbPlaceable = measurables.first {
        it.layoutId == CustomSliderComponents.THUMB
    }.measure(constraints)
    val thumbRadius = (thumbPlaceable.width / 2).toFloat()

    val indicatorPlaceables = measurables.filter {
        it.layoutId == CustomSliderComponents.INDICATOR
    }.map { measurable ->
        measurable.measure(constraints)
    }
    val indicatorHeight = indicatorPlaceables.maxByOrNull { it.height }?.height ?: 0

    val sliderPlaceable = measurables.first {
        it.layoutId == CustomSliderComponents.SLIDER
    }.measure(constraints)
    val sliderHeight = sliderPlaceable.height

    val labelPlaceable = measurables.find {
        it.layoutId == CustomSliderComponents.LABEL
    }?.measure(constraints)
    val labelHeight = labelPlaceable?.height ?: 0

    // Calculate the total width and height of the custom slider layout
    val width = sliderPlaceable.width
    val height = labelHeight + sliderHeight + indicatorHeight

    // Calculate the available width for the track (excluding thumb radius on both sides).
    val trackWidth = width - (2 * thumbRadius)

    // Calculate the width of each section in the track.
    val sectionWidth = trackWidth / itemCount
    // Calculate the horizontal spacing between indicators.
    val indicatorSpacing = sectionWidth * gap

    // To calculate offset of the label, first we will calculate the progress of the slider
    // by subtracting startValue from the current value.
    // After that we will multiply this progress by the sectionWidth.
    // Add thumb radius to this resulting value.
    val labelOffset = (sectionWidth * (value - startValue)) + thumbRadius

    layout(width = width, height = height) {
        var indicatorOffsetX = thumbRadius
        // Place label at top.
        // We have to subtract the half width of the label from the labelOffset,
        // to place our label at the center.
        labelPlaceable?.placeRelative(
            x = (labelOffset - (labelPlaceable.width / 2)).roundToInt(),
            y = 0
        )

        // Place slider placeable below the label.
        sliderPlaceable.placeRelative(x = 0, y = labelHeight)

        // Place indicators below the slider.
        indicatorPlaceables.forEach { placeable ->
            // We have to subtract the half width of the each indicator from the indicatorOffset,
            // to place our indicators at the center.
            placeable.placeRelative(
                x = (indicatorOffsetX - (placeable.width / 2)).roundToInt(),
                y = labelHeight + sliderHeight
            )
            indicatorOffsetX += indicatorSpacing
        }
    }
}

/**
 * Object to hold defaults used by [CustomSlider]
 */
object CustomSliderDefaults {


    /**
     * Composable function that represents the label of the slider.
     *
     * @param labelValue The value to display as the label.
     * @param modifier The modifier for styling the label.
     * @param style The style of the label text.
     * @param postfix The postfix to label
     */
    @Composable
    fun Label(
        labelValue: String,
        modifier: Modifier = Modifier,
        postfix: String = "",
        style: TextStyle = Typography.labelMedium,
    ) {
        Box(modifier = modifier) {
            Text(
                text = labelValue + postfix,
                style = style,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}


private enum class CustomSliderComponents {
    SLIDER, LABEL, INDICATOR, THUMB
}

private const val Gap = 1
private val ValueRange = 0f..10f
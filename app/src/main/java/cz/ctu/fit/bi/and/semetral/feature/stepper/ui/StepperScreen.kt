package cz.ctu.fit.bi.and.semetral.feature.stepper.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.ctu.fit.bi.and.semetral.R
import cz.ctu.fit.bi.and.semetral.ui.theme.BackgroundCircleIndicator
import cz.ctu.fit.bi.and.semetral.ui.theme.CircleIndicator


@Composable
@Preview
fun StepperScreen(
    viewModel: StepperViewModel = StepperViewModel(),
) {

    //TODO FROM VIEW
    Column {
        ProcessIndicator(
            modifier = Modifier.fillMaxWidth(),
            size = 150.dp,
            viewModel = viewModel
        )
    }
}

@Composable
fun StepperStat(
    modifier: Modifier = Modifier,
    kcalValue: Double = 20.5,
    elevationGain: Double = 20.5,
    steps: Int = 35000,
) {
    Column(modifier) {
        IconWithText(
            id = R.drawable.fire,
            text = "$kcalValue Kcal"
        )
        Spacer(modifier = Modifier.height(12.dp))
        IconWithText(
            id = R.drawable.mountain,
            text = "$elevationGain Meters"
        )
        Spacer(modifier = Modifier.height(12.dp))
        IconWithText(
            id = R.drawable.footprint,
            text = "$steps Steps"
        )
    }
}

/**
 * Composable function that displays an icon with text beside it.
 *
 * @param modifier Modifier to be applied to the Row containing the icon and text.
 * @param id Resource ID of the icon drawable.
 * @param text The text to be displayed beside the icon.
 */
@Composable
fun IconWithText(
    modifier: Modifier = Modifier,
    id: Int = R.drawable.error,
    text: String = stringResource(id = R.string.error)
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = id),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

/**
 * Function showing a custom circular progress indicator along with statistics about steps, calories burned, and elevation gain.
 *
 * @param size The size of the circular progress indicator.
 * @param id The resource ID for an optional icon or image.
 * @param viewModel The ViewModel instance providing data.
 */
@Composable
fun StepperData(
    size: Dp = 96.dp,
    id: Int = R.drawable.error,
    viewModel: StepperViewModel,
) {
    Column(
        modifier = Modifier.padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val personKcal = 10.5
        val personEvaluation = 1.5
        val personSteps = 3005
        val progress = 1f
        CustomCircularProgressIndicator(
            progress = progress,
            size = size,
            id = id,
        )
        Spacer(modifier = Modifier.height(20.dp))
        StepperStat(
            kcalValue = personKcal,
            elevationGain = personEvaluation,
            steps = personSteps
        )
    }
}

/**
 * Composable function to display a process indicator, which includes a custom circular progress indicator.
 *
 * @param modifier The modifier to apply to the process indicator.
 * @param size Size of the circular indicator.
 * @param viewModel ViewModel
 */
@Composable
fun ProcessIndicator(
    modifier: Modifier = Modifier,
    size: Dp = 96.dp,
    viewModel: StepperViewModel
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        StepperData(
            size = size,
            id = R.drawable.jogging,
            viewModel = viewModel
        )
        StepperData(
            size = size,
            id = R.drawable.running_dog,
            viewModel = viewModel
        )
    }
}

/**
 * Composable function to display a custom circular progress indicator with text in the center.
 *
 * @param size Size of the circular indicator.
 * @param strokeWidth Width of the indicator stroke.
 * @param progress Progress value as a percentage (0.0 - 1.0).
 */
@Composable
fun CustomCircularProgressIndicator(
    size: Dp = 96.dp,
    strokeWidth: Dp = 8.dp,
    progress: Float = 1F,
    id: Int,
) {
    Box {
        val textSize = Dp(size.value / 8).value.sp
        val padding = Dp(size.value * 0.1f)
        val measurement = size + padding
        val iconSize = Dp(size.value / 6f)
        Box(
            modifier = Modifier
                .size(measurement)
                .padding(padding / 2),
            contentAlignment = Alignment.BottomCenter
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                drawArc(
                    color = BackgroundCircleIndicator,
                    startAngle = 120F,
                    sweepAngle = 300F,
                    useCenter = false,
                    size = Size(size.toPx(), size.toPx()),
                    style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
                )
                drawArc(
                    color = CircleIndicator,
                    startAngle = 120F,
                    sweepAngle = progress * 300F,
                    useCenter = false,
                    size = Size(size.toPx(), size.toPx()),
                    style = Stroke(width = strokeWidth.toPx() + 2, cap = StrokeCap.Round)
                )
            }
            Icon(
                painter = painterResource(id = id),
                contentDescription = null,
                tint = if (progress < 1.0f) BackgroundCircleIndicator else CircleIndicator,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(iconSize)
            )
            Text(
                fontSize = textSize,
                text = "${progress * 100}%",
                fontWeight = FontWeight.Bold,
            )

        }
    }
}
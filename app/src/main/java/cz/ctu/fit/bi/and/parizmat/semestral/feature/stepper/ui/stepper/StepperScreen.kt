package cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.ui.stepper

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cz.ctu.fit.bi.and.parizmat.semestral.R
import cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.ui.LoadingScreen
import cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.ui.theme.BackgroundCircleIndicator
import cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.ui.theme.CircleIndicator
import cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.ui.theme.IconSize
import cz.ctu.fit.bi.and.parizmat.semestral.feature.permisions.Permission
import cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.domain.StepCounter
import cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.ui.stats.StatScreen
import cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.ui.stats.StatsViewModel
import org.koin.androidx.compose.koinViewModel
import android.icu.math.BigDecimal
import android.util.Log


@Composable
@Preview
fun StepperScreen(
    viewModel: StepperViewModel = koinViewModel(),
) {
    Permission(rationale = stringResource(R.string.rationale)) {
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .verticalScroll(state = scrollState, enabled = true)
                .fillMaxHeight(0.8f),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Overview()
            Statistic()
        }
    }
}


@Composable
fun Statistic(
    viewModel: StatsViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LoadingScreen(screenState = state ) {
        StatScreen(it)
    }
}

@Composable
fun Overview(
    viewModel: StepperViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LoadingScreen(screenState = state) {
        ProcessIndicator(
            modifier = Modifier.fillMaxWidth(),
            size = 150.dp,
            stepperState = it
        )
    }
}

@Composable
fun StepperStat(
    modifier: Modifier = Modifier,
    kcalValue: Double = 20.5,
    elevationGain: Double = 20.5,
    steps: Long = 35000L,
) {
    Column(modifier) {
        IconWithText(
            id = R.drawable.fire,
            text = stringResource(R.string.kcal, kcalValue)
        )
        Spacer(modifier = Modifier.height(12.dp))
        IconWithText(
            id = R.drawable.mountain,
            text = stringResource(R.string.meters, elevationGain)
        )
        Spacer(modifier = Modifier.height(12.dp))
        IconWithText(
            id = R.drawable.footprint,
            text = stringResource(R.string.value_steps, steps)
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
            modifier = Modifier.size(IconSize.mediumBody),
            painter = painterResource(id = id),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSecondary
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = 4.dp),
            color = MaterialTheme.colorScheme.onPrimary

        )
    }
}

/**
 * Function showing a custom circular progress indicator along with statistics about steps, calories burned, and elevation gain.
 *
 * @param size The size of the circular progress indicator.
 * @param id The resource ID for an optional icon or image.
 * @param stepCounter Data
 */
@Composable
fun StepperData(
    size: Dp = 96.dp,
    id: Int = R.drawable.error,
    stepCounter: StepCounter
) {
    Column(
        modifier = Modifier.padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val progress = if (stepCounter.progress > 1f) 1f else stepCounter.progress
        CustomCircularProgressIndicator(
            progress = progress,
            size = size,
            id = id,
        )
        Spacer(modifier = Modifier.height(20.dp))
        StepperStat(
            kcalValue = stepCounter.kcal,
            elevationGain = stepCounter.evaluation,
            steps = stepCounter.steps
        )
    }
}

/**
 * Composable function to display a process indicator, which includes a custom circular progress indicator.
 *
 * @param modifier The modifier to apply to the process indicator.
 * @param size Size of the circular indicator.
 * @param stepperState Data
 */
@Composable
fun ProcessIndicator(
    modifier: Modifier = Modifier,
    size: Dp = 96.dp,
    stepperState: StepperState
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        StepperData(
            size = size,
            id = R.drawable.jogging,
            stepCounter = stepperState.person
        )
        StepperData(
            size = size,
            id = R.drawable.running_dog,
            stepCounter = stepperState.dog
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
    size: Dp = IconSize.largeGraph,
    strokeWidth: Dp = 8.dp,
    progress: Float = 1F,
    id: Int,
) {
    Box {
        val textSize = Dp(size.value / 8).value.sp
        val padding = Dp(size.value * 0.1f)
        val measurement = size + padding
        val iconSize = Dp(size.value / 6f)
        val textValue = adjustString(progress*100f)
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
                text = "$textValue%",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary
            )

        }
    }
}

fun adjustString(value: Float):String{
    return BigDecimal(value.toDouble()).setScale(2,BigDecimal.ROUND_HALF_UP).toString()
}
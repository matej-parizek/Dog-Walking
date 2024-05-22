package cz.ctu.fit.bi.and.semestral.feature.stepper.ui.stats

import android.graphics.Typeface
import android.text.Layout
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberAxisGuidelineComponent
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberBottomAxis
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberStartAxis
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberColumnCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.compose.cartesian.rememberVicoScrollState
import com.patrykandpatrick.vico.compose.cartesian.rememberVicoZoomState
import com.patrykandpatrick.vico.compose.common.component.fixed
import com.patrykandpatrick.vico.compose.common.component.rememberLayeredComponent
import com.patrykandpatrick.vico.compose.common.component.rememberLineComponent
import com.patrykandpatrick.vico.compose.common.component.rememberShapeComponent
import com.patrykandpatrick.vico.compose.common.component.rememberTextComponent
import com.patrykandpatrick.vico.compose.common.of
import com.patrykandpatrick.vico.compose.common.shape.markerCornered
import com.patrykandpatrick.vico.core.cartesian.CartesianMeasureContext
import com.patrykandpatrick.vico.core.cartesian.HorizontalDimensions
import com.patrykandpatrick.vico.core.cartesian.Insets
import com.patrykandpatrick.vico.core.cartesian.Scroll
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.CartesianValueFormatter
import com.patrykandpatrick.vico.core.cartesian.data.columnSeries
import com.patrykandpatrick.vico.core.cartesian.layer.ColumnCartesianLayer
import com.patrykandpatrick.vico.core.cartesian.marker.CartesianMarker
import com.patrykandpatrick.vico.core.cartesian.marker.DefaultCartesianMarker
import com.patrykandpatrick.vico.core.common.Dimensions
import com.patrykandpatrick.vico.core.common.component.TextComponent
import com.patrykandpatrick.vico.core.common.shape.Corner
import com.patrykandpatrick.vico.core.common.shape.Shape
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


/**
 * Graph copy from com.patrykandpatrick.vico.compose samples
 *
 **/

@Composable
fun StatScreen() {
    val modelProducer = remember { CartesianChartModelProducer.build() }
    LaunchedEffect(Unit) {
        withContext(Dispatchers.Default) {
            modelProducer.tryRunTransaction {
                columnSeries {
                    /* Set Value */

                    val list = listOf<Float>(
                        0.15f,
                        28f,
                        48f,
                        15f,
                        16f,
                        48f,
                        0.15f,
                        28f,
                        48f,
                        15f,
                        16f,
                        48f
                    )
                    series(
                        list
                    )
                }
            }
        }
    }
    ComposeChart6(modelProducer = modelProducer, modifier = Modifier.fillMaxWidth())
}

@Composable
private fun ComposeChart6(
    modelProducer: CartesianChartModelProducer,
    modifier: Modifier,
) {
    val horizontalBox = rememberHorizontalBox()
    val shape = remember { Shape.cut(topLeftPercent = 50) }
    CartesianChartHost(
        chart =
        rememberCartesianChart(
            rememberColumnCartesianLayer(
                columnProvider = ColumnCartesianLayer.ColumnProvider.series(
                    columnColors.map {
                        rememberLineComponent(
                            color = it,
                            thickness = 6.dp,
                            shape = shape
                        )
                    },
                ),
            ),
            startAxis = rememberStartAxis(),
            bottomAxis = rememberBottomAxis(valueFormatter = bottomAxisValueFormatter),
            decorations = remember(horizontalBox) { listOf(horizontalBox) },
        ),
        modelProducer = modelProducer,
        modifier = modifier,
        marker = rememberMarker(),
        runInitialAnimation = false,
        zoomState = rememberVicoZoomState(zoomEnabled = true),
        scrollState = rememberVicoScrollState(
            scrollEnabled = true,
            initialScroll = Scroll.Absolute.End
        ),
        placeholder = {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "You haven't walk yet. After walking chart will show up")
            }
        }
    )
}


@Composable
private fun rememberHorizontalBox() =
    com.patrykandpatrick.vico.compose.cartesian.decoration.rememberHorizontalBox(
        y = { 13f..14f },
        box = rememberShapeComponent(color = horizontalBoxColor.copy(.1f)),
    )

private val columnColors = listOf(Color(0xFF701D7E), Color(0xFF9B5AA7), Color(0xFFA782AD))
private val horizontalBoxColor = Color(0xFF19FF00)
private val daysOfWeek = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
private val bottomAxisValueFormatter =
    CartesianValueFormatter { x, _, _ -> daysOfWeek[x.toInt() % daysOfWeek.size] }


@Composable
internal fun rememberMarker(
    labelPosition: DefaultCartesianMarker.LabelPosition = DefaultCartesianMarker.LabelPosition.Top,
    showIndicator: Boolean = true,
): CartesianMarker {
    val labelBackgroundShape = Shape.markerCornered(Corner.FullyRounded)
    val labelBackground =
        rememberShapeComponent(labelBackgroundShape, MaterialTheme.colorScheme.surface)
            .setShadow(
                radius = LABEL_BACKGROUND_SHADOW_RADIUS_DP,
                dy = LABEL_BACKGROUND_SHADOW_DY_DP,
                applyElevationOverlay = true,
            )
    val label =
        rememberTextComponent(
            color = MaterialTheme.colorScheme.onSurface,
            background = labelBackground,
            padding = Dimensions.of(8.dp, 4.dp),
            typeface = Typeface.MONOSPACE,
            textAlignment = Layout.Alignment.ALIGN_CENTER,
            minWidth = TextComponent.MinWidth.fixed(40.dp),
        )
    val indicatorFrontComponent =
        rememberShapeComponent(Shape.Pill, MaterialTheme.colorScheme.surface)
    val indicatorCenterComponent = rememberShapeComponent(Shape.Pill)
    val indicatorRearComponent = rememberShapeComponent(Shape.Pill)
    val indicator =
        rememberLayeredComponent(
            rear = indicatorRearComponent,
            front =
            rememberLayeredComponent(
                rear = indicatorCenterComponent,
                front = indicatorFrontComponent,
                padding = Dimensions.of(5.dp),
            ),
            padding = Dimensions.of(10.dp),
        )
    val guideline = rememberAxisGuidelineComponent()
    return remember(label, labelPosition, indicator, showIndicator, guideline) {
        object : DefaultCartesianMarker(
            label = label,
            labelPosition = labelPosition,
            indicator = if (showIndicator) indicator else null,
            indicatorSizeDp = 36f,
            setIndicatorColor =
            if (showIndicator) { it ->
                indicatorRearComponent.color = Color.Black.value.toInt()
                indicatorCenterComponent.color = it
                indicatorCenterComponent.setShadow(radius = 12f, color = it)

            } else {
                null
            },
            guideline = guideline,
        ) {
            override fun getInsets(
                context: CartesianMeasureContext,
                outInsets: Insets,
                horizontalDimensions: HorizontalDimensions,
            ) {
                with(context) {
                    outInsets.top =
                        (
                                CLIPPING_FREE_SHADOW_RADIUS_MULTIPLIER * LABEL_BACKGROUND_SHADOW_RADIUS_DP -
                                        LABEL_BACKGROUND_SHADOW_DY_DP
                                )
                            .pixels
                    if (labelPosition == LabelPosition.AroundPoint) return
                    outInsets.top += label.getHeight(context) + labelBackgroundShape.tickSizeDp.pixels
                }
            }
        }
    }
}

private const val LABEL_BACKGROUND_SHADOW_RADIUS_DP = 4f
private const val LABEL_BACKGROUND_SHADOW_DY_DP = 2f
private const val CLIPPING_FREE_SHADOW_RADIUS_MULTIPLIER = 1.4f

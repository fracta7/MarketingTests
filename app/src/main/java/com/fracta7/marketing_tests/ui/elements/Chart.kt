package com.fracta7.marketing_tests.ui.elements

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.round
import kotlin.math.roundToInt

@Composable
fun Chart(
    info: List<Float>,
    modifier: Modifier = Modifier,
    graphColor: Color = MaterialTheme.colorScheme.primary
) {
    val spacing = 100f
    val transparentGraphColor = remember {
        graphColor.copy(alpha = 0.5f)
    }
    val upperValue = remember(info) {
        (info.maxOfOrNull { it }?.plus(1)?.roundToInt() ?: 0)
    }
    val lowerValue = remember(info) {
        (info.minOfOrNull { it }?.toInt() ?: 0)
    }

    val density = LocalDensity.current

    val textPaint = remember {
        Paint().apply {
            color = android.graphics.Color.WHITE
            textAlign = Paint.Align.CENTER
            textSize = density.run { 12.sp.toPx() }
        }
    }

    Canvas(modifier = modifier) {
        val spacePerInfo = (size.width - spacing) / info.size
        (0 until info.size - 1 step 2).forEach {
            val infoEntry = info[it]
            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    it.toString(),
                    spacing + it * spacePerInfo,
                    size.height - 5,
                    textPaint
                )
            }
        }
        val infoStep = (upperValue - lowerValue) / 5f
        (0..5).forEach {
            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    round(lowerValue + infoStep * it).toString(),
                    30f,
                    size.height - spacing - it * size.height / 5f,
                    textPaint
                )
            }
        }
        var lastX = 0f
        val strokePath = Path().apply {
            val height = size.height
            for (i in info.indices) {
                val currentInfo = info[i]
                val nextInfo = info.getOrNull(i + 1) ?: info.last()
                val leftRatio = (currentInfo - lowerValue) / (upperValue - lowerValue)
                val rightRatio = (nextInfo - lowerValue) / (upperValue - lowerValue)

                val x1 = spacing + i * spacePerInfo
                val y1 = height - spacing - (leftRatio * height).toFloat()
                val x2 = spacing + (i + 1) * spacePerInfo
                val y2 = height - spacing - (rightRatio * height).toFloat()

                if (i == 0) {
                    moveTo(
                        x1,
                        y1
                    )
                }
                lastX = (x1 + x2) / 2f
                quadraticBezierTo(
                    x1, y1, lastX, (y1 + y2) / 2f
                )
            }
        }
        val fillPath = android.graphics.Path(strokePath.asAndroidPath())
            .asComposePath()
            .apply {
                lineTo(lastX, size.height - spacing)
                lineTo(spacing, size.height - spacing)
                close()
            }
        drawPath(
            path = fillPath,
            brush = Brush.verticalGradient(
                colors = listOf(
                    transparentGraphColor,
                    Color.Transparent
                ),
                endY = size.height - spacing
            )
        )
        drawPath(
            path = strokePath,
            color = graphColor,
            style = Stroke(
                width = 3.dp.toPx(),
                cap = StrokeCap.Round
            )
        )
    }
}
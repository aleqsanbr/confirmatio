package com.aleqsanbr.confirmatio.testsSystem

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HorizontalScoreScaleWithTicks(
    score: Int,
    maxScore: Int,
    numberOfTicks: Int,
    barWidth: Dp = 200.dp,
    barHeight: Dp = 30.dp,
    cornerRadius: Dp = 10.dp,
    points : List<Int>,
    padding : Dp = 0.dp,
    actText : Boolean = true
) {
    val actualScoreWidth = (barWidth * score.toFloat() / maxScore).coerceAtMost(barWidth)
    val barColor = when {
        score < points.get(0) -> Color(red = 0x8F, green = 0xBC, blue = 0x8F, alpha = 0xFF)
        score < points.get(1) -> Color(red = 0xFF, green = 0xD7, blue = 0x00, alpha = 0xFF)
        else -> Color(red = 0xB2, green = 0x22, blue = 0x22, alpha = 0xFF)
    }
    Column(
        modifier = Modifier.padding(horizontal = padding, vertical = padding/2).width(barWidth+24.dp)
    ) {

        if(actText) Text(text = "Шкала баллов", modifier = Modifier.padding(bottom = 8.dp))
        Canvas(
            modifier = Modifier
                .size(barWidth, barHeight)
                .padding(vertical = 8.dp, horizontal = 12.dp)
        ) {
            drawRoundRect(
                color = Color(red = 0xFA, green = 0xFA, blue = 0xD2, alpha = 0xFF),
                topLeft = Offset(0f, 0f),
                size = Size(barWidth.toPx(), barHeight.toPx()),
                cornerRadius = CornerRadius(cornerRadius.toPx(), cornerRadius.toPx()),
                style = Stroke(width = barHeight.toPx())
            )

            if (score > 0) {
                // Рисуем заполненную часть шкалы с скругленными углами
                drawRoundRect(
                    color = barColor,
                    topLeft = Offset(0f, 0f),
                    size = Size(actualScoreWidth.toPx(), barHeight.toPx()),
                    cornerRadius = CornerRadius(cornerRadius.toPx(), cornerRadius.toPx()),
                    style = Stroke(width = barHeight.toPx())
                )
            }



            // Рисуем промежуточные отметки
            val tickSpacing = barWidth.toPx() / numberOfTicks
            for (i in 0..numberOfTicks) {
                val x = i * tickSpacing
                drawLine(
                    color = Color.Black,
                    start = Offset(x, 0f),
                    end = Offset(x, barHeight.toPx()),
                    strokeWidth = 2f
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 2.dp)
                .offset(y = barHeight / 2 + 8.dp), // Смещение ниже шкалы
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "0", fontSize = 12.sp)
            Text(text = "$maxScore", fontSize = 12.sp)
        }

    }
}
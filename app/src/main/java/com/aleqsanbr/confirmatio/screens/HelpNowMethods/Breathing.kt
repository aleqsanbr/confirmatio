package com.aleqsanbr.confirmatio.screens.HelpNowMethods

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import com.aleqsanbr.confirmatio.SubTitle
import com.aleqsanbr.confirmatio.Title
import kotlinx.coroutines.delay


@Composable
fun BreathingExercise(ccolor: Color) {
    val breathState = remember { mutableStateOf("вдох") }
    val breathAnimation = remember { Animatable(0.5f) }
    LaunchedEffect(key1 = breathState.value) {
        when (breathState.value) {
            "вдох" -> {
                breathAnimation.animateTo(
                    targetValue = 0.95f,
                    animationSpec = tween(durationMillis = 4000, easing = FastOutSlowInEasing)
                )
                breathState.value = "задержка"
            }
            "задержка" -> {
                breathAnimation.animateTo(
                    targetValue = 0.95f,
                    animationSpec = tween(durationMillis = 4000, easing = LinearEasing)
                )
                breathState.value = "выдох"
            }
            "выдох" -> {
                breathAnimation.animateTo(
                    targetValue = 0.5f,
                    animationSpec = tween(durationMillis = 4000, easing = FastOutSlowInEasing)
                )
                breathState.value = "вдох"
            }
        }
    }
    Canvas(modifier = Modifier.fillMaxSize()) {
        val radius = size.minDimension * breathAnimation.value / 2
        drawCircle(
            color = ccolor,
            radius = radius,
            style = Stroke(width = 55f),
        )
        val paint = android.graphics.Paint().apply {
            color = android.graphics.Color.WHITE
            textSize = 100f
            textAlign = android.graphics.Paint.Align.CENTER
        }
        drawContext.canvas.nativeCanvas.drawText(
            breathState.value,
            size.width / 2,
            size.height / 2 + (paint.textSize / 3),
            paint
        )
    }
}

@Composable
fun Breathing() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title("Дыхательное упражнение")
        Spacer(modifier = Modifier.size(20.dp))
        SubTitle("Расслабьтесь и успокойтесь. Вдыхайте 4 секунды, задержите дыхание на 4 секунды, выдохните. Повторите 4 раза.")
        Spacer(modifier = Modifier.size(20.dp))
        BreathingExercise(MaterialTheme.colorScheme.primaryContainer)
        Spacer(modifier = Modifier.size(20.dp))

    }
}
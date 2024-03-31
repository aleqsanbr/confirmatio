package com.example.confirmatio.screens.HelpNowMethods

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.confirmatio.CustomText

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.drawscope.Stroke
import com.example.confirmatio.SubTitle
import com.example.confirmatio.Title

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.animation.core.*
import android.graphics.Paint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.graphics.nativeCanvas
import kotlinx.coroutines.delay


@Composable
fun BreathingExercise(ccolor: Color) {
    val infiniteTransition = rememberInfiniteTransition()
    val breathAnimation = infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 0.95f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 7000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )
    val breathState = remember { mutableStateOf("вдох") }
    LaunchedEffect(key1 = true) {
        while (true) {
            delay(7000)
            breathState.value = if (breathState.value == "вдох") "выдох" else "вдох"
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

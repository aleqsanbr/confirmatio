package com.example.confirmatio.screens

import android.widget.ToggleButton
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.confirmatio.SubTitle
import com.example.confirmatio.Title
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp



@Composable
fun SegmentedButtons() {
    // Состояние для выбранного переключателя
    var selected by remember { mutableStateOf(0) }
    // Список опций для переключателей
    val options = listOf("Заземление", "Дыхание", "Медитация")
    // Стиль для текста переключателей
    val textStyle = MaterialTheme.typography.button.copy(color = colorScheme.onBackground)
    // Стиль для выбранного переключателя
    val selectedButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = colorScheme.primaryContainer,
        contentColor = colorScheme.onBackground
    )
    // Стиль для не выбранного переключателя
    val unselectedButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = colorScheme.outlineVariant,
        contentColor = colorScheme.onSurface,
    )
    // Создаем горизонтальный ряд для переключателей
    Row(modifier = Modifier.fillMaxWidth()) {
        // Для каждой опции создаем переключатель
        options.forEachIndexed { index, text ->
            // Определяем, выбран ли переключатель
            val isSelected = selected == index
            // Определяем, является ли переключатель первым или последним в ряду
            val isStart = index == 0
            val isEnd = index == options.size - 1
            // Определяем, какие углы нужно закруглить для переключателя
            val shape = when (index) {
                0 -> RoundedCornerShape(topStart = 30.dp, bottomStart = 30.dp) // Первая кнопка с большими закруглениями слева
                2 -> RoundedCornerShape(topEnd = 30.dp, bottomEnd = 30.dp) // Третья кнопка с большими закруглениями справа
                else -> RoundedCornerShape(0.dp) // Вторая кнопка без закруглений
            }
            // Создаем переключатель с текстом и цветом в зависимости от выбранности
            Button(
                onClick = { selected = index },
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp),
                shape = shape,
                contentPadding = PaddingValues(
                    start = 2.dp,
                    top = 2.dp,
                    end = 2.dp,
                    bottom = 2.dp
                ),
                colors = if (isSelected) selectedButtonColors else unselectedButtonColors
            ) {
                Text(text = text, style = textStyle)
            }
        }
    }
}

@Composable
fun BigRoundButton(text: String) {

    val infiniteTransition = rememberInfiniteTransition()

    val sizeValue by infiniteTransition.animateFloat(
        initialValue = (LocalConfiguration.current.screenWidthDp.dp-70.dp).value,
        targetValue = (LocalConfiguration.current.screenWidthDp.dp-25.dp).value,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        )
    )

    val colorValue by infiniteTransition.animateFloat(
        initialValue = 0.6f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        )
    )
    Row(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(contentAlignment = Alignment.Center)
        {
            Box(
                modifier = Modifier
                    .size(sizeValue.dp)
                    .clip(shape = RoundedCornerShape(percent = 50))
                    .background(color = colorScheme.primaryContainer.copy(alpha = colorValue))
            )
            Button(
                onClick = { },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(colorScheme.primaryContainer),
                modifier = Modifier
                    .size(LocalConfiguration.current.screenWidthDp.dp-70.dp)
                    .aspectRatio(1f)
                ) {
                Text(
                    text = text,
                    fontSize = 48.sp,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun HelpNow() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(0.dp, 20.dp, 0.dp, 0.dp)
                .fillMaxWidth()
        ) {
            Title("Чувствуете тревогу?")
            SubTitle("Выберите технику")
            Row(modifier = Modifier.padding(horizontal = 15.dp, vertical = 25.dp)) {
                SegmentedButtons()
            }
           BigRoundButton(text = "Старт")
        }
    }
}


package com.example.confirmatio.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.confirmatio.SubTitle
import com.example.confirmatio.Title
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.confirmatio.screens.HelpNowMethods.Breathing
import com.example.confirmatio.screens.HelpNowMethods.Grounding
import com.example.confirmatio.screens.HelpNowMethods.Grounding.GroundingProcess
import com.example.confirmatio.screens.HelpNowMethods.Meditation

@Composable
fun BigRoundButton(text: String, onButtonClick: () -> Unit) {

    val infiniteTransition = rememberInfiniteTransition()

    val sizeValue by infiniteTransition.animateFloat(
        initialValue = (LocalConfiguration.current.screenWidthDp.dp-70.dp).value,
        targetValue = (LocalConfiguration.current.screenWidthDp.dp-25.dp).value,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = EaseInOut),
            repeatMode = RepeatMode.Restart,
        ), label = ""
    )

    val colorValue by infiniteTransition.animateFloat(
        initialValue = 0.6f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = EaseInOut),
            repeatMode = RepeatMode.Restart,
        ), label = ""
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
                onClick = {
                    onButtonClick()
                },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(colorScheme.primaryContainer),
                modifier = Modifier
                    .size(LocalConfiguration.current.screenWidthDp.dp - 70.dp)
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
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "helpNow") {
        composable("helpNow") { HelpNowContent(navController) }
        composable("grounding") { Grounding(navController) }
        composable("breathing") { Breathing() }
        composable("meditation") { Meditation() }
        composable("grounding_process") { GroundingProcess() }
    }
}

@Composable
fun HelpNowContent(navController: NavHostController) {
    var selected by remember { mutableStateOf(0) }
    val options = listOf("Заземление", "Дыхание", "Медитация")
    val textStyle = MaterialTheme.typography.button.copy(color = colorScheme.onBackground)
    val selectedButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = colorScheme.primaryContainer,
        contentColor = colorScheme.onBackground
    )
    val unselectedButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = colorScheme.outlineVariant,
        contentColor = colorScheme.onSurface,
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title("Чувствуете тревогу?")
        SubTitle("Выберите технику")
        Row(modifier = Modifier.padding(horizontal = 15.dp, vertical = 25.dp)) {
            options.forEachIndexed { index, text ->
                val isSelected = selected == index
                val isStart = index == 0
                val isEnd = index == options.size - 1
                val shape = when (index) {
                    0 -> RoundedCornerShape(topStart = 30.dp, bottomStart = 30.dp)
                    2 -> RoundedCornerShape(topEnd = 30.dp, bottomEnd = 30.dp)
                    else -> RoundedCornerShape(0.dp)
                }
                Button(
                    onClick = {
                        selected = index
                        //when (index) {
                        //    0 -> navController.navigate("grounding")
                        //    1 -> navController.navigate("breathing")
                        //    2 -> navController.navigate("meditation")
                        //}
                    },
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

        BigRoundButton(text = "Старт") {
            when (selected) {
                0 -> navController.navigate("grounding")
                1 -> navController.navigate("breathing")
                2 -> navController.navigate("meditation")
            }
        }
    }
}

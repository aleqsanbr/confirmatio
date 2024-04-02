package com.aleqsanbr.confirmatio.screens.HelpNowMethods.Grounding

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessibilityNew
import androidx.compose.material.icons.outlined.Audiotrack
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.Coffee
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.aleqsanbr.confirmatio.SubTitle
import com.aleqsanbr.confirmatio.Title
import kotlinx.coroutines.delay

@Composable
fun GroundingBlock(icon : ImageVector, title: String, description: String) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .padding(15.dp, 30.dp, 0.dp, 15.dp)
                    .size(65.dp)
                    .align(
                        Alignment.CenterVertically
                    )
            )
            Box(modifier = Modifier.align(Alignment.CenterVertically)) {
                Title(title)
            }
        }
        SubTitle(description)
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun GroundingProcess(navController: NavHostController) {
    val scrollState = rememberScrollState()

    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 0.dp)
            .verticalScroll(scrollState)
    ) {
        var blocks by remember { mutableStateOf(listOf(true, false, false, false, false)) }



        LaunchedEffect(blocks) {
            delay(300)
            scrollState.animateScrollTo(
                value = scrollState.maxValue,
                animationSpec = tween(durationMillis = 200, easing = LinearOutSlowInEasing)
            )
        }



        AnimatedVisibility(visible = blocks[0]) {
            Column(modifier = Modifier.fillMaxSize()) {
                val title = "Найдите пять любых предметов"
                val description = "Найдите пять объектов вокруг вас и произнесите их названия " +
                        "вслух или в уме. Это может быть что угодно, вне зависимости от места. " +
                        "Например, если вы находитесь в комнате, это могут быть окно, дверь, " +
                        "часы, салфетка и ручка. Не торопитесь, внимательно обозревайте и " +
                        "называйте предметы. Когда будете готовы, нажмите на кнопку."

                GroundingBlock(Icons.Outlined.Search, title, description)
            }
        }

        AnimatedVisibility(visible = blocks[1]) {
            Column(modifier = Modifier.fillMaxSize()) {
                val title = "Услышьте четыре звука"
                val description = "Услышьте четыре звука и назовите их в уме или вслух, если " +
                        "обстановка позволяет. Это может быть голос соседей, тиканье часов, " +
                        "звук мотора машины за окном или полная тишина, которая тоже является звуком."
                GroundingBlock(Icons.Outlined.Audiotrack, title, description)
            }
        }

        AnimatedVisibility(visible = blocks[2]) {
            Column(modifier = Modifier.fillMaxSize()) {
                val title = "Почувствуйте три ощущения"
                val description = "Почувствуйте три ощущения и назовите их в уме или вслух, " +
                        "если можно. Коснитесь трех объектов или отметьте свои ощущения. " +
                        "Например, ваши часы могут слегка давить на запястье; " +
                        "ваш свитер может быть мягким на ощупь; " +
                        "прикасаясь к батарее можно почувствовать, какая она теплая или твердая."
                GroundingBlock(Icons.Outlined.AccessibilityNew, title, description)
            }
        }

        AnimatedVisibility(visible = blocks[3]) {
            Column(modifier = Modifier.fillMaxSize()) {
                val title = "Заметьте два запаха"
                val description = "Заметьте два запаха и назовите в уме или вслух, если возможно. " +
                        "Найдите в вашем окружении предметы, которые могут иметь запах, и " +
                        "почувствуйте их аромат. Например, если вы используете парфюм, нанесите " +
                        "его на запястье, затем понюхайте и опишите аромат; если вы находитесь " +
                        "в комнате с множеством бумаги, ощутите запах бумаги и опишите его."
                GroundingBlock(Icons.Outlined.AutoAwesome, title, description)
            }
        }

        AnimatedVisibility(visible = blocks[4]) {
            Column(modifier = Modifier.fillMaxSize()) {
                val title = "Ощутите один вкус"
                val description = "Почувствуйте один вкус и назовите его в уме или вслух, если " +
                        "можно. С этим пунктом можно экспериментировать: сфокусируйтесь на вкусе " +
                        "во рту и опишите его (сладкий, горький, кислый, соленый); съешьте " +
                        "что-то или пожуйте жвачку и опишите вкус; представьте в уме какую-то " +
                        "еду и ощутите ее вкус (например, клубничную конфету – опишите ее " +
                        "сладость и аромат)."
                GroundingBlock(Icons.Outlined.Coffee, title, description)
            }
        }

        Column {
            Spacer(modifier = Modifier.padding(20.dp))
            var i by remember { mutableStateOf(0) }
            Button(
                onClick = {
                    if (i + 1 >= blocks.size) {
                        navController.navigate("grounding_success")
                    }
                    else {
                        blocks = blocks.toMutableList().also {
                            //it[i] = false
                            it[i + 1] = true
                        }
                        i++
                    }
                },
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(),
                shape = MaterialTheme.shapes.extraLarge,
                colors = ButtonDefaults.buttonColors(backgroundColor = colorScheme.onPrimary)
            ) {
                Text(text = "Далее", fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.padding(50.dp))
        }
    }
}

package com.aleqsanbr.confirmatio.screens.HelpNowMethods

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.aleqsanbr.confirmatio.StartButton
import com.aleqsanbr.confirmatio.Title
import com.aleqsanbr.confirmatio.screens.InfoCard

val about = "1. Cильная тревога или навязчивые мысли, мешающие концентрации внимания\n" +
        "2. Панические расстройства\n" +
        "3. Руминация (размышления о неприятных ситуациях в прошлом) и это ухудшают настроение\n" +
        "4. Острый стресс, когда хочется переключить внимание, расслабиться, отпустить и сфокусироваться на чем-то одном\n" +
        "5. И в любых других ситуациях, когда хочется успокоиться"

@Composable
fun Grounding(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(ScrollState(0)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title("Заземление")
        Spacer(modifier = Modifier.padding(5.dp))
        InfoCard(
            title = "Цель техники",
            "Переключить фокус внимания с тревожных мыслей на реальность, снять напряжение и стресс в моменте, замедлиться и успокоиться.",
            icon = Icons.Outlined.Info
        )
        Spacer(modifier = Modifier.padding(15.dp))
        InfoCard(title = "Когда пригодится?", about, icon = Icons.Outlined.Star)
        Spacer(modifier = Modifier.padding(10.dp))
        StartButton(text = "Старт") {
            navController.navigate("grounding_process")
        }
    }
}
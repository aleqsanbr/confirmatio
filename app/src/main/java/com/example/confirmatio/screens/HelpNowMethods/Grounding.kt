package com.example.confirmatio.screens.HelpNowMethods

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.confirmatio.Title
import com.example.confirmatio.screens.InfoCard
import com.example.confirmatio.testsSystem.TestLoader

@Composable
fun Grounding() {
    Column(modifier = Modifier.fillMaxSize()) {
        Title("Заземление")
        Spacer(modifier = Modifier.padding(5.dp))
        InfoCard(title = "Цель техники", "Переключить фокус внимания с тревожных мыслей на реальность, снять напряжение и стресс в моменте, замедлиться и успокоиться.", icon = Icons.Outlined.Info)
        Spacer(modifier = Modifier.padding(15.dp))
        InfoCard(title = "Когда пригодится?", "...", icon = Icons.Outlined.Star)
    }
}
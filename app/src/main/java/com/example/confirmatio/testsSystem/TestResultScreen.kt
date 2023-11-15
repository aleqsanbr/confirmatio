package com.example.confirmatio.testsSystem

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.confirmatio.NavigateUpButton

@Composable
fun TestResultScreen(
   // manager: TestManager,
    navigateToStart: () -> Unit)
 {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp, 0.dp),
    ) {
        Column(
            modifier = Modifier
                .align(CenterVertically),
        ){
            Text(
                modifier = Modifier,
                text = "Вы прошли тест!\nРезультаты появятся позже...",
                fontSize = 8.em,
                style = TextStyle(lineHeight = 40.sp)
            )
        }
    }
     NavigateUpButton(navigateToStart)
}
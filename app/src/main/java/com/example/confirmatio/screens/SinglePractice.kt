package com.example.confirmatio.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.confirmatio.navigation.PracticeID
import com.example.confirmatio.practices.BrainStorm
import com.example.confirmatio.practices.HotThoughts
import com.example.confirmatio.practices.MagicWand
import com.example.confirmatio.practices.MindTraps
import com.example.confirmatio.practices.PieTechnique

@Composable
fun SinglePractice(
    practiceId: Int,
    navController: NavHostController
)
{
    when(practiceId) {
        PracticeID.Pie.id -> PieTechnique()
        PracticeID.MindTrap.id -> MindTraps()
        PracticeID.BrainStorm.id -> BrainStorm()
        PracticeID.HotThought.id -> HotThoughts()
        PracticeID.MagicWand.id -> MagicWand()

        else ->
        {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 25.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            )
            {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center,
                    text = "Данная практика еще не реализована!",
                    fontSize = 23.sp
                )
            }
        }
    }
}


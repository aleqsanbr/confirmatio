package com.aleqsanbr.confirmatio.practices

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aleqsanbr.compose.md_theme_dark_secondaryContainer
import com.aleqsanbr.confirmatio.Title
import com.aleqsanbr.confirmatio.database.NotesViewModel

sealed class ScreensForVFB(val route: String) {
    object view_from_balcony : Screen(route = "view_from_balcony");
    object questons_vfb : Screen(route = "questions_view_from_balcony")

}

@Composable
fun navViewFromBalcony(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = ScreensForVFB.view_from_balcony.route
    ) {
        composable(ScreensForVFB.view_from_balcony.route) { ViewFromBalcony1(navController) }
        composable(ScreensForVFB.questons_vfb.route) { screenWithQuestionsForVFB(navController) }
    }
}

@Composable
fun ViewFromBalcony() {
    val navController = rememberNavController()
    navViewFromBalcony(navController = navController);
}

@Composable
fun ViewFromBalcony1(navController: NavHostController) {
    Column(
        modifier = Modifier
            .background(color = Color.Transparent)
            .verticalScroll(ScrollState(0))
    ) {

        Title("Упражнение\n\"Вид с балкона\"")

        headings("Описание")

        contentText(
            "Это упражнение поможет научить сознание не зацикливаться на пессимистичной трактовке. " +
                    "Попробуйте взять паузу и снова проанализировать положение. Если попытаться, " +
                    "почти всегда можно посмотреть на него с другой точки зрения. Вспомните театр: из каждого сектора — партера, " +
                    "бельэтажа или ложи — открывается свой вид на сцену. Посидев в разных местах, вы получите новые впечатления. "
        )

        Spacer(modifier = Modifier.padding(5.dp))

        val unselectedButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = md_theme_dark_secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSurface,
        )
        val textStyle =
            androidx.compose.material.MaterialTheme.typography.button.copy(color = MaterialTheme.colorScheme.onBackground)
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    navController.navigate(ScreensForVFB.questons_vfb.route)
                },
                shape = RoundedCornerShape(20.dp),
                colors = unselectedButtonColors
            ) {

                Text(
                    text = "✍\uD83C\uDFFB заполнить шаблон \n\n«Вид с балкона»",
                    modifier = Modifier
                        .padding(horizontal = 15.dp, vertical = 5.dp),
                    style = textStyle,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                )
            }
        }

        Spacer(modifier = Modifier.padding(5.dp));

        contentText(
            "Попрактиковавшись, вы обнаружите, что сознание начнет переключаться на " +
                    "другие возможные трактовки ситуации быстрее и легче. Это показывает, " +
                    "что мышление становится пластичнее. Развив гибкость мышления, " +
                    "вы реже будете испытывать тревогу и подавленность и перестанете " +
                    "стремительно погружаться в пучину тоски и мрака."
        )

        Spacer(modifier = Modifier.padding(10.dp));
    }
}

@Composable
fun screenWithQuestionsForVFB(
    navController: NavHostController,
    notesViewModel: NotesViewModel = viewModel(factory = NotesViewModel.factory)
) {

}
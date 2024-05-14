package com.aleqsanbr.confirmatio.practices

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aleqsanbr.compose.md_theme_dark_onSecondary
import com.aleqsanbr.compose.md_theme_dark_secondaryContainer
import com.aleqsanbr.confirmatio.Title
import com.aleqsanbr.confirmatio.database.NoteType
import com.aleqsanbr.confirmatio.database.NotesViewModel
import java.util.Date

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

val questions1 = listOf(
    "Вкратце опишите событие или ситуацию",
    "Опишите свою негативную интерпретацию события под влиянием депрессии или тревожности"
)


fun createNote(answers : MutableList<String> ) : String {
    val note = "\n**Краткое описание события или ситуации:**\n" + answers[0]+
            "\n\n**Ваша негативная интерпретация:**\n" + answers[1] +
            "\n\n**Насколько вы были в этом убеждены до выполнения практики:**\n" + answers[2]+
            "\n\n**Иная точка зрения №1 на события:**\n" + answers[3]+
            "\n\n**Иная точка зрения №2 на события:**\n" + answers[4]+
            "\n\n**Иная точка зрения №3 на события:**\n" + answers[5]+
            "\n\n**Иная точка зрения №4 на события:**\n" + answers[6]+
            "\n\n**Иная точка зрения №5 на события:**\n" + answers[7]+
            "\n\n**Ваша переоценка негативной интерпретации после выполнения практики:**\n" + answers[8]

            return note
}
@Composable
fun screenWithQuestionsForVFB(
    navController: NavHostController,
    notesViewModel: NotesViewModel = viewModel(factory = NotesViewModel.factory)
) {
    var currentQuestionIndex by remember { mutableStateOf(0) }

    val unselectedButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = md_theme_dark_secondaryContainer,
        contentColor = MaterialTheme.colorScheme.onSurface,
    )
    val shape = RoundedCornerShape(20.dp)
    val textStyle =
        androidx.compose.material.MaterialTheme.typography.button.copy(color = MaterialTheme.colorScheme.onBackground)
    var _answer by remember { mutableStateOf(mutableListOf<String>()) }

    var otherPointsOfView by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (currentQuestionIndex < 2) {

            Text(
                text = questions1[currentQuestionIndex],
                modifier = Modifier.fillMaxWidth(0.75f),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.padding(10.dp));

            var answer by remember { mutableStateOf(TextFieldValue("")) }

            val isKeyboardOpen by keyboardAsState()
            val focusManager = LocalFocusManager.current
            if (!isKeyboardOpen) {
                focusManager.clearFocus()
            }
            TextField(
                enabled = true,
                modifier = Modifier
                    .verticalScroll(ScrollState(0))
                    .fillMaxWidth(0.75f)
                    .height(350.dp),
                shape = RoundedCornerShape(15.dp),
                value = answer,
                onValueChange = { answer = it },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.White,
                    textColor = Color.White,
                    backgroundColor = md_theme_dark_secondaryContainer,
                ),
            )

            Spacer(modifier = Modifier.padding(10.dp))

            Button(
                onClick = {
                    _answer.add(answer.text)
                    answer = TextFieldValue("");
                    currentQuestionIndex++
                },
                shape = RoundedCornerShape(20.dp),
                colors = unselectedButtonColors
            ) {
                Text(
                    text = "Далее",
                    modifier = Modifier
                        .padding(horizontal = 15.dp, vertical = 5.dp),
                    style = textStyle,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                )
            }
        } else if (currentQuestionIndex == 2) {
            Text(
                text = "Оцените, насколько верны ваши предсказания\n(100% - полная уверенность в вашей правоте)",
                modifier = Modifier.fillMaxWidth(0.80f),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.padding(10.dp))
            var valueSlider by remember { mutableStateOf(50f) }
            Box(
                modifier = Modifier
                    .background(
                        color = md_theme_dark_secondaryContainer,
                        shape = shape
                    )
                    .width(LocalConfiguration.current.screenWidthDp.dp - 50.dp),
            )

            {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    androidx.compose.material.Text(
                        text = "${valueSlider.toInt()}%",
                        fontSize = 50.sp,
                        textAlign = TextAlign.Center,
                        style = textStyle,
                        modifier = Modifier.padding(top = 20.dp)
                    )
                    Slider(
                        modifier = Modifier.padding(horizontal = 10.dp),
                        value = valueSlider,
                        valueRange = 1f..100f,
                        onValueChange = { valueSlider = it },
                        colors = SliderDefaults.colors(
                            thumbColor = md_theme_dark_onSecondary,
                            activeTrackColor = Color.White,
                            inactiveTrackColor = Color.White,
                            inactiveTickColor = Color.White,
                            activeTickColor = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                }
            }
            Spacer(modifier = Modifier.padding(10.dp))

            Button(
                onClick = {
                    _answer.add("${valueSlider.toInt()}%")
                    currentQuestionIndex++
                },
                shape = RoundedCornerShape(20.dp),
                colors = unselectedButtonColors
            ) {
                Text(
                    text = "Далее",
                    modifier = Modifier
                        .padding(horizontal = 15.dp, vertical = 5.dp),
                    style = textStyle,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                )
            }
        } else if (currentQuestionIndex == 3) {
            Text(
                text = "Придумайте 5 иных точек зрения или объяснений произошедшего",
                modifier = Modifier.fillMaxWidth(0.80f),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(10.dp))
            Button(
                onClick = {
                    currentQuestionIndex++
                },
                shape = RoundedCornerShape(20.dp),
                colors = unselectedButtonColors
            ) {
                androidx.compose.material3.Text(
                    text = "Далее",
                    modifier = Modifier
                        .padding(horizontal = 15.dp, vertical = 5.dp),
                    style = textStyle,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                )
            }
        } else if (currentQuestionIndex < 9) {
            Text(
                text = "Иная точка зрения №" + "${otherPointsOfView + 1}",
                modifier = Modifier.fillMaxWidth(0.75f),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.padding(5.dp));

            var answer by remember { mutableStateOf(TextFieldValue("")) }

            val isKeyboardOpen by keyboardAsState()
            val focusManager = LocalFocusManager.current
            if (!isKeyboardOpen) {
                focusManager.clearFocus()
            }
            TextField(
                enabled = true,
                modifier = Modifier
                    .verticalScroll(ScrollState(0))
                    .fillMaxWidth(0.75f)
                    .height(350.dp),
                shape = RoundedCornerShape(15.dp),
                value = answer,
                onValueChange = { answer = it },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.White,
                    textColor = Color.White,
                    backgroundColor = md_theme_dark_secondaryContainer,
                ),
            )

            Spacer(modifier = Modifier.padding(5.dp));

            Text(
                text = "Оцените её правдоподобность",
                modifier = Modifier.fillMaxWidth(0.75f),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(5.dp));
            var valueSlider by remember { mutableStateOf(50f) }
            Box(
                modifier = Modifier
                    .background(
                        color = md_theme_dark_secondaryContainer,
                        shape = shape
                    )
                    .width(LocalConfiguration.current.screenWidthDp.dp - 50.dp),
            )
            {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    androidx.compose.material.Text(
                        text = "${valueSlider.toInt()}%",
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center,
                        style = textStyle,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                    Slider(
                        modifier = Modifier.padding(horizontal = 10.dp),
                        value = valueSlider,
                        valueRange = 1f..100f,
                        onValueChange = { valueSlider = it },
                        colors = SliderDefaults.colors(
                            thumbColor = md_theme_dark_onSecondary,
                            activeTrackColor = Color.White,
                            inactiveTrackColor = Color.White,
                            inactiveTickColor = Color.White,
                            activeTickColor = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                }
            }
            Spacer(modifier = Modifier.padding(10.dp))

            Button(
                onClick = {
                    otherPointsOfView++
                    _answer.add(answer.text + " (Вероятность истинности - " + " ${valueSlider.toInt()}%)")
                    valueSlider = 50f
                    answer = TextFieldValue("");
                    currentQuestionIndex++
                },
                shape = RoundedCornerShape(20.dp),
                colors = unselectedButtonColors
            ) {
                Text(
                    text = "Далее",
                    modifier = Modifier
                        .padding(horizontal = 15.dp, vertical = 5.dp),
                    style = textStyle,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                )
            }
        } else if (currentQuestionIndex == 9) {
            Text(
                text = "Прочитайте заполненный шаблон ещё раз и переоцените собственную негативную интерпретацию события",
                modifier = Modifier.fillMaxWidth(0.80f),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(10.dp))
            Button(
                onClick = {
                    currentQuestionIndex++
                },
                shape = RoundedCornerShape(20.dp),
                colors = unselectedButtonColors
            ) {
                androidx.compose.material3.Text(
                    text = "Далее",
                    modifier = Modifier
                        .padding(horizontal = 15.dp, vertical = 5.dp),
                    style = textStyle,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                )
            }
        } else if (currentQuestionIndex == 10) {
            Column(horizontalAlignment = Alignment.CenterHorizontally)
            {
                Box(
                    modifier = Modifier
                        .background(
                            color = md_theme_dark_secondaryContainer,
                            shape = shape
                        )
                        .width(LocalConfiguration.current.screenWidthDp.dp - 50.dp)
                        .height(LocalConfiguration.current.screenHeightDp.dp / 2),
                ) {
                    Spacer(modifier = Modifier.padding(5.dp));
                    val text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontStyle = FontStyle.Italic,
                                color = Color(red = 0x00, green = 0xFF, blue = 0x7F, alpha = 0xFF)
                            )
                        )
                        { append("Событие: ") }
                        append(_answer[0] + "\n\n")

                        withStyle(
                            style = SpanStyle(
                                fontStyle = FontStyle.Italic,
                                color = Color(red = 0x00, green = 0xFF, blue = 0x7F, alpha = 0xFF)
                            )
                        )
                        { append("Ваша негативная интерпретация события: ") }
                        append(_answer[1] + "\n\n")

                        withStyle(
                            style = SpanStyle(
                                fontStyle = FontStyle.Italic,
                                color = Color(red = 0x00, green = 0xFF, blue = 0x7F, alpha = 0xFF)
                            )
                        )
                        { append("Насколько вы в этом убеждены (сначала): ") }
                        append(_answer[2] + "\n\n")

                        withStyle(
                            style = SpanStyle(
                                fontStyle = FontStyle.Italic,
                                color = Color(red = 0x00, green = 0xFF, blue = 0x7F, alpha = 0xFF)
                            )
                        )
                        { append("Другая точка зрения №1: ") }
                        append(_answer[3] + "\n\n")

                        withStyle(
                            style = SpanStyle(
                                fontStyle = FontStyle.Italic,
                                color = Color(red = 0x00, green = 0xFF, blue = 0x7F, alpha = 0xFF)
                            )
                        )
                        { append("Другая точка зрения №2: ") }
                        append(_answer[4] + "\n\n")

                        withStyle(
                            style = SpanStyle(
                                fontStyle = FontStyle.Italic,
                                color = Color(red = 0x00, green = 0xFF, blue = 0x7F, alpha = 0xFF)
                            )
                        )
                        { append("Другая точка зрения №3: ") }
                        append(_answer[5] + "\n\n")

                        withStyle(
                            style = SpanStyle(
                                fontStyle = FontStyle.Italic,
                                color = Color(red = 0x00, green = 0xFF, blue = 0x7F, alpha = 0xFF)
                            )
                        )
                        { append("Другая точка зрения №4: ") }
                        append(_answer[6] + "\n\n")

                        withStyle(
                            style = SpanStyle(
                                fontStyle = FontStyle.Italic,
                                color = Color(red = 0x00, green = 0xFF, blue = 0x7F, alpha = 0xFF)
                            )
                        )
                        { append("Другая точка зрения №5: ") }
                        append(_answer[7] + "\n")
                    }
                    Text(
                        modifier = Modifier
                            .padding(10.dp, 5.dp)
                            .verticalScroll(rememberScrollState()),
                        text = text,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start,
                        overflow = TextOverflow.Clip,
                    )


                }

                Spacer(modifier = Modifier.padding(5.dp));

                var valueSlider by remember { mutableStateOf(50f) }
                Box(
                    modifier = Modifier
                        .background(
                            color = md_theme_dark_secondaryContainer,
                            shape = shape
                        )
                        .width(LocalConfiguration.current.screenWidthDp.dp - 50.dp),
                )
                {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        androidx.compose.material.Text(
                            text = "${valueSlider.toInt()}%",
                            fontSize = 30.sp,
                            textAlign = TextAlign.Center,
                            style = textStyle,
                            modifier = Modifier.padding(top = 10.dp)
                        )
                        Slider(
                            modifier = Modifier.padding(horizontal = 10.dp),
                            value = valueSlider,
                            valueRange = 1f..100f,
                            onValueChange = { valueSlider = it },
                            colors = SliderDefaults.colors(
                                thumbColor = md_theme_dark_onSecondary,
                                activeTrackColor = Color.White,
                                inactiveTrackColor = Color.White,
                                inactiveTickColor = Color.White,
                                activeTickColor = Color.White
                            )
                        )
                        Spacer(modifier = Modifier.padding(5.dp))
                    }
                }
                Spacer(modifier = Modifier.padding(5.dp));

                Button(
                    onClick = {
                        _answer.add("${valueSlider.toInt()}%")
                        val diaryEntry = createNote(_answer)
                        notesViewModel.InsertItem("Запись из упражнения \"Вид с балкона\"-", diaryEntry, Date(), NoteType.PRACTICE.type )
                        navController.navigateUp()
                        currentQuestionIndex++


                    },
                    shape = RoundedCornerShape(20.dp),
                    colors = unselectedButtonColors
                ) {
                    Text(
                        text = "Сохранить запись в дневник",
                        modifier = Modifier
                            .padding(horizontal = 15.dp, vertical = 5.dp),
                        style = textStyle,
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                    )
                }
            }
        }
    }
}